package com.example.nomadly;

public class TripModel {
    String name;
    int imageResId;
    String dateRange;

    public TripModel(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public TripModel(String name, int imageResId, String dateRange) {
        this.name = name;
        this.imageResId = imageResId;
        this.dateRange = dateRange;
    }
}
