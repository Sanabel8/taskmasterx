package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task);

        TextView text = findViewById(R.id.allTasxText);
        text.setText("all task");

        Button reverseBut = findViewById(R.id.button1);
        reverseBut.setText("back button");
        reverseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AllTask = new Intent(AllTask.this, MainActivity.class);
                startActivity(AllTask);
            }
        });
    }

}