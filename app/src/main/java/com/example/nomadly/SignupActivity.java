package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText fullNameEditText, emailEditText, passwordEditText, phoneEditText, nationalityEditText;
    Button createAccountButton;
    TextView loginNowText;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        nationalityEditText = findViewById(R.id.nationalityEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
        loginNowText = findViewById(R.id.loginNowText);
        backButton = findViewById(R.id.backButton);

        createAccountButton.setOnClickListener(view -> {
            String fullName = fullNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String nationality = nationalityEditText.getText().toString();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()
                    || phone.isEmpty() || nationality.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginNowText.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> finish());
    }
}