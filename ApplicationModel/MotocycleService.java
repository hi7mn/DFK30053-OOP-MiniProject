public class MotocycleService extends VehicleService{
    // Atribut khas untuk Motosikal[cite: 4]
    private String engineType; // "2-Stroke" atau "4-Stroke"
    private boolean includeChainLube; // true jika tambah pelincir rantai

    // Constructor[cite: 4]
    public MotorcycleService(String serviceId, String customerName, String plateNumber, double basePrice, String engineType, boolean includeChainLube) {
        super(serviceId, customerName, plateNumber, basePrice); // Memanggil constructor VehicleService[cite: 4]
        this.engineType = engineType;
        this.includeChainLube = includeChainLube;
    }

    // Getters and Setters[cite: 4]
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public boolean isIncludeChainLube() {
        return includeChainLube;
    }

    public void setIncludeChainLube(boolean includeChainLube) {
        this.includeChainLube = includeChainLube;
    }

    // Method Overriding untuk mengira jumlah kos (Polymorphism)[cite: 4]
    @Override
    public double calculateTotalCost() {
        double total = basePrice;

        // Tambah caj untuk jenis enjin
        if (engineType.equalsIgnoreCase("2-Stroke")) {
            total += 15.0; // Caj minyak 2T
        } else {
            total += 10.0; // Caj minyak enjin 4T
        }

        // Tambah caj pelincir rantai jika diminta
        if (includeChainLube) {
            total += 10.0;
        }

        // Pengiraan Cukai SST 6%[cite: 4]
        double tax = total * 0.06;
        return total + tax;
    }

    @Override
    public String getServiceDetails() {
        String lubeStatus = includeChainLube ? "Ya" : "Tidak";
        return super.getServiceDetails() + 
               " | Jenis: Motosikal (" + engineType + ", Lube Rantai: " + lubeStatus + ")" + 
               " | Jumlah Keseluruhan (Inc. SST): RM" + String.format("%.2f", calculateTotalCost());
    }
}
