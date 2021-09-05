package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        Intent goToAllTask = new Intent(TaskDetailPage.this,AllTask.class);
        startActivity(goToAllTask);


        Intent intent = getIntent();

        String forName = intent.getExtras().getString("titleTask");
        String forBody = intent.getExtras().getString("bodyTask");
        String forState = intent.getExtras().getString("stateTask");

        TextView textViewForName = findViewById(R.id.nameOfTask);
        TextView textViewForBody = findViewById(R.id.textForBody);
        TextView textViewForState = findViewById(R.id.textForState);


        textViewForName.setText(forName);
        textViewForBody.setText(forBody);
        textViewForState.setText(forState);

    }
}