package application.main;

import application.model.OilChangeService;
import application.model.Service;
import application.model.TyreService;
import application.service.ServiceManager;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static ServiceManager manager = new ServiceManager();

    public static void main(String[] args) {
        int choice = 0;

        do {
            showMenu();
            try {
                choice = readInt("Masukkan pilihan (1-5): ");

                switch (choice) {
                    case 1:
                        addService();
                        break;
                    case 2:
                        displayAll();
                        break;
                    case 3:
                        searchByPlate();
                        break;
                    case 4:
                        deleteService();
                        break;
                    case 5:
                        System.out.println("Terima kasih. Program tamat.");
                        break;
                    default:
                        System.out.println("Pilihan tidak sah. Sila pilih 1 hingga 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ralat: Input tidak sah. Sila masukkan nombor sahaja.");
                choice = 0;
            } catch (IllegalArgumentException e) {
                System.out.println("Ralat: " + e.getMessage());
                choice = 0;
            } catch (NoSuchElementException e) {
                System.out.println("Input tamat. Program ditutup.");
                choice = 5;
            }
        } while (choice != 5);

        input.close();
    }

    // ---------- Menu ----------

    private static void showMenu() {
        System.out.println();
        System.out.println("=== SISTEM PENGURUSAN SERVICE KENDERAAN ===");
        System.out.println("1. Tambah servis");
        System.out.println("2. Papar semua servis");
        System.out.println("3. Cari servis (nombor plat)");
        System.out.println("4. Padam servis");
        System.out.println("5. Keluar");
        System.out.println("===========================================");
    }

    // ---------- Operasi menu ----------

    private static void addService() {
        System.out.println();
        System.out.println("--- Tambah Servis ---");
        System.out.println("1. Tukar Minyak");
        System.out.println("2. Servis Tayar");
        int type = readInt("Pilih jenis servis (1-2): ");

        if (type != 1 && type != 2) {
            System.out.println("Gagal: Jenis servis tidak sah.");
            return;
        }

        String plate = formatPlate(readText("Nombor plat: "));
        String owner = formatName(readText("Nama pemilik: "));
        Service service;

        if (type == 1) {
            double liters = readPositiveDouble("Kuantiti minyak (liter): ");
            double price = readPositiveDouble("Harga seliter (RM): ");
            service = new OilChangeService(manager.getNextId(), plate, owner, liters, price);
        } else {
            int count = readPositiveInt("Bilangan tayar: ");
            double price = readPositiveDouble("Harga satu tayar (RM): ");
            service = new TyreService(manager.getNextId(), plate, owner, count, price);
        }

        manager.addService(service);
        System.out.printf("Berjaya: Servis ditambah. Kos servis = RM %.2f%n", service.calculateCost());
    }

    private static void displayAll() {
        System.out.println();
        System.out.println("--- Senarai Semua Servis ---");
        ArrayList<Service> list = manager.getAllServices();

        if (list.isEmpty()) {
            System.out.println("Tiada rekod servis.");
            return;
        }

        printTable(list);
        System.out.printf("Jumlah kos semua servis: RM %.2f%n", manager.getTotalCost());
    }

    private static void searchByPlate() {
        System.out.println();
        System.out.println("--- Cari Servis ---");
        String plate = formatPlate(readText("Masukkan nombor plat: "));
        ArrayList<Service> result = manager.findByPlate(plate);

        if (result.isEmpty()) {
            System.out.println("Gagal: Nombor plat " + plate + " tidak dijumpai.");
        } else {
            printTable(result);
        }
    }

    private static void deleteService() {
        System.out.println();
        System.out.println("--- Padam Servis ---");
        int id = readInt("Masukkan ID servis: ");

        if (manager.deleteById(id)) {
            System.out.println("Berjaya: Servis ID " + id + " telah dipadam.");
        } else {
            System.out.println("Gagal: ID " + id + " tidak dijumpai.");
        }
    }

    // ---------- Paparan jadual ----------

    private static void printTable(ArrayList<Service> list) {
        System.out.printf("%-4s %-14s %-10s %-20s %12s%n",
                "ID", "Jenis", "No. Plat", "Pemilik", "Kos (RM)");
        System.out.println("-----------------------------------------------------------------");
        for (Service s : list) {
            System.out.printf("%-4d %-14s %-10s %-20s %12.2f%n",
                    s.getId(), s.getServiceType(), s.getPlateNumber(),
                    s.getOwnerName(), s.calculateCost());
        }
    }

    // ---------- Input dan validasi ----------

    private static String readText(String prompt) {
        System.out.print(prompt);
        String text = input.nextLine().trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input tidak boleh kosong.");
        }
        return text;
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(input.nextLine().trim());
    }

    private static int readPositiveInt(String prompt) {
        int value = readInt(prompt);
        if (value <= 0) {
            throw new IllegalArgumentException("Nilai mesti lebih daripada 0.");
        }
        return value;
    }

    private static double readPositiveDouble(String prompt) {
        System.out.print(prompt);
        double value = Double.parseDouble(input.nextLine().trim());
        if (!(value > 0) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Nilai mesti nombor lebih daripada 0.");
        }
        return value;
    }

    // ---------- String manipulation ----------

    // Buang ruang kosong, tukar huruf besar, dan sahkan hanya huruf/nombor
    private static String formatPlate(String text) {
        String plate = text.trim().toUpperCase().replace(" ", "");
        if (!plate.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("Nombor plat hanya boleh mengandungi huruf dan nombor.");
        }
        return plate;
    }

    // Buang ruang berlebihan dan jadikan huruf pertama setiap perkataan huruf besar
    private static String formatName(String text) {
        String[] words = text.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase())
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
        }
        return result.toString().trim();
    }
}
