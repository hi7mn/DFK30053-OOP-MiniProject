import java.util.ArrayList;

class VehicleService {
    private String plateNumber;
    private String serviceType;
    private String customerName;

    public VehicleService(String plateNumber, String serviceType, String customerName) {
        this.plateNumber = plateNumber;
        this.serviceType = serviceType;
        this.customerName = customerName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Nombor plat: " + plateNumber + "\nJenis servis: " + serviceType + "\nNama pelanggan: " + customerName;
    }
}

public class ServiceManager {
    private ArrayList<VehicleService> serviceList;

    public ServiceManager() {
        serviceList = new ArrayList<>();
    }

    // Menambah tempahan baru ke dalam senarai
    public void addService(VehicleService service) {
        serviceList.add(service);
        System.out.println("\n>>> Tempahan berjaya ditambah! <<<\n");
    }

    // Memaparkan semua senarai tempahan
    public void displayAllServices() {
        if (serviceList.isEmpty()) {
            System.out.println("\n>>> Tiada rekod tempahan ditemui. <<<\n");
            return;
        }
        System.out.println("\n--- SENARAI TEMPAHAN SERVIS KENDERAAN ---");
        for (int i = 0; i < serviceList.size(); i++) {
            System.out.println("Rekod ke-" + (i + 1));
            System.out.println(serviceList.get(i));
            System.out.println("----------------------------------------");
        }
    }

    // Mencari maklumat berdasarkan nombor plat (String manipulation)
    public void searchByPlateNumber(String plate) {
        boolean found = false;
        String searchKey = plate.toUpperCase().trim();

        System.out.println("\n--- HASIL CARIAN ---");
        for (VehicleService service : serviceList) {
            if (service.getPlateNumber().contains(searchKey)) {
                System.out.println(service);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println(">>> Tiada kenderaan dengan nombor plat '" + plate + "' ditemui. <<<\n");
        }
    }
}