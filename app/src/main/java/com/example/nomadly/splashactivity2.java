package com.example.nomadly;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class splashactivity2 extends AppCompatActivity {

    Button signupButton, loginButton;
    SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "NomadlyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);  // 🔹 Added Log In button reference
        ImageView backButton = findViewById(R.id.backButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        backButton.setOnClickListener(view -> finish());

        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(splashactivity2.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(splashactivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
