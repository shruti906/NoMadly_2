package com.example.nomadly;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private ExpenseDatabaseHelper dbHelper;
    private TextView budgetText, remainingText;
    private Button backButton;

    private int totalBudget = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        recyclerView = findViewById(R.id.recycler_view);
        budgetText = findViewById(R.id.budget_text);
        remainingText = findViewById(R.id.remaining_text);
        backButton = findViewById(R.id.back_button);

        dbHelper = new ExpenseDatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(this, dbHelper.getAllExpenses());
        recyclerView.setAdapter(adapter);

        updateBudgetDisplay();

        backButton.setOnClickListener(v -> finish());
    }

    private void updateBudgetDisplay() {
        int totalSpent = 0;
        for (Expense e : dbHelper.getAllExpenses()) {
            totalSpent += e.getAmount();
        }

        budgetText.setText(getString(R.string.budget_display, totalBudget));
        remainingText.setText(getString(R.string.remaining_display, totalBudget - totalSpent));
    }

}
