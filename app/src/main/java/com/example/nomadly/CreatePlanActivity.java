package com.example.nomadly;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CreatePlanActivity extends AppCompatActivity {

    EditText edtTripName, edtDestination, edtStartDate, edtEndDate, edtBudget;
    RadioGroup rgTripType;
    Button btnSubmitPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        // Initialize views
        edtTripName = findViewById(R.id.edtTripName);
        edtDestination = findViewById(R.id.edtDestination);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtBudget = findViewById(R.id.edtBudget);
        rgTripType = findViewById(R.id.rgTripType);
        btnSubmitPlan = findViewById(R.id.btnSubmitPlan);

        // Date picker for Start Date
        edtStartDate.setOnClickListener(v -> showDatePicker(edtStartDate));

        // Date picker for End Date
        edtEndDate.setOnClickListener(v -> showDatePicker(edtEndDate));

        // Submit Plan Button Click
        btnSubmitPlan.setOnClickListener(v -> {
            if (validateInputs()) {
                savePlanData();
            }
        });
    }

    // Show date picker
    private void showDatePicker(final EditText dateField) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreatePlanActivity.this,
                (view, year1, month1, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    dateField.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    // Validate user inputs
    private boolean validateInputs() {
        if (edtTripName.getText().toString().isEmpty() ||
                edtDestination.getText().toString().isEmpty() ||
                edtStartDate.getText().toString().isEmpty() ||
                edtEndDate.getText().toString().isEmpty() ||
                edtBudget.getText().toString().isEmpty() ||
                rgTripType.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Save plan data and navigate to PlanItineraryActivity
    private void savePlanData() {
        // Show confirmation message
        Toast.makeText(this, "Plan Created Successfully!", Toast.LENGTH_SHORT).show();

        // Navigate to PlanItineraryActivity
        Intent intent = new Intent(CreatePlanActivity.this, PlanItineraryActivity.class);
        startActivity(intent);
        finish();
    }
}
