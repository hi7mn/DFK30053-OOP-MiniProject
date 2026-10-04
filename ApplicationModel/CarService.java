public class CarService extends VehicleService{
    private int engineCapacity; // cc (contoh: 1300, 1500, 2000)
    private boolean isFullService; // true jika servis penuh, false jika servis biasa

    // Constructor[cite: 4]
    public CarService(String serviceId, String customerName, String plateNumber, double basePrice, int engineCapacity, boolean isFullService) {
        super(serviceId, customerName, plateNumber, basePrice); // Memanggil constructor VehicleService[cite: 4]
        this.engineCapacity = engineCapacity;
        this.isFullService = isFullService;
    }

    // Getters and Setters[cite: 4]
    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean isFullService() {
        return isFullService;
    }

    public void setFullService(boolean isFullService) {
        this.isFullService = isFullService;
    }

    // Method Overriding untuk mengira jumlah kos (Polymorphism)[cite: 4]
    @Override
    public double calculateTotalCost() {
        double total = basePrice;

        // Tambah caj jika servis penuh
        if (isFullService) {
            total += 100.0; // Caj servis penuh
        } else {
            total += 40.0;  // Caj tukar minyak hitam biasa
        }

        // Tambah caj berdasarkan cc enjin
        if (engineCapacity > 1600) {
            total += 50.0; // Enjin besar
        }

        // Pengiraan Cukai SST 6%[cite: 4]
        double tax = total * 0.06;
        return total + tax;
    }

    @Override
    public String getServiceDetails() {
        String serviceType = isFullService ? "Servis Penuh" : "Servis Biasa";
        return super.getServiceDetails() + 
               " | Jenis: Kereta (" + engineCapacity + "cc, " + serviceType + ")" + 
               " | Jumlah Keseluruhan (Inc. SST): RM" + String.format("%.2f", calculateTotalCost());
    }
}
