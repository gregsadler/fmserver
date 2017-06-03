package Services;

/**
 * Created by Greg on 5/19/2017.
 * Login Request Object stores info from Login page
 */

public class LoginRequest {
    /**
     * Fields from Login page
     */
    private String userName;
    private String password;

    /**
     * Getters and Setters for class variables
     * @return
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
