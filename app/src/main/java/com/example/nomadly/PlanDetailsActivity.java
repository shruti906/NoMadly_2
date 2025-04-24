package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlanDetailsActivity extends AppCompatActivity {

    TextView txtTripName, txtDestination, txtStartDate, txtEndDate, txtBudget, txtTripType;
    Button btnEditPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);

        // Initialize views
        txtTripName = findViewById(R.id.txtTripName);
        txtDestination = findViewById(R.id.txtDestination);
        txtStartDate = findViewById(R.id.txtStartDate);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtBudget = findViewById(R.id.txtBudget);
        txtTripType = findViewById(R.id.txtTripType);
        btnEditPlan = findViewById(R.id.btnEditPlan);

        // Get data from intent
        Intent intent = getIntent();
        txtTripName.setText(intent.getStringExtra("tripName"));
        txtDestination.setText(intent.getStringExtra("destination"));
        txtStartDate.setText(intent.getStringExtra("startDate"));
        txtEndDate.setText(intent.getStringExtra("endDate"));
        txtBudget.setText(intent.getStringExtra("budget"));
        txtTripType.setText(intent.getStringExtra("tripType"));

        // Edit Plan Button Click
        btnEditPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(PlanDetailsActivity.this, CreatePlanActivity.class);
                startActivity(editIntent);
            }
        });
    }
}
