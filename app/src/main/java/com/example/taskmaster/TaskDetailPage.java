package com.example.taskmaster;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

        String forName = intent.getExtras().getString("title");
        String forBody = intent.getExtras().getString("body");
        String forState = intent.getExtras().getString("state");
//        String forLatLog = intent.getExtras().getString("state");

        TextView textViewForName = findViewById(R.id.nameOfTask);
        TextView textViewForBody = findViewById(R.id.textForBody);
        TextView textViewForState = findViewById(R.id.textForState);
        TextView longAndLat = findViewById(R.id.viewlatandLog);

        textViewForName.setText(forName);
        textViewForBody.setText(forBody);
        textViewForState.setText(forState);
        longAndLat.setText("longtitude= "+intent.getExtras().getString("lon") +"\n" + " latitude= "+intent.getExtras().getString("lat"));


        //to download image
        Amplify.Storage.downloadFile(
                intent.getExtras().getString("imgName"),
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    ImageView imageView = findViewById(R.id.imagFromAdd);
                    String newImg = result.getFile().getPath();
                    imageView.setImageBitmap(BitmapFactory.decodeFile(newImg));

                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());},
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
    }

}