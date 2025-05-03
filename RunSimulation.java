import java.util.*;
import java.io.*;

/**
 * The {@code RunSimulation} class serves as the entry point for the space object tracking simulation.
 * It provides a console-based menu for different types of users such as Scientists, 
 * Space Agency Representatives, and Administrators to interact with the system.
 * 
 * <p>The system loads data from a CSV file and utilizes the {@link TrackingSystem} to provide tracking features.</p>
 */
public class RunSimulation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TrackingSystem trackingSystem = new TrackingSystem();
    private static Map<String, Boolean> loginStatus = new HashMap<>();
    /**
     * The main method launches the application, loads data from a CSV file,
     * and presents a menu for user type selection.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        loadDataFromCSV(); // <-- Updated to read from CSV

        while (true) {
            System.out.println("\nSelect user type:");
            System.out.println("1. Scientist\n2. Space Agency Rep\n3. Administrator\n4. EXIT");
            // Try adding self explanatory inputs //
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (login("scientist")) {
                        LoggerUtility.log("Scientist accessed the system.");
                        scientistMenu();
                    }
                    break;
                case "2":
                    if (login("agency")) {
                        LoggerUtility.log("Space Agency Representative accessed the system.");
                        spaceAgencyMenu();
                    }
                    break;
                case "3":
                    if (login("admin")) {
                        LoggerUtility.log("Administrator accessed the system.");
                        administratorMenu();
                    }
                    break;
                case "4":
                    LoggerUtility.log("Session ended by user.");
                    ReportWriter.writeUpdatedReport(trackingSystem.getAllObjects(), "updated_debris_report.csv");
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Input");
            }
            
        }
    }

    private static boolean login(String userType) {
        // Check if already logged in
        if (loginStatus.getOrDefault(userType.toLowerCase(), false)) {
            return true; // Already logged in
        }
    
        // Load users from the CSV file
        List<User> users = UserManager.loadUsers();
    
        System.out.println("\n" + userType + " Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().toLowerCase();  // Ensure case-insensitivity
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        // Find the user in the loaded user list
        for (User user : users) {
            // Check if the username matches and the userType is correct
            if (user.getUsername().equalsIgnoreCase(username) && user.getUserType().equalsIgnoreCase(userType)) {
                if (user.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    LoggerUtility.log(userType + " login successful.");
    
                    // Mark this user type as logged in
                    loginStatus.put(userType.toLowerCase(), true);
    
                    return true;
                } else {
                    System.out.println("\nIncorrect password.");
                    LoggerUtility.log(userType + " login failed (wrong password).");
                    return false;
                }
            }
        }
    
        // If we didn't find the user or user type didn't match
        System.out.println("\nInvalid username or user type.");
        LoggerUtility.log(userType + " login failed (user not found).");
        return false;
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
                scientistTrackObjectsMenu();
            } else if (choice.equals("2")) {
                LoggerUtility.log("Scientist selected to assess orbit status.");
                scientistOrbitStatusMenu();
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
     * Displays the Administrator menu and handles user management features (currently unimplemented).
     */
    private static void administratorMenu() {
        while (true) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Create User\n2. Manage User\n3. Delete User\n4. Back");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    UserManager.createUser(scanner);
                    break;
                case "2":
                    UserManager.manageUser(scanner);
                    break;
                case "3":
                    UserManager.deleteUser(scanner);
                    break;
                case "4":
                    LoggerUtility.log("Administrator returned to main menu.");
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Displays the submenu for selecting which type of space object to track.
     */
    private static void scientistTrackObjectsMenu() {
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
    private static void scientistOrbitStatusMenu() {
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
            System.out.println("Error loading CSV: " + e.getMessage() + ". Please restart the program.");
        }
    }

    // Helper to let us test the LoadDataFromCSV without changing to a public method.
    public static void testableLoadDataFromCSV() {
        loadDataFromCSV();
    }
    
}
