package com.example.tripservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String DRIVER_SERVICE_URL = "http://localhost:8081/driver";

    public Trip startTrip(String riderId, String origin, String destination) {
        String driverResponse = restTemplate.postForObject(DRIVER_SERVICE_URL + "/startTrip", null, String.class);

        if (driverResponse == null || driverResponse.startsWith("No drivers")) {
            throw new RuntimeException("No available drivers to start the trip");
        }

        Trip trip = new Trip();
        trip.setDriverId("D001"); // Hardcoded for now
        trip.setRiderId(riderId);
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setStartTime(LocalDateTime.now());

        System.out.println("Trip started at: " + trip.getStartTime());
        return repository.save(trip);
    }

    public Trip endTrip(Long tripId) {
        Trip trip = repository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

        trip.setEndTime(LocalDateTime.now());
        Duration duration = Duration.between(trip.getStartTime(), trip.getEndTime());
        trip.setDuration(duration);
        double minutes = duration.toMinutes();
        double fare = 5.0 + minutes * 2.0;
        trip.setCost(fare);

        return repository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return repository.findAll();
    }

    public Trip getTrip(Long id) {
        return repository.findById(id).orElse(null);
    }
}
