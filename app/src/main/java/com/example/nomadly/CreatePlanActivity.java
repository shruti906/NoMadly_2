package com.example.nomadly;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    Trip tripToEdit = null;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        edtTripName = findViewById(R.id.edtTripName);
        edtDestination = findViewById(R.id.edtDestination);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtBudget = findViewById(R.id.edtBudget);
        rgTripType = findViewById(R.id.rgTripType);
        btnSubmitPlan = findViewById(R.id.btnSubmitPlan);

        edtStartDate.setOnClickListener(v -> showDatePicker(edtStartDate));
        edtEndDate.setOnClickListener(v -> showDatePicker(edtEndDate));

        int tripId = getIntent().getIntExtra("tripId", -1);
        if (tripId != -1) {
            isEditMode = true;
            loadTripFromDatabase(tripId);
        }

        btnSubmitPlan.setOnClickListener(v -> {
            if (validateInputs()) {
                if (isEditMode) updateTrip();
                else insertTrip();
            }
        });
    }

    private void loadTripFromDatabase(int id) {
        new Thread(() -> {
            TripDao dao = TripDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().tripDao();
            tripToEdit = dao.getTripById(id);

            runOnUiThread(() -> {
                if (tripToEdit != null) {
                    edtTripName.setText(tripToEdit.tripName);
                    edtDestination.setText(tripToEdit.destination);
                    edtStartDate.setText(tripToEdit.startDate);
                    edtEndDate.setText(tripToEdit.endDate);
                    edtBudget.setText(String.valueOf(tripToEdit.budget));
                    setRadioSelection(tripToEdit.tripType);
                }
            });
        }).start();
    }

    private void setRadioSelection(String tripType) {
        for (int i = 0; i < rgTripType.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rgTripType.getChildAt(i);
            if (rb.getText().toString().equalsIgnoreCase(tripType)) {
                rb.setChecked(true);
                break;
            }
        }
    }

    private void showDatePicker(final EditText dateField) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    dateField.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

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

    private void insertTrip() {
        Trip trip = new Trip(
                edtTripName.getText().toString(),
                edtDestination.getText().toString(),
                edtStartDate.getText().toString(),
                edtEndDate.getText().toString(),
                Double.parseDouble(edtBudget.getText().toString()),
                ((RadioButton) findViewById(rgTripType.getCheckedRadioButtonId())).getText().toString()
        );

        new Thread(() -> {
            TripDao dao = TripDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().tripDao();
            dao.insertTrip(trip);
            runOnUiThread(() -> {
                Toast.makeText(this, "Plan Created Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, PlanItineraryActivity.class));
                finish();
            });
        }).start();
    }

    private void updateTrip() {
        tripToEdit.tripName = edtTripName.getText().toString();
        tripToEdit.destination = edtDestination.getText().toString();
        tripToEdit.startDate = edtStartDate.getText().toString();
        tripToEdit.endDate = edtEndDate.getText().toString();
        tripToEdit.budget = Double.parseDouble(edtBudget.getText().toString());
        tripToEdit.tripType = ((RadioButton) findViewById(rgTripType.getCheckedRadioButtonId())).getText().toString();

        new Thread(() -> {
            TripDao dao = TripDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().tripDao();
            dao.updateTrip(tripToEdit);
            runOnUiThread(() -> {
                Toast.makeText(this, "Plan Updated Successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
