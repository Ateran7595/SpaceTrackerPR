import java.util.*;
import java.io.*;

public class UserManager {
    private static final String FILE_PATH = "users.csv";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("username,password,userType");
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getUserType());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static void createUser(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user type (scientist, agency, policy, admin): ");
        String userType = scanner.nextLine().toLowerCase();
    
        // Validate userType to ensure it is one of the allowed types
        if (!isValidUserType(userType)) {
            System.out.println("Invalid user type. Please enter one of the following: scientist, agency, policy, admin.");
            return;
        }
    
        List<User> users = loadUsers();
    
        // Check if username already exists
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }
    
        // Add new user and save to file
        users.add(new User(username, password, userType));
        saveUsers(users);
        System.out.println("User created successfully.");
    }
    
    // Helper method to validate the user type
    private static boolean isValidUserType(String userType) {
        return userType.equals("scientist") || userType.equals("agency") || userType.equals("policy") || userType.equals("admin");
    }
    

    public static void manageUser(Scanner scanner) {
        List<User> users = loadUsers();

        System.out.print("Enter username to manage: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.print("Enter new username (or press Enter to keep current): ");
                String newUsername = scanner.nextLine();
                if (!newUsername.isEmpty()) {
                    user.setUsername(newUsername);
                }

                System.out.print("Enter new password (or press Enter to keep current): ");
                String newPassword = scanner.nextLine();
                if (!newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }

                saveUsers(users);
                System.out.println("User updated successfully.");
                return;
            }
        }

        System.out.println("User not found.");
    }

    public static void deleteUser(Scanner scanner) {
        List<User> users = loadUsers();
    
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();
    
        // Iterate through the list of users to find the matching username
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equalsIgnoreCase(username)) {
                // Ask for confirmation before deletion
                System.out.print("Are you sure you want to delete this user? (yes/no): ");
                String confirmation = scanner.nextLine().toLowerCase();
    
                if (confirmation.equals("yes")) {
                    iterator.remove();
                    saveUsers(users);
                    System.out.println("User deleted successfully.");
                    return;
                } else {
                    System.out.println("User deletion canceled.");
                    return;
                }
            }
        }
    
        System.out.println("User not found.");
    }    
}
