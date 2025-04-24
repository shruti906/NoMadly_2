package com.example.nomadly;

public class ItineraryItem {
    private String timeOfDay;
    private String placeName;
    private String description;

    public ItineraryItem(String timeOfDay, String placeName, String description) {
        this.timeOfDay = timeOfDay;
        this.placeName = placeName;
        this.description = description;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getDescription() {
        return description;
    }
}
