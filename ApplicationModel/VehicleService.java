public class VehicleService {
    // Encapsulation: Access modifier protected supaya boleh dicapai oleh subclass
    protected String serviceId;
    protected String customerName;
    protected String plateNumber;
    protected double basePrice;

    // Constructor
    public VehicleService(String serviceId, String customerName, String plateNumber, double basePrice) {
        this.serviceId = serviceId;
        this.customerName = customerName;
        this.plateNumber = plateNumber;
        this.basePrice = basePrice;
    }

    // Abstract method untuk dilaksanakan oleh subclass (Polymorphism)[cite: 4]
    public abstract double calculateTotalCost();

    // Getters and Setters[cite: 4]
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    // Kaedah memaparkan maklumat asas servis
    public String getServiceDetails() {
        return "ID Servis: " + serviceId + 
               " | Pelanggan: " + customerName + 
               " | Plat: " + plateNumber + 
               " | Kos Asas: RM" + String.format("%.2f", basePrice);
    }
}
