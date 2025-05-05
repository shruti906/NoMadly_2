package com.example.nomadly;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseActivity1 extends AppCompatActivity {

    private ExpenseDatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private TextView budgetText, remainingText;
    private Button backButton;

    private final int totalBudget = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        dbHelper = new ExpenseDatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view);
        budgetText = findViewById(R.id.budget_text);
        remainingText = findViewById(R.id.remaining_text);
        backButton = findViewById(R.id.back_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Expense> expenseList = dbHelper.getAllExpenses();
        adapter = new ExpenseAdapter(this, expenseList);
        recyclerView.setAdapter(adapter);

        int totalSpent = 0;
        for (Expense e : expenseList) {
            totalSpent += e.getAmount();
        }

        int remaining = totalBudget - totalSpent;

        budgetText.setText("Budget: ₹" + totalBudget);
        remainingText.setText("Remaining: ₹" + remaining);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}
