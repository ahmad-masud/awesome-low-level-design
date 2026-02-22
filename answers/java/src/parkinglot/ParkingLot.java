import entities.ParkingFloor;
import entities.ParkingSpot;
import entities.ParkingTicket;
import strategy.fee.FeeStrategy;
import strategy.fee.FlatRateFeeStrategy;
import strategy.parking.BestFitStrategy;
import strategy.parking.ParkingStrategy;
import vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private ParkingStrategy parkingStrategy;
    private static ParkingLot instance;
    private final List<ParkingFloor> parkingFloors;
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy feeStrategy;

    public ParkingLot() {
        this(new BestFitStrategy(), new FlatRateFeeStrategy());
    }

    public ParkingLot(ParkingStrategy parkingStrategy, FeeStrategy feeStrategy) {
        this.parkingStrategy = parkingStrategy;
        this.feeStrategy = feeStrategy;
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingFloors = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor parkingFloor) {
        parkingFloors.add(parkingFloor);
    }

    public void setFeeStrategy (FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> parkingSpotOpt = parkingStrategy.findSpot(parkingFloors, vehicle);

        if (parkingSpotOpt.isPresent()) {
            ParkingSpot parkingSpot = parkingSpotOpt.get();
            parkingSpot.parkVehicle(vehicle);
            ParkingTicket parkingTicket = new ParkingTicket(vehicle, parkingSpot);
            activeTickets.put(vehicle.getLicenseNumber(), parkingTicket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(), parkingSpot.getSpotId(), parkingTicket.getTicketId());
            return Optional.of(parkingTicket);
        }

        System.out.println("No available spot for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket parkingTicket = activeTickets.remove(licenseNumber);

        if (parkingTicket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        parkingTicket.getSpot().unParkVehicle();
        parkingTicket.setExitTimeStamp();

        Double parkingFee = feeStrategy.calculateFee(parkingTicket);

        return Optional.of(parkingFee);
    }
}
