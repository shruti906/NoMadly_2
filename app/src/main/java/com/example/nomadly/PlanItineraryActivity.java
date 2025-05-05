package com.example.nomadly;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PlanItineraryActivity extends AppCompatActivity {

    // Activity Section
    EditText edtActivityName, edtActivityTime;
    Button btnAddActivity;
    LinearLayout layoutActivityList;

    // Itinerary Section
    Button btnAddItinerary;
    LinearLayout layoutItineraryList;

    // Food Recommendations
    EditText edtFoodItem;
    Button btnAddFood;
    LinearLayout layoutFoodList;

    // Packing Checklist
    EditText edtPackingItem;
    Button btnAddPacking;
    LinearLayout layoutPackingList;

    String selectedTimeOfDay = "Morning"; // Default to Morning

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_itinerary);

        // Initialize views
        edtActivityName = findViewById(R.id.edtActivityName);
        edtActivityTime = findViewById(R.id.edtActivityTime);
        btnAddActivity = findViewById(R.id.btnAddActivity);
        layoutActivityList = findViewById(R.id.layoutActivityList);

        btnAddItinerary = findViewById(R.id.btnAddItinerary);
        layoutItineraryList = findViewById(R.id.layoutItineraryList);

        edtFoodItem = findViewById(R.id.edtFoodItem);
        btnAddFood = findViewById(R.id.btnAddFood);
        layoutFoodList = findViewById(R.id.layoutFoodList);

        edtPackingItem = findViewById(R.id.edtPackingItem);
        btnAddPacking = findViewById(R.id.btnAddPacking);
        layoutPackingList = findViewById(R.id.layoutPackingList);


        // Add activity button click
        btnAddActivity.setOnClickListener(v -> addItem(layoutActivityList, edtActivityName, edtActivityTime));

        // Add itinerary button click (opens modal)
        btnAddItinerary.setOnClickListener(v -> showAddPlaceDialog());

        // Add food button click
        btnAddFood.setOnClickListener(v -> addItem(layoutFoodList, edtFoodItem, null));

        // Add packing button click
        btnAddPacking.setOnClickListener(v -> addItem(layoutPackingList, edtPackingItem, null));

        Button btnSavePlan = findViewById(R.id.btnSavePlan);
        btnSavePlan.setOnClickListener(v -> {
            // Navigate to AddPlanIntroActivity
            startActivity(new Intent(PlanItineraryActivity.this, AddPlanIntroActivity.class));
        });
    }

    // Add items dynamically with delete button
    private void addItem(LinearLayout layout, EditText edtItem, EditText edtTime) {
        String itemName = edtItem.getText().toString().trim();
        String itemTime = edtTime != null ? edtTime.getText().toString().trim() : null;

        if (!itemName.isEmpty()) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_activity, layout, false);
            TextView txtActivityName = itemView.findViewById(R.id.txtActivityName);
            TextView txtActivityTime = itemView.findViewById(R.id.txtActivityTime);
            ImageButton btnDeleteActivity = itemView.findViewById(R.id.btnDeleteActivity);

            txtActivityName.setText(itemName);

            if (itemTime != null && !itemTime.isEmpty()) {
                txtActivityTime.setText(itemTime);
                txtActivityTime.setVisibility(View.VISIBLE);
            } else {
                txtActivityTime.setVisibility(View.GONE);
            }

            // Delete the item when the button is clicked
            btnDeleteActivity.setOnClickListener(v -> {
                layout.removeView(itemView);
                Toast.makeText(this, "Item Removed!", Toast.LENGTH_SHORT).show();
            });

            layout.addView(itemView);
            edtItem.setText("");
            if (edtTime != null) edtTime.setText("");
            Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter valid information!", Toast.LENGTH_SHORT).show();
        }
    }

    // Show modal to add place with options
    private void showAddPlaceDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_place);
        dialog.setCancelable(false);

        // Get dialog views
        Button btnMorning = dialog.findViewById(R.id.btnMorning);
        Button btnAfternoon = dialog.findViewById(R.id.btnAfternoon);
        Button btnNight = dialog.findViewById(R.id.btnNight);
        EditText edtPlaceName = dialog.findViewById(R.id.edtPlaceName);
        EditText edtDescription = dialog.findViewById(R.id.edtDescription);
        Button btnAddPlace = dialog.findViewById(R.id.btnAddPlace);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        // Set default time selection
        selectTime(btnMorning, btnAfternoon, btnNight, "Morning");

        // Handle time selection
        btnMorning.setOnClickListener(v -> selectTime(btnMorning, btnAfternoon, btnNight, "Morning"));
        btnAfternoon.setOnClickListener(v -> selectTime(btnMorning, btnAfternoon, btnNight, "Afternoon"));
        btnNight.setOnClickListener(v -> selectTime(btnMorning, btnAfternoon, btnNight, "Night"));

        // Add place button click
        btnAddPlace.setOnClickListener(v -> {
            String placeName = edtPlaceName.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();
            if (!placeName.isEmpty()) {
                addPlaceToList(placeName, description, selectedTimeOfDay);
                edtPlaceName.setText("");
                edtDescription.setText("");
                Toast.makeText(this, "Place Added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Enter a valid place name!", Toast.LENGTH_SHORT).show();
            }
        });

        // Save and close dialog
        btnSave.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(this, "Itinerary Saved!", Toast.LENGTH_SHORT).show();
        });

        // Cancel and close dialog
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Set dialog width dynamically
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        dialog.show();
    }

    // Handle time selection and button state
    private void selectTime(Button btnMorning, Button btnAfternoon, Button btnNight, String time) {
        btnMorning.setBackgroundResource(time.equals("Morning") ? R.drawable.button_selected : R.drawable.button_unselected);
        btnAfternoon.setBackgroundResource(time.equals("Afternoon") ? R.drawable.button_selected : R.drawable.button_unselected);
        btnNight.setBackgroundResource(time.equals("Night") ? R.drawable.button_selected : R.drawable.button_unselected);
        selectedTimeOfDay = time;
    }

    // Add place dynamically to the list with time of day
    private void addPlaceToList(String placeName, String description, String time) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_activity, layoutItineraryList, false);
        TextView txtActivityName = itemView.findViewById(R.id.txtActivityName);
        TextView txtActivityTime = itemView.findViewById(R.id.txtActivityTime);
        ImageButton btnDeleteActivity = itemView.findViewById(R.id.btnDeleteActivity);

        txtActivityName.setText(placeName + (description.isEmpty() ? "" : " - " + description));
        txtActivityTime.setText(time);
        txtActivityTime.setVisibility(View.VISIBLE);

        btnDeleteActivity.setOnClickListener(v -> {
            layoutItineraryList.removeView(itemView);
            Toast.makeText(this, "Place Removed!", Toast.LENGTH_SHORT).show();
        });

        layoutItineraryList.addView(itemView);
    }
}