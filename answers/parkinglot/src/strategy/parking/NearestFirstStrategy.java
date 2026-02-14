package strategy.parking;

import vehicle.Vehicle;
import entities.ParkingSpot;
import entities.ParkingFloor;

import java.util.List;
import java.util.Optional;

public class NearestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloorList, Vehicle vehicle) {
        Optional<ParkingSpot> firstAvailableParkingSpot = Optional.empty();

        for (ParkingFloor parkingFloor : parkingFloorList) {
            firstAvailableParkingSpot = parkingFloor.findAvailableSpot(vehicle);

            if (firstAvailableParkingSpot.isPresent()) {
                break;
            }
        }

        return firstAvailableParkingSpot;
    }
}
