package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button11);
        button.setText("add Task");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "the button was cicked", Toast.LENGTH_SHORT).show();
                Intent goToAddTask = new Intent(MainActivity.this,AddTask.class);
                startActivity(goToAddTask);
            }
        });
        Button button2 = (Button) findViewById(R.id.button12);
        button2.setText("all Task");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "the button was cicked", Toast.LENGTH_SHORT).show();

                Intent goToAllTask = new Intent(MainActivity.this,AllTask.class);
                startActivity(goToAllTask);
            }
        });
        TextView textView = findViewById(R.id.welcomeText);
        textView.setText("welcome In Home Page");


    }

    @Override
    protected void onResume() {
        super.onResume();
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