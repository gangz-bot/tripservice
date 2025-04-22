package com.example.DriverService;

public class Driver {
    private String driverId;
    private String name;
    private String carModel;
    private boolean available;

    public Driver(String driverId, String name, String carModel, boolean available) {
        this.driverId = driverId;
        this.name = name;
        this.carModel = carModel;
        this.available = available;
    }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
