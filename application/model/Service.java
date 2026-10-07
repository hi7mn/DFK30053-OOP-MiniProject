package application.model;

/**
 * Abstract class (parent) untuk semua jenis servis kenderaan.
 * Attribute dibuat private (encapsulation) dan diakses melalui getter/setter.
 */
public abstract class Service {

    private int id;
    private String plateNumber;
    private String ownerName;

    // Constructor
    public Service(int id, String plateNumber, String ownerName) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // Abstract method: setiap child class mesti override (polymorphism)
    public abstract double calculateCost();

    public abstract String getServiceType();
}
