package com.example.tripservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository repository;

    public Trip startTrip(String driverId, String riderId) {
        Trip trip = new Trip();
        trip.setDriverId(driverId);
        trip.setRiderId(riderId);
        trip.setStartTime(LocalDateTime.now());
        return repository.save(trip);
    }

    public Trip endTrip(Long tripId) {
        Trip trip = repository.findById(tripId).orElseThrow();
        trip.setEndTime(LocalDateTime.now());

        Duration duration = Duration.between(trip.getStartTime(), trip.getEndTime());
        double minutes = duration.toMinutes();
        double fare = 5.0 + minutes * 2.0; // base fare + per minute

        trip.setCost(fare);
        return repository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return repository.findAll();
    }
}
