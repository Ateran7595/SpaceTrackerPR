/**
 * The {@code User} class represents a user with a username, password, and user type.
 * It provides methods to access and modify these properties.
 */
public class User {
    /** The username of the user. */
    private String username;

    /** The password of the user. */
    private String password;

    /** The type of user (e.g., "scientist", "agency", "admin"). */
    private String userType;

    /**
     * Constructs a new {@code User} with the specified username, password, and user type.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param userType the type of user (e.g., "scientist", "agency", "admin")
     */
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user type.
     *
     * @param userType the new user type (e.g., "scientist", "agency", "admin")
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
