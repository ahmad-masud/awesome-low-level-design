package vehicle;

public abstract class Vehicle {
    private final VehicleSize size;
    private final String licenseNumber;

    public Vehicle(VehicleSize size, String licenseNumber) {
        this.size = size;
        this.licenseNumber = licenseNumber;
    }

    public VehicleSize getVehicleSize() {
        return size;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}