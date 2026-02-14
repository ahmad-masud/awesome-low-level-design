package strategy.parking;

import entities.ParkingSpot;
import entities.ParkingFloor;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloorList, Vehicle vehicle);
}
