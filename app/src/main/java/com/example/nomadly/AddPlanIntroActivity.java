package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddPlanIntroActivity extends AppCompatActivity {

    EditText edtSearchTrips;
    Button btnCreatePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_intro);

        // Initialize views
        edtSearchTrips = findViewById(R.id.edtSearchTrips);
        btnCreatePlan = findViewById(R.id.btnCreatePlan);

        // Handle "Create Plan" button click
        btnCreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlanIntroActivity.this, CreatePlanActivity.class);
                startActivity(intent);
            }
        });
    }
}
