package com.example.nomadly;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Insert
    void insertTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("SELECT * FROM trips")
    List<Trip> getAllTrips();

    @Query("SELECT * FROM trips WHERE id = :tripId LIMIT 1")
    Trip getTripById(int tripId);
}
