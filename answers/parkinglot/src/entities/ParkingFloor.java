package entities;

import vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ConcurrentHashMap<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public ParkingSpot get(Object key) {
                return null;
            }

            @Override
            public ParkingSpot put(String key, ParkingSpot value) {
                return null;
            }

            @Override
            public ParkingSpot remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends ParkingSpot> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public KeySetView<String, ParkingSpot> keySet() {
                return Set.of();
            }

            @Override
            public Collection<ParkingSpot> values() {
                return List.of();
            }

            @Override
            public Set<Entry<String, ParkingSpot>> entrySet() {
                return Set.of();
            }
        };
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
