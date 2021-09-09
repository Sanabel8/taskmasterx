package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
                Intent goToAddTask = new Intent(MainActivity.this,AddTask.class);
                startActivity(goToAddTask);
            }
        });


//        Button btnAllTask = (Button) findViewById(R.id.allTaskBtn);
//
//        btnAllTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToAllTask = new Intent(MainActivity.this,AllTask.class);
//                startActivity(goToAllTask);
//            }
//        });

        //lab27
//        Button showtbtn = findViewById(R.id.showingBtn);
//        showtbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToShowing = new Intent(MainActivity.this,SettingsPage.class);
//
//                startActivity(goToShowing);
//            }
//        });
//        Button displayBtn = findViewById(R.id.displayingBtn);
//        displayBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goTodisplay = new Intent(MainActivity.this,SettingsPage.class);
//                startActivity(goTodisplay);
//            }
//        });
        Button settingbtn = findViewById(R.id.settitngbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this,SettingsPage.class);
                startActivity(goToSettingPage);
            }
        });



        TaskDb taskDB = Room.databaseBuilder(getApplicationContext(), TaskDb.class, "tasks").allowMainThreadQueries().build();

        List<Task> infoForList;
        TaskDao taskDao;
        taskDao = taskDB.taskDao();
        infoForList = taskDao.getAll();

        // get the recycler view
        RecyclerView allTASKsRecuclerView = findViewById(R.id.recycleViewListtask);
        // set a layout manager for this view
        allTASKsRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recyclerView
        allTASKsRecuclerView.setAdapter(new TaskAdapter(infoForList));

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