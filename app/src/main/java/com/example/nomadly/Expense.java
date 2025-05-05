package com.example.nomadly;

public class Expense {
    private int id;
    private String name;
    private String purpose;
    private int amount;

    public Expense(int id, String name, String purpose, int amount) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getAmount() {
        return amount;
    }
}
