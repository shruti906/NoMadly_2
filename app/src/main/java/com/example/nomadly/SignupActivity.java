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
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

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
                // Check if email already exists
                if (databaseHelper.checkEmailExists(email)) {
                    Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add user to database
                boolean success = databaseHelper.addUser(fullName, email, password, phone, nationality);
                if (success) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginNowText.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> finish());
    }
}