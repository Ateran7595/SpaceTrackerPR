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
                    LoggerUtility.log("Scientist accessed the system.");
                    scientistMenu();
                    break;
                case "2":
                    LoggerUtility.log("Space Agency Representative accessed the system.");
                    spaceAgencyMenu();
                    break;
                case "3":
                    LoggerUtility.log("Policymaker accessed the system.");
                    policyMakerMenu();
                    break;
                case "4":
                    LoggerUtility.log("Administrator accessed the system.");
                    administratorMenu();
                    break;
                case "5":
                    LoggerUtility.log("Session ended by user.");
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
                LoggerUtility.log("Scientist selected to track objects in space.");
                trackObjectsMenu();
            } else if (choice.equals("2")) {
                LoggerUtility.log("Scientist selected to assess orbit status.");
                OrbitStatus();
            } else if (choice.equals("3")) {
                LoggerUtility.log("Scientist returned to main menu.");
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
                LoggerUtility.log("Space Agency tried to analyze long-term impact (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("2")) {
                LoggerUtility.log("Space Agency tried to generate density reports (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("3")) {
                LoggerUtility.log("Space Agency returned to main menu.");
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
                LoggerUtility.log("Policymaker attempted to review reports on debris impact (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("2")) {
                LoggerUtility.log("Policymaker attempted to assess risk levels (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("3")) {
                LoggerUtility.log("Policymaker returned to main menu.");
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
                LoggerUtility.log("Administrator tried to create a user (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("2")) {
                LoggerUtility.log("Administrator tried to manage users (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("3")) {
                LoggerUtility.log("Administrator tried to delete a user (not yet implemented).");
                System.out.println("Error: Functionality Under Development");
            } else if (choice.equals("4")) {
                LoggerUtility.log("Administrator returned to main menu.");
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void trackObjectsMenu() {
        System.out.println("\nTrack Objects:");
        System.out.println("1. Rocket Body\n2. Debris\n3. Payload\n4. Unknown\n5. Back");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                LoggerUtility.log("Scientist queried all Rocket Bodies.");
                trackingSystem.displayObjects("Rocket Body");
                break;
            case "2":
                LoggerUtility.log("Scientist queried all Debris.");
                trackingSystem.displayObjects("Debris");
                break;
            case "3":
                LoggerUtility.log("Scientist queried all Payloads.");
                trackingSystem.displayObjects("Payload");
                break;
            case "4":
                LoggerUtility.log("Scientist queried all Unknown objects.");
                trackingSystem.displayObjects("Unknown");
                break;
            default:
                System.out.println("Back or Invalid option");
        }
    }

    private static void OrbitStatus() {
        System.out.println("\nAssess Orbit Status:");
        System.out.println("1. Track Objects in LEO\n2. Assess if debris is still in orbit\n3. Back");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                LoggerUtility.log("Scientist tracked objects in Low Earth Orbit (LEO).");
                trackingSystem.displayObjectsInLEO();
                break;
            case "2":
                LoggerUtility.log("Scientist assessed if debris is still in orbit.");
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
            LoggerUtility.log("System successfully loaded data from rso_metrics.csv.");
            System.out.println("Data loaded successfully from rso_metrics.csv.");
        } catch (IOException e) {
            LoggerUtility.log("Error loading data from rso_metrics.csv: " + e.getMessage());
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }
}
