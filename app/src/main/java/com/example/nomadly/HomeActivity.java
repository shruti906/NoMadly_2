package com.example.nomadly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Button btnCreatePlan;
    ImageView imgProfile;
    LinearLayout tripCard1, tripCard2, tripCard3, pastTrip1, pastTrip2;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCreatePlan = findViewById(R.id.btnCreatePlan);
        imgProfile = findViewById(R.id.imgProfile);
        tripCard1 = findViewById(R.id.tripCard1);
        tripCard2 = findViewById(R.id.tripCard2);
        tripCard3 = findViewById(R.id.tripCard3);
        pastTrip1 = findViewById(R.id.pastTrip1);
        pastTrip2 = findViewById(R.id.pastTrip2);

        btnCreatePlan.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddPlanIntroActivity.class);
            startActivity(intent);
        });

        tripCard1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
            startActivity(intent);
        });

        tripCard2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
            startActivity(intent);
        });

        tripCard3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
            startActivity(intent);
        });

        pastTrip1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
            startActivity(intent);
        });

        pastTrip2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
            startActivity(intent);
        });

        imgProfile.setOnClickListener(v -> {
            // Optional: Open ProfileActivity
        });
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_explore) {
                startActivity(new Intent(HomeActivity.this, ExploreActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(HomeActivity.this, PlanDetailsActivity.class));
                return true;
            } else if (id == R.id.nav_inbox) {
                startActivity(new Intent(HomeActivity.this, InboxActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                return true;
            }

            return false;
        });
    }
}
