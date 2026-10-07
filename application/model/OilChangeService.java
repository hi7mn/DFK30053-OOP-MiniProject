package application.model;

/**
 * Child class: servis tukar minyak enjin.
 * Kos = (kuantiti minyak x harga seliter) + upah tetap.
 */
public class OilChangeService extends Service {

    private static final double LABOUR_CHARGE = 30.0;

    private double oilLiters;
    private double pricePerLiter;

    public OilChangeService(int id, String plateNumber, String ownerName,
                            double oilLiters, double pricePerLiter) {
        super(id, plateNumber, ownerName);
        this.oilLiters = oilLiters;
        this.pricePerLiter = pricePerLiter;
    }

    public double getOilLiters() {
        return oilLiters;
    }

    public void setOilLiters(double oilLiters) {
        this.oilLiters = oilLiters;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    @Override
    public double calculateCost() {
        return (oilLiters * pricePerLiter) + LABOUR_CHARGE;
    }

    @Override
    public String getServiceType() {
        return "Tukar Minyak";
    }
}
