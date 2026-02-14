package strategy.parking;

import vehicle.Vehicle;
import entities.ParkingSpot;
import entities.ParkingFloor;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloorList, Vehicle vehicle) {
        Optional<ParkingSpot> bestFitParkingSpot = Optional.empty();

        for (ParkingFloor parkingFloor : parkingFloorList) {
            Optional<ParkingSpot> currentParkingSpot = parkingFloor.findAvailableSpot(vehicle);

            if (currentParkingSpot.isPresent()) {
                if (bestFitParkingSpot.isEmpty()) {
                    bestFitParkingSpot = currentParkingSpot;
                } else {
                    if (currentParkingSpot.get().getSpotSize().ordinal() < bestFitParkingSpot.get().getSpotSize().ordinal()) {
                        bestFitParkingSpot = currentParkingSpot;
                    }
                }
            }
        }

        return bestFitParkingSpot;
    }
}
