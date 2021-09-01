package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        Intent goToAllTask = new Intent(TaskDetailPage.this,AllTask.class);
        startActivity(goToAllTask);


        Intent intent = getIntent();
        String titleMove = intent.getExtras().getString("title");

        //to set text name
        TextView tileNameForDetailsPage = findViewById(R.id.titleDetailsPage);
        tileNameForDetailsPage.setText(titleMove);


    }
}