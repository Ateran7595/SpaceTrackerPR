import java.util.*;
import java.io.*;

/**
 * The {@code UserManager} class provides utility methods to manage user accounts,
 * including loading, saving, creating, updating, and deleting users from a CSV file.
 */
public class UserManager {
    /** Path to the CSV file used to store user data. */
    private static final String FILE_PATH = "users.csv";

    /**
     * Loads the list of users from the CSV file.
     *
     * @return a list of {@link User} objects loaded from the file
     */
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

    /**
     * Saves a list of users to the CSV file.
     *
     * @param users the list of {@link User} objects to be saved
     */
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

    /**
     * Creates a new user by prompting input from the user via the console.
     * Validates the user type and checks for duplicate usernames before saving.
     *
     * @param scanner the {@link Scanner} object used to read user input
     */
    public static void createUser(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user type (scientist, agency, admin): ");
        String userType = scanner.nextLine().toLowerCase();

        if (!isValidUserType(userType)) {
            System.out.println("Invalid user type. Please enter one of the following: scientist, agency, admin.");
            return;
        }

        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }

        users.add(new User(username, password, userType));
        saveUsers(users);
        System.out.println("User created successfully.");
    }

    /**
     * Validates whether the given user type is one of the allowed values.
     *
     * @param userType the type of user to validate
     * @return {@code true} if the user type is valid, {@code false} otherwise
     */
    public static boolean isValidUserType(String userType) {
        return userType.equals("scientist") || userType.equals("agency") || userType.equals("admin");
    }

    /**
     * Allows modification of an existing user's username and/or password.
     * Prompts the user for input and updates the information in the CSV file.
     *
     * @param scanner the {@link Scanner} object used to read user input
     */
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

    /**
     * Deletes a user from the list based on the username provided via console input.
     * Asks for confirmation before deletion.
     *
     * @param scanner the {@link Scanner} object used to read user input
     */
    public static void deleteUser(Scanner scanner) {
        List<User> users = loadUsers();

        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equalsIgnoreCase(username)) {
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
