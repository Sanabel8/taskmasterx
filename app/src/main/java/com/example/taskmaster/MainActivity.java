package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);


            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }


    public void logIn() {
        Amplify.Auth.signInWithWebUI(
                MainActivity.this,
                result -> {
                    Log.i("AuthQuickStart", result.toString());
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }

    //to check the state for login
    public String checkLoginStatus() {
        String username = "";
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifyQuickstart", String.valueOf(result.isSignedIn()));
                    if (!result.isSignedIn()) {
                        logIn();
                    }
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        username = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        Button logOut = (Button) findViewById(R.id.logOutBtn);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> {
                            logIn();
                            Log.i("AuthQuickstart", "Signed out successfully");
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

        Button btnAddTask = (Button) findViewById(R.id.addTaskBtn);
        btnAddTask.setText("add Task");
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTask);
            }
        });

        Button settingbtn = findViewById(R.id.settitngbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this, SettingsPage.class);
                startActivity(goToSettingPage);
            }
        });

//        TaskDb taskDB = Room.databaseBuilder(getApplicationContext(), TaskDb.class, "tasks").allowMainThreadQueries().build();

//        List<Task> infoForList ;
//        TaskDao taskDao;
//        taskDao = taskDB.taskDao();
//        infoForList = taskDao.getAll();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String team = sharedPreferences.getString("team", "team");


        RecyclerView allTASKsRecuclerView = findViewById(R.id.recycleViewListtask);
        List<Team> teams = new ArrayList<>();
        List<Task> allTasksData = new ArrayList<>();

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                allTASKsRecuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        allTASKsRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recyclerView
        allTASKsRecuclerView.setAdapter(new TaskAdapter(allTasksData));


//
//        Amplify.API.query(
//                ModelQuery.list(Task.class),
//                response -> {
//                    for (Task task : response.getData()) {
//                        Log.i("MyAmplifyApp", task.getBody());
//                        allTasksData.add(task);
//                    }
//                    handler.sendEmptyMessage(1);
//                    Log.i("MyAmplifyApp", "outsoid the loop");
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team teamo : response.getData()) {
                        Log.i("MyAmplifyApp", teamo.getTeamName());
                        Log.i("MyAmplifyApp", teamo.getId());

                        ///add new data to array
                        teams.add(teamo);
                    }
                    for (int i = 0; i < teams.size(); i++) {
                        if (teams.get(i).getTeamName().equals(team)) {
                            allTasksData.addAll(teams.get(i).getTasks());
                            break;
                        }
                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outside the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = checkLoginStatus();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String nameFromSetting = sharedPreferences.getString("username", "sanbael");
        String team = sharedPreferences.getString("team", "team");

        TextView nameView = findViewById(R.id.hiUserName);
        nameView.setText("hello" + username);

        TextView teamName = findViewById(R.id.teamName);
        teamName.setText(team);

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