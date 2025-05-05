package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddPlanIntroActivity extends AppCompatActivity {

    LinearLayout btnCreatePlanContainer, tripListContainer;
    TextView txtNoPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_intro);

        btnCreatePlanContainer = findViewById(R.id.btnCreatePlanContainer);
        tripListContainer = findViewById(R.id.tripListContainer);
        txtNoPlans = findViewById(R.id.txtNoPlans);

        // Create Plan Button Click Listener
        btnCreatePlanContainer.setOnClickListener(v -> {
            Intent intent = new Intent(AddPlanIntroActivity.this, CreatePlanActivity.class);
            startActivity(intent);
        });

        // Load trips from the database
        loadTripsFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTripsFromDatabase();  // Reload trips when returning to this activity
    }

    private void loadTripsFromDatabase() {
        new Thread(() -> {
            TripDao dao = TripDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().tripDao();
            List<Trip> tripList = dao.getAllTrips();

            runOnUiThread(() -> {
                if (tripList.isEmpty()) {
                    txtNoPlans.setVisibility(View.VISIBLE);
                    tripListContainer.setVisibility(View.GONE);
                } else {
                    txtNoPlans.setVisibility(View.GONE);
                    tripListContainer.setVisibility(View.VISIBLE);

                    // Clear previous views
                    tripListContainer.removeAllViews();

                    for (Trip trip : tripList) {
                        LinearLayout tripItem = new LinearLayout(this);
                        tripItem.setOrientation(LinearLayout.VERTICAL);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, 24); // space between cards
                        tripItem.setLayoutParams(params);
                        tripItem.setPadding(32, 32, 32, 32);
                        tripItem.setBackground(getDrawable(R.drawable.card1));
                        tripItem.setClickable(true);
                        tripItem.setFocusable(true);

                        TextView title = new TextView(this);
                        title.setText(trip.tripName);
                        title.setTextSize(20);
                        title.setTextColor(getResources().getColor(android.R.color.black));
                        title.setTypeface(null, android.graphics.Typeface.BOLD);

                        TextView destination = new TextView(this);
                        destination.setText("Destination: " + trip.destination);
                        destination.setTextSize(16);
                        destination.setTextColor(getResources().getColor(android.R.color.darker_gray));

                        // Add more fields if needed (like trip.startDate or duration)

                        tripItem.addView(title);
                        tripItem.addView(destination);

                        tripItem.setOnClickListener(v -> {
                            Intent intent = new Intent(AddPlanIntroActivity.this, PlanDetailsActivity.class);
                            intent.putExtra("tripId", trip.id);
                            startActivity(intent);
                        });

                        tripListContainer.addView(tripItem);
                    }

                }
            });
        }).start();
    }
}
