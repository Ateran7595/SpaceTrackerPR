import java.util.*;
import java.io.*;

/**
 * The {@code RunSimulation} class serves as the entry point for the space object tracking simulation.
 * It provides a console-based menu for different types of users such as Scientists, 
 * Space Agency Representatives, Policymakers, and Administrators to interact with the system.
 * 
 * <p>The system loads data from a CSV file and utilizes the {@link TrackingSystem} to provide tracking features.</p>
 */
public class RunSimulation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TrackingSystem trackingSystem = new TrackingSystem();

    /**
     * The main method launches the application, loads data from a CSV file,
     * and presents a menu for user type selection.
     * 
     * @param args command-line arguments (not used)
     */
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

    /**
     * Displays the Scientist menu and handles related actions such as tracking and assessing orbits.
     */
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

    /**
     * Displays the Space Agency Representative menu and handles related (currently unimplemented) actions.
     */
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

    /**
     * Displays the Policymaker menu and handles related (currently unimplemented) actions.
     */
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

    /**
     * Displays the Administrator menu and handles user management features (currently unimplemented).
     */
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

    /**
     * Displays the submenu for selecting which type of space object to track.
     */
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

    /**
     * Displays the submenu for assessing orbit status and selecting tracking methods.
     */
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

    /**
     * Loads space object data from a CSV file using {@link CSVReader}.
     * If successful, logs the event and informs the user; otherwise logs the error.
     */
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
