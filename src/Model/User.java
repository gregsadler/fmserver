package Model;

/**
 * Created by Greg on 5/19/2017.
 */

public class User {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;



    public String getUserName() {
        return userName;
    }

    public void addregisterdata(Services.RegisterRequest request){
        this.userName = request.getUserName();
        this.password = request.getPassword();
        this.email = request.getEmail();
        this.firstName = request.getfirstName();
        this.lastName = request.getlastName();
        this.gender = request.getGender();
    }

    /**
     * Getters and setters for class
     * @param
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getpersonID() {
        return personID;
    }

    public void setpersonID(String personID) {
        if(this.personID == null) {
            this.personID = personID;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", personID='" + personID + '\'' +
                '}';
    }
}
