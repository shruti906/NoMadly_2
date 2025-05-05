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

    Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);

        txtTripName = findViewById(R.id.txtTripName);
        txtDestination = findViewById(R.id.txtDestination);
        txtStartDate = findViewById(R.id.txtStartDate);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtBudget = findViewById(R.id.txtBudget);
        txtTripType = findViewById(R.id.txtTripType);
        btnEditPlan = findViewById(R.id.btnEditPlan);

        int tripId = getIntent().getIntExtra("tripId", -1);
        if (tripId != -1) {
            fetchTripFromDatabase(tripId);
        }
    }

    private void fetchTripFromDatabase(int id) {
        new Thread(() -> {
            TripDao dao = TripDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().tripDao();
            trip = dao.getTripById(id);

            runOnUiThread(() -> {
                if (trip != null) {
                    txtTripName.setText(validateText(trip.tripName, "No trip name available"));
                    txtDestination.setText("Destination: " + validateText(trip.destination, "No destination available"));
                    txtStartDate.setText("Start Date: " + validateText(trip.startDate, "No start date available"));
                    txtEndDate.setText("End Date: " + validateText(trip.endDate, "No end date available"));
                    txtBudget.setText("Budget: ₹" + validateText(String.format("%.2f", trip.budget), "No budget available"));
                    txtTripType.setText("Trip Type: " + validateText(trip.tripType, "No trip type available"));

                    btnEditPlan.setOnClickListener(v -> {
                        Intent editIntent = new Intent(PlanDetailsActivity.this, CreatePlanActivity.class);
                        editIntent.putExtra("tripId", trip.id); // pass id for editing
                        startActivity(editIntent);
                    });
                }
            });
        }).start();
    }

    private String validateText(String value, String fallback) {
        return (value != null && !value.trim().isEmpty()) ? value : fallback;
    }
}
