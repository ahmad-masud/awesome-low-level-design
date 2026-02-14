package entities;

import vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final ParkingSpot parkingSpot;
    private final String ticketId;
    private final long entryTimeStamp;
    private final Vehicle vehicle;
    private long exitTimeStamp;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.parkingSpot = spot;
        this.entryTimeStamp = new Date().getTime();
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return parkingSpot; }
    public long getEntryTimestamp() { return entryTimeStamp; }
    public long getExitTimestamp() { return exitTimeStamp; }

    public void setExitTimeStamp() {
        exitTimeStamp = new Date().getTime();
    }
}
