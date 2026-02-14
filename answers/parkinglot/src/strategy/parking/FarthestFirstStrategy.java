package strategy.parking;

import vehicle.Vehicle;
import entities.ParkingSpot;
import entities.ParkingFloor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarthestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloorList, Vehicle vehicle) {
        Optional<ParkingSpot> farthestFirstParkingSpot = Optional.empty();
        Collections.reverse(parkingFloorList);

        for (ParkingFloor parkingFloor : parkingFloorList) {
            farthestFirstParkingSpot = parkingFloor.findAvailableSpot(vehicle);

            if (farthestFirstParkingSpot.isPresent()) {
                break;
            }
        }

        return farthestFirstParkingSpot;
    }
}
