package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button topBtn = findViewById(R.id.top);
        topBtn.setText("all Task");
        topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "the button was cicked", Toast.LENGTH_SHORT).show();
                EditText taskTitle = findViewById(R.id.editTextTextPersonName);
                String taskTitleValue = taskTitle.getText().toString();

                        Intent goToAllTask = new Intent(AddTask.this,MainActivity.class);
                startActivity(goToAllTask);
            }
        });
        Button secBtn = findViewById(R.id.secbutton);
        secBtn.setText("submit");
        secBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "the button was cicked", Toast.LENGTH_SHORT).show();
                EditText taskDescription = findViewById(R.id.editTextTextPersonName2);
                String taskDescriptionValue = taskDescription.getText().toString();

                Intent goToAllTask = new Intent(AddTask.this,MainActivity.class);
                startActivity(goToAllTask);
            }
        });
        TextView AddText = findViewById(R.id.addText);
        AddText.setText("Add task");



    }
}