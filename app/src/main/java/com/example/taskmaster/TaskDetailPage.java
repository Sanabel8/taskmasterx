package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        Button goHome = findViewById(R.id.home2);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToShowing = new Intent(TaskDetailPage.this,MainActivity.class);

                startActivity(goToShowing);
            }
        });

        Intent intent = getIntent();

        String forName = intent.getExtras().getString("TaskName");
        String forBody = intent.getExtras().getString("TaskBody");
        String forState = intent.getExtras().getString("TaskState");

        TextView textViewForName = findViewById(R.id.nameOfTask);
        TextView textViewForBody = findViewById(R.id.textForBody);
        TextView textViewForState = findViewById(R.id.textForState);


        textViewForName.setText(forName);
        textViewForBody.setText(forBody);
        textViewForState.setText(forState);

    }

}