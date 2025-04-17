import java.util.*;
import java.io.*;

public class RunSimulation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TrackingSystem trackingSystem = new TrackingSystem();

    public static void main(String[] args) {
        loadDataFromCSV(); // <-- Updated to read from CSV

        while (true) {
            System.out.println("Select user type:");
            System.out.println("1. Scientist\n2. Space Agency Rep\n3. Policymaker\n4. Administrator\n5. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    scientistMenu();
                    break;
                case "2":
                    spaceAgencyMenu();
                    break;
                case "3":
                    policyMakerMenu();
                    break;
                case "4":
                    administratorMenu();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Functionality not implemented for this user yet.");
            }
        }
    }

    private static void scientistMenu() {
        while (true) {
            System.out.println("\nScientist Menu:");
            System.out.println("1. Track Objects in Space\n2. Assess Orbit Status\n3. Back");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                trackObjectsMenu();
            } else if (choice.equals("2")) {
                OrbitStatus();
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void spaceAgencyMenu() {
        while (true) {
            System.out.println("\nSpace Agency Menu:");
            System.out.println("1. Analyze Long-Term Impact\n2. Generate Density Reports\n3. Back");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("2")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void policyMakerMenu() {
        while (true) {
            System.out.println("\nPolicy Maker Menu:");
            System.out.println("1. Review Reports on Debris Impact\n2. Assess Risk Levels for Future Space Missions\n3. Back");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("2")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void administratorMenu() {
        while (true) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Create User\n2. Manage User\n3. Delete User\n4. Back");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("2")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("3")) {
                System.out.println("Error: Functionality Under Development"); //needs development
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void trackObjectsMenu() {
        System.out.println("Track Objects:");
        System.out.println("1. Rocket Body\n2. Debris\n3. Payload\n4. Unknown\n5. Back");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                trackingSystem.displayObjects("Rocket Body");
                break;
            case "2":
                trackingSystem.displayObjects("Debris");
                break;
            case "3":
                trackingSystem.displayObjects("Payload");
                break;
            case "4":
                trackingSystem.displayObjects("Unknown");
                break;
            default:
                System.out.println("Back or Invalid option");
        }
    }

    private static void OrbitStatus() {
        System.out.println("Assess Orbit Status:");
        System.out.println("1. Track Objects in LEO\n2. Assess if debris is still in orbit\n3. Back");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                trackingSystem.displayObjectsInLEO();
                break;
            case "2":
                trackingSystem.assessOrbitStatusAndExport();
                break;
            default:
                System.out.println("Back or Invalid option");
        }
    }

    private static void loadDataFromCSV() {
        CSVReader reader = new CSVReader();
        try {
            reader.loadObjects("rso_metrics.csv", trackingSystem);
            System.out.println("Data loaded successfully from rso_metrics.csv.");
        } catch (IOException e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }
}
