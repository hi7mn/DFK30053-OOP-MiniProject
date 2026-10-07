package application.service;

import application.model.Service;
import java.util.ArrayList;

/**
 * Menguruskan senarai rekod servis menggunakan ArrayList.
 */
public class ServiceManager {

    private ArrayList<Service> serviceList = new ArrayList<>();
    private int lastId = 0;

    // Jana ID baharu untuk setiap rekod servis
    public int getNextId() {
        lastId++;
        return lastId;
    }

    // Tambah rekod servis
    public void addService(Service service) {
        serviceList.add(service);
    }

    // Dapatkan semua rekod
    public ArrayList<Service> getAllServices() {
        return serviceList;
    }

    // Cari rekod berdasarkan nombor plat
    public ArrayList<Service> findByPlate(String plateNumber) {
        ArrayList<Service> result = new ArrayList<>();
        for (Service s : serviceList) {
            if (s.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                result.add(s);
            }
        }
        return result;
    }

    // Padam rekod berdasarkan ID. Pulangkan true jika berjaya.
    public boolean deleteById(int id) {
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getId() == id) {
                serviceList.remove(i);
                return true;
            }
        }
        return false;
    }

    // Kira jumlah kos semua servis (polymorphism: calculateCost() ikut jenis servis)
    public double getTotalCost() {
        double total = 0;
        for (Service s : serviceList) {
            total += s.calculateCost();
        }
        return total;
    }
}
