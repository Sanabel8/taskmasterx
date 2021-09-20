package com.example.taskmaster;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {
    //for location
    private FusedLocationProviderClient fusedLocationClient;
    private String lon;
    private String lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //for location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1234);

            boolean x = ActivityCompat
                    .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat
                            .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED;


            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();

                            lat = String.valueOf(longitude);
//                            lat.setText((int) latitude);
                            lon = String.valueOf(latitude);
//                            lon.setText((int) longitude);
                        }
                    }

                });
        ///////end location////////

    }

    String imgName = "";

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (intent.getType() != null && intent.getType().equals("text/plain")) {
            EditText desc = findViewById(R.id.editingDescription);
            desc.setText(intent.getExtras().get(Intent.EXTRA_TEXT).toString());
        }

        List<Team> teamList = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getTeamName());
                        teamList.add(team);

                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button uploadImg = findViewById(R.id.btnUplodImage);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChoose();
            }
        });

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

                String name = "";
                if (rb1.isChecked()) {
                    name = "team1";
                } else if (rb2.isChecked()) {
                    name = "team2";
                } else if (rb3.isChecked()) {
                    name = "team3";
                }


                Team team = null;
                for (int i = 0; i < teamList.size(); i++) {
                    if (teamList.get(i).getTeamName().equals(name)) {
                        team = teamList.get(i);
                    }
                }


                com.amplifyframework.datastore.generated.model.Task task1 = Task.builder()
                        .title(editTitle.getText().toString())
                        .body(editDescription.getText().toString())
                        .state(editstate.getText().toString())
                        .imgName(imgName)
                        .lon(lon)
                        .lat(lat)
                        .team(team)
                        .build();
//         .lat(lat)
//         .lon(lon)
                Amplify.API.mutate(ModelMutation.create(task1),
                        response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error));


                Intent gotToHome = new Intent(AddTask.this, MainActivity.class);
                startActivity(gotToHome);
            }
        });

    }

    public void fileChoose() {
        Intent fileChoose = new Intent(Intent.ACTION_GET_CONTENT);
        fileChoose.setType("*/*");
        fileChoose = Intent.createChooser(fileChoose, "choose file");
        startActivityForResult(fileChoose, 1111);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            imgName = data.getData().toString();

            Amplify.Storage.uploadInputStream(
                    data.getData().toString(),
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
    }

}
