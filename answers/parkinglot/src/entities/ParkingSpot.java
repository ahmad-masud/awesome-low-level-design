package entities;

import vehicle.Vehicle;
import vehicle.VehicleSize;

public class ParkingSpot {
    private final VehicleSize spotSize;
    private final String spotId;

    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(VehicleSize spotSize, String spotId) {
        this.spotSize = spotSize;
        this.spotId = spotId;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return getSpotSize().ordinal() >= vehicle.getVehicleSize().ordinal();
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (canFitVehicle(vehicle) && isAvailable()) {
            isOccupied = true;
            parkedVehicle = vehicle;
        }
    }

    public void unParkVehicle() {
        isOccupied = false;
        parkedVehicle = null;
    }
}
