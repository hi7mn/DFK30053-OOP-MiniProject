package application.model;

/**
 * Child class: servis tukar tayar.
 * Kos = (bilangan tayar x harga satu tayar) + upah tetap.
 */
public class TyreService extends Service {

    private static final double LABOUR_CHARGE = 20.0;

    private int tyreCount;
    private double pricePerTyre;

    public TyreService(int id, String plateNumber, String ownerName,
                       int tyreCount, double pricePerTyre) {
        super(id, plateNumber, ownerName);
        this.tyreCount = tyreCount;
        this.pricePerTyre = pricePerTyre;
    }

    public int getTyreCount() {
        return tyreCount;
    }

    public void setTyreCount(int tyreCount) {
        this.tyreCount = tyreCount;
    }

    public double getPricePerTyre() {
        return pricePerTyre;
    }

    public void setPricePerTyre(double pricePerTyre) {
        this.pricePerTyre = pricePerTyre;
    }

    @Override
    public double calculateCost() {
        return (tyreCount * pricePerTyre) + LABOUR_CHARGE;
    }

    @Override
    public String getServiceType() {
        return "Servis Tayar";
    }
}
