import ApplicationModel.VehicleService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ServiceManager {
    private ArrayList<VehicleService> serviceList;

    public ServiceManager() {
        serviceList = new ArrayList<>();
    }

    private void printServiceInfo(VehicleService service) {
        try {
            Method displayInfoMethod = VehicleService.class.getDeclaredMethod("displayInfo");
            displayInfoMethod.setAccessible(true);
            displayInfoMethod.invoke(service);
        } catch (NoSuchMethodException e) {
            System.out.println(service);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
            printServiceInfo(serviceList.get(i));
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
                printServiceInfo(service);
                System.out.println("--------------------");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println(">>> Tiada kenderaan dengan nombor plat '" + plate + "' ditemui. <<<\n");
        }
    }

    // [FUNGSI TAMBAHAN] Mengira jumlah keseluruhan kos pendapatan servis
    private double calculateServiceCost(VehicleService service) {
        try {
            Method costMethod = service.getClass().getDeclaredMethod("calculateServiceCost");
            costMethod.setAccessible(true);
            Object result = costMethod.invoke(service);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            }
        } catch (NoSuchMethodException e) {
            System.out.println("\n>>> Tiada kaedah calculateServiceCost() dalam kelas " + service.getClass().getSimpleName() + ". <<<\n");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double calculateTotalRevenue() {
        double total = 0;
        for (VehicleService service : serviceList) {
            total += calculateServiceCost(service);
        }
        return total;
    }

    // [FUNGSI TAMBAHAN] Memadam rekod mengikut nombor indeks senarai
    public void removeService(int index) {
        if (index >= 0 && index < serviceList.size()) {
            serviceList.remove(index);
            System.out.println("\n>>> Rekod berjaya dipadam! <<<\n");
        } else {
            System.out.println("\n>>> Ralat: Nombor rekod tidak sah. <<<\n");
        }
    }
}