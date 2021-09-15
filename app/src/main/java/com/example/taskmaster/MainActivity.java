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
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void logIn(){
        Amplify.Auth.signInWithWebUI(
                MainActivity.this,
                result -> {Log.i("AuthQuickStart", result.toString());},
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }
    //to check the state for login
    public String checkLoginStatus(){
        String username="";
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifyQuickstart", String.valueOf(result.isSignedIn()));
                    if (!result.isSignedIn()){
                        logIn();
                    }
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        username = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        logIn();

       Button logOut = (Button) findViewById(R.id.logOutBtn);
       logOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Amplify.Auth.signOut(
                       () -> {
                           logIn();
                           Log.i("AuthQuickstart", "Signed out successfully");
                       },
                       error -> Log.e("AuthQuickstart", error.toString())
               );
           }
       });

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

        String username = checkLoginStatus();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String nameFromSetting = sharedPreferences.getString("username", "sanbael");
        String team = sharedPreferences.getString("team", "team");

        TextView nameView = findViewById(R.id.hiUserName);
        nameView.setText("hello" + username);

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