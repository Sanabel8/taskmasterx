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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<com.amplifyframework.datastore.generated.model.Task> list = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.DataStore.observe(com.amplifyframework.datastore.generated.model.Task.class,
                started -> Log.i("MyAmplifyApp", "Observation began."),
                change -> Log.i("MyAmplifyApp", change.item().toString()),
                failure -> Log.e("MyAmplifyApp", "Observation failed.", failure),
                () -> Log.i("MyAmplifyApp", "Observation complete.")
        );


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

//        List<Task> list;
        list = new ArrayList<>();
//        TaskDao taskDao;
//        taskDao = taskDB.taskDao();
//        infoForList = taskDao.getAll();



        // get the recycler view
        RecyclerView allTASKsRecuclerView = findViewById(R.id.recycleViewListtask);
        // set a layout manager for this view
        allTASKsRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recyclerView
        allTASKsRecuclerView.setAdapter(new TaskAdapter(list));

        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allTASKsRecuclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                response -> {

                    for (com.amplifyframework.datastore.generated.model.Task todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.getTitle());
                        list.add(todo);
                        System.out.println(todo.getTitle());
                    }
                    handler.sendEmptyMessage(1);
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

        TextView nameView = findViewById(R.id.hiUserName);
        nameView.setText(welcomeMessage + nameFromSetting);
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
    protected void onStart() { super.onStart(); }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}