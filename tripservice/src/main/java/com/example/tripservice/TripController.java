package com.example.tripservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/start")
    public Trip createTrip(@RequestBody Trip trip) {
        if (trip.getOrigin() == null || trip.getDestination() == null || trip.getRiderId() == null) {
            throw new IllegalArgumentException("Origin, destination, and riderId are required");
        }

        return tripService.startTrip(trip.getRiderId(), trip.getOrigin(), trip.getDestination());
    }

    @PutMapping("/end/{id}")
    public Trip endTrip(@PathVariable Long id) {
        return tripService.endTrip(id);
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTrip(id);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        // Optional delete
    }
}
