package com.example.tripservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip updatedTrip) {
        return tripRepository.findById(id).map(trip -> {
            trip.setOrigin(updatedTrip.getOrigin());
            trip.setDestination(updatedTrip.getDestination());
            trip.setDuration(updatedTrip.getDuration());
            trip.setCost(updatedTrip.getCost());
            return tripRepository.save(trip);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripRepository.deleteById(id);
    }
}
