package com.example.nomadly;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExpenseTrackerActivity extends AppCompatActivity {

    ListView lvExpenses;
    Button btnAddExpense;
    ArrayList<String> expenseList;
    ArrayAdapter<String> expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        // Initialize views
        lvExpenses = findViewById(R.id.lvExpenses);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        // Setup ListView
        expenseList = new ArrayList<>();
        expenseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        lvExpenses.setAdapter(expenseAdapter);

        // Add Expense Button Click
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExpenseDialog();
            }
        });
    }

    // Show Add Expense Dialog
    private void showAddExpenseDialog() {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Expense")
                .setMessage("Enter expense details:")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String expense = input.getText().toString().trim();
                        if (!expense.isEmpty()) {
                            expenseList.add(expense);
                            expenseAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
