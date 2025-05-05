package com.example.nomadly;

import android.content.Context;

import androidx.room.Room;

public class TripDatabaseClient {
    private static TripDatabaseClient instance;
    private final AppDatabase appDatabase;

    private TripDatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "TripDB")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized TripDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new TripDatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
