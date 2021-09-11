package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

                Toast.makeText(getApplicationContext(), "Add username Done !", Toast.LENGTH_SHORT).show();

                sharedPreferencesEditor.putString("username", addUserName);
                sharedPreferencesEditor.apply();

                Intent intent = new Intent(SettingsPage.this,MainActivity.class);
                startActivity(intent);


                RadioButton rbb1 = findViewById(R.id.radioButton4);
                RadioButton rbb2 = findViewById(R.id.rb2);
                RadioButton rbb3 = findViewById(R.id.rb3);

                if (rbb1.isChecked()){
                    sharedPreferencesEditor.putString("team", rbb1.getText().toString());
                }else if(rbb2.isChecked()){
                    sharedPreferencesEditor.putString("team", rbb2.getText().toString());
                }else if(rbb3.isChecked()){
                    sharedPreferencesEditor.putString("team", rbb3.getText().toString());
                }
            }


    });

}
}