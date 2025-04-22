package com.example.DriverService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private List<Driver> drivers = new ArrayList<>();

    public DriverController() {
        drivers.add(new Driver("D001", "John Doe", "Toyota Camry", true));
        drivers.add(new Driver("D002", "Jane Smith", "Honda Accord", true));
        drivers.add(new Driver("D003", "Bob Johnson", "Ford Focus", true));
    }

    @PostMapping("/startTrip")
    public String startTrip() {
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                driver.setAvailable(false);
                return "Trip started with driver ID: " + driver.getDriverId() + ", Name: " + driver.getName();
            }
        }
        return "No drivers available right now.";
    }

    @PostMapping("/endTrip")
    public String endTrip() {
        for (Driver driver : drivers) {
            if (!driver.isAvailable()) {
                driver.setAvailable(true);
                return "Driver " + driver.getDriverId() + " is now available.";
            }
        }
        return "No active trips to end.";
    }
}
