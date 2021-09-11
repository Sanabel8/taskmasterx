package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnAddTask = (Button) findViewById(R.id.addTaskBtn);
        btnAddTask.setText("add Task");
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTask);
            }
        });

        Button settingbtn = findViewById(R.id.settitngbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this, SettingsPage.class);
                startActivity(goToSettingPage);
            }
        });

//        TaskDb taskDB = Room.databaseBuilder(getApplicationContext(), TaskDb.class, "tasks").allowMainThreadQueries().build();

//        List<Task> infoForList ;
//        TaskDao taskDao;
//        taskDao = taskDB.taskDao();
//        infoForList = taskDao.getAll();

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String team = sharedPreferences.getString("team", "team");


        RecyclerView allTASKsRecuclerView = findViewById(R.id.recycleViewListtask);
        List<Team> teams = new ArrayList<>();
        List<Task> allTasksData = new ArrayList<>();

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                allTASKsRecuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        allTASKsRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recyclerView
        allTASKsRecuclerView.setAdapter(new TaskAdapter(allTasksData));


//
//        Amplify.API.query(
//                ModelQuery.list(Task.class),
//                response -> {
//                    for (Task task : response.getData()) {
//                        Log.i("MyAmplifyApp", task.getBody());
//                        allTasksData.add(task);
//                    }
//                    handler.sendEmptyMessage(1);
//                    Log.i("MyAmplifyApp", "outsoid the loop");
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team teamo : response.getData()) {
                        Log.i("MyAmplifyApp", teamo.getTeamName());
                        Log.i("MyAmplifyApp", teamo.getId());

                        ///add new data to array
                        teams.add(teamo);
                    }
                    for (int i = 0; i < teams.size(); i++) {
                        if (teams.get(i).getTeamName().equals(team)) {
                            allTasksData.addAll(teams.get(i).getTasks());
                            break;
                        }
                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outside the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        String welcomeMessage = "Hello ";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String nameFromSetting = sharedPreferences.getString("username", "sanbael");
        String team = sharedPreferences.getString("team", "team");

        TextView nameView = findViewById(R.id.hiUserName);
        nameView.setText(welcomeMessage + nameFromSetting);

        TextView teamName = findViewById(R.id.teamName);
        teamName.setText(team);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}