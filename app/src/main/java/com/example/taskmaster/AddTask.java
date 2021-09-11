package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Team> teamList = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.getTeamName());
                        teamList.add(todo);

                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button topBtn = (Button) findViewById(R.id.TopBtnToMain);
        topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMain = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMain);
            }
        });

        EditText editTitle = findViewById(R.id.taskTitleediting);
        EditText editDescription = findViewById(R.id.editingDescription);
        EditText editstate = findViewById(R.id.editState);


        Button addTaskPageBtn = findViewById(R.id.submitbtn);
        addTaskPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(), "Add Task Done !", Toast.LENGTH_SHORT).show();

//                Task task = new Task(editTitle.getText().toString(),editDescription.getText().toString(),editstate.getText().toString());
//                TaskDb db = Room.databaseBuilder(getApplicationContext(),TaskDb.class, "tasks").allowMainThreadQueries().build();
//                TaskDao dao = db.taskDao();
//                dao.insert(task);
                RadioButton rb1 = findViewById(R.id.radioButton1);
                RadioButton rb2 = findViewById(R.id.radioButton2);
                RadioButton rb3 = findViewById(R.id.radioButton3);

                String name="";
                if(rb1.isChecked()) {
                    name="team1";
                }else if(rb2.isChecked()){
                    name = "team2";
                }else if(rb3.isChecked()){
                    name = "team3";
                }


                Team teamObj =null;
                for (int i = 0; i < teamList.size(); i++) {
                    if(teamList.get(i).getTeamName().equals(name)){
                        teamObj = teamList.get(i);
                    }
                }


                com.amplifyframework.datastore.generated.model.Task task1 = Task.builder()
                        .title(editTitle.getText().toString())
                        .body(editDescription.getText().toString())
                        .state(editstate.getText().toString())
                        .team(teamObj)
                        .build();

                Amplify.API.mutate(ModelMutation.create(task1),
                        response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );


                Intent gotToHome = new Intent(AddTask.this, MainActivity.class);
                startActivity(gotToHome);
            }
        });

    }


}