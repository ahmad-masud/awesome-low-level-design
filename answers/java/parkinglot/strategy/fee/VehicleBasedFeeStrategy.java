package strategy.fee;

import vehicle.VehicleSize;
import java.util.Map;
import entities.ParkingTicket;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private static final Map<VehicleSize, Double> HOURLY_RATES = Map.of(
        VehicleSize.SMALL, 10.0,
        VehicleSize.MEDIUM, 20.0,
        VehicleSize.LARGE, 30.0
    );

    public double calculateFee(ParkingTicket parkingTicket) {
        VehicleSize vehicleSize = parkingTicket.getVehicle().getVehicleSize();
        long duration = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * HOURLY_RATES.get(vehicleSize);
    }
}
