package com.example.nomadly;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trips")
public class Trip {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String tripName;
    public String destination;
    public String startDate;
    public String endDate;
    public double budget;
    public String tripType;

    public Trip(String tripName, String destination, String startDate, String endDate, double budget, String tripType) {
        this.tripName = tripName;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.tripType = tripType;
    }
}
