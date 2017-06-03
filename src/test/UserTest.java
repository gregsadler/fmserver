package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 6/2/2017.
 */
public class UserTest {

    Model.User user;


    @Before
    public void setUp() throws Exception {
        user = new Model.User();
    }

    @Test
    public void setUserName() throws Exception {
        String value;
        value = "cooly445";
        System.out.println("value: " + value);
        user.setUserName(value);
        System.out.println("Object: " + user.getUserName());
    }

    @Test
    public void setPassword() throws Exception {
        String value;
        value = "neversaynever";
        System.out.println("value: " + value);
        user.setPassword(value);
        System.out.println("Object: " + user.getPassword());
    }

    @Test
    public void setEmail() throws Exception {
        String value;
        value = "hotstuff@hotmail.com";
        System.out.println("value: " + value);
        user.setEmail(value);
        System.out.println("Object: " + user.getEmail());
    }

    @Test
    public void setfirstName() throws Exception {
        String value;
        value = "Wilford";
        System.out.println("value: " + value);
        user.setfirstName(value);
        System.out.println("Object: " + user.getfirstName());
    }

    @Test
    public void setlastName() throws Exception {
        String value;
        value = "Woodruff";
        System.out.println("value: " + value);
        user.setlastName(value);
        System.out.println("Object: " + user.getlastName());
    }

    @Test
    public void setGender() throws Exception {
        String value;
        value = "trans";
        System.out.println("value: " + value);
        user.setGender(value);
        System.out.println("Object: " + user.getGender());
    }

    @Test
    public void setpersonID() throws Exception {
        String value;
        value = "cooly445";
        System.out.println("value: " + value);
        user.setpersonID(value);
        System.out.println("Object: " + user.getpersonID());
    }

}