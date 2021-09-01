package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        Button storeUserName = findViewById(R.id.storebtn);
        storeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

                EditText instructorNameField = findViewById(R.id.addedUserName);
                String addUserName = instructorNameField.getText().toString();

                sharedPreferencesEditor.putString("userName", addUserName);
                sharedPreferencesEditor.apply();

            }

    });

}
}