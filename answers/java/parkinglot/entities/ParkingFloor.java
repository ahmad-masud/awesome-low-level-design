package entities;

import vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ConcurrentHashMap<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getSpotId(), parkingSpot);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        return parkingSpots.values().stream()
                .filter(spot -> spot.isAvailable() && spot.canFitVehicle(vehicle)).min(Comparator.comparing(ParkingSpot::getSpotSize));
    }

    public void displayAvailability() {
        System.out.printf("--- Floor %d Availability ---\n", floorNumber);

        parkingSpots.forEach((spotId, parkingSpot) -> {
            if (parkingSpot.isAvailable()) {
                System.out.printf("<%s>",spotId);
            } else {
                System.out.print("<X>");
            }
        });
    }


}
