package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button topBtn =(Button) findViewById(R.id.TopBtnToMain);
        topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMain = new Intent(AddTask.this,MainActivity.class);
                startActivity(goToMain);
            }
        });



        EditText editTitle = findViewById(R.id.taskTitleediting);

        EditText editDescription = findViewById(R.id.editingDescription);


        Button addTaskPageBtn = findViewById(R.id.submitbtn);
        addTaskPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Add Task Done !", Toast.LENGTH_SHORT).show();


                String titleForTask=editTitle.getText().toString();
                String descriptionTask =editDescription.getText().toString();

                TaskDb db = Room.databaseBuilder(getApplicationContext(),TaskDb.class,"Task_db").allowMainThreadQueries().build();
                Task task = new Task(titleForTask,descriptionTask,"new");

                db.taskDao().insert(task);


                Intent intent = new Intent(AddTask.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
}