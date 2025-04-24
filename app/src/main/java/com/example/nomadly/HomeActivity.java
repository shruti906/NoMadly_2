package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnCreatePlan;
    ImageView imgProfile;
    LinearLayout tripCard1, tripCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCreatePlan = findViewById(R.id.btnCreatePlan);
        imgProfile = findViewById(R.id.imgProfile);
        tripCard1 = findViewById(R.id.tripCard1);
        tripCard2 = findViewById(R.id.tripCard2);

        // Navigate to CreatePlanActivity
        btnCreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CreatePlanActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to Trip Details (optional demo)
        tripCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
                startActivity(intent);
            }
        });

        tripCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlanDetailsActivity.class);
                startActivity(intent);
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Optional: Open ProfileActivity
                // startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });
    }
}
