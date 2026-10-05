import model.CarService;
import model.MotorcycleService;
import service.ServiceManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServiceManager manager = new ServiceManager();
        int choice = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEM PENGURUSAN SERVIS KENDERAAN    ");
            System.out.println("==========================================");
            System.out.println("1. Tambah Servis Kereta");
            System.out.println("2. Tambah Servis Motosikal");
            System.out.println("3. Papar Senarai Semua Servis");
            System.out.println("4. Cari Servis Mengikut Nombor Plat");
            System.out.println("5. Keluar Sistem");
            System.out.println("------------------------------------------");
            System.out.print("Pilih pilihan anda (1-5): ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer input

                switch (choice) {
                    case 1:
                        tambahServisKereta(scanner, manager);
                        break;
                    case 2:
                        tambahServisMotosikal(scanner, manager);
                        break;
                    case 3:
                        manager.displayAllServices();
                        break;
                    case 4:
                        cariServis(scanner, manager);
                        break;
                    case 5:
                        System.out.println("\n[INFO] Terima kasih kerana menggunakan sistem ini. Selamat tinggal!");
                        break;
                    default:
                        System.out.println("\n[RALAT] Pilihan tidak sah! Sila pilih nombor antara 1 hingga 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[RALAT] Input tidak sah! Sila masukkan nombor sahaja.");
                scanner.nextLine(); // Clear invalid input from scanner
                choice = 0;
            } catch (Exception e) {
                System.out.println("\n[RALAT SIKAP] Berlaku ralat: " + e.getMessage());
                scanner.nextLine();
            }

        } while (choice != 5);

        scanner.close();
    }

    // --- Helper Method 1: Tambah Servis Kereta ---
    private static void tambahServisKereta(Scanner scanner, ServiceManager manager) {
        System.out.println("\n--- Tambah Servis Kereta ---");

        System.out.print("Masukkan ID Servis (cth: SRV101): ");
        String id = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan Nama Pelanggan: ");
        String name = scanner.nextLine().trim();

        System.out.print("Masukkan Nombor Plat Kenderaan: ");
        String plate = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan Harga Asas Servis (RM): ");
        double basePrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Masukkan Kapasiti Enjin (cc): ");
        int cc = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Servis Penuh? (true/false): ");
        boolean isFull = scanner.nextBoolean();
        scanner.nextLine();

        CarService car = new CarService(id, name, plate, basePrice, cc, isFull);
        manager.addService(car);
    }

    // --- Helper Method 2: Tambah Servis Motosikal ---
    private static void tambahServisMotosikal(Scanner scanner, ServiceManager manager) {
        System.out.println("\n--- Tambah Servis Motosikal ---");

        System.out.print("Masukkan ID Servis (cth: SRV102): ");
        String id = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan Nama Pelanggan: ");
        String name = scanner.nextLine().trim();

        System.out.print("Masukkan Nombor Plat Kenderaan: ");
        String plate = scanner.nextLine().trim().toUpperCase();

        System.out.print("Masukkan Harga Asas Servis (RM): ");
        double basePrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Masukkan Jenis Enjin (2-Stroke / 4-Stroke): ");
        String engineType = scanner.nextLine().trim();

        System.out.print("Hadiah Percuma Helmet? (true/false): ");
        boolean freeHelmet = scanner.nextBoolean();
        scanner.nextLine();

        MotorcycleService moto = new MotorcycleService(id, name, plate, basePrice, engineType, freeHelmet);
        manager.addService(moto);
    }

    // --- Helper Method 3: Carian Nombor Plat ---
    private static void cariServis(Scanner scanner, ServiceManager manager) {
        System.out.print("\nMasukkan Nombor Plat untuk dicari: ");
        String searchPlate = scanner.nextLine().trim().toUpperCase();

        manager.searchByPlateNumber(searchPlate);
    }
}