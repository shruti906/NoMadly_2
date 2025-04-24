package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InboxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbarInbox);
        setSupportActionBar(toolbar);

        // Enable the up button (back arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Set the click listener for the back arrow to navigate to HomeActivity
        toolbar.setNavigationOnClickListener(v -> {
            // Navigate to the HomeActivity
            startActivity(new Intent(InboxActivity.this, HomeActivity.class));
            finish();
        });
    }
}
