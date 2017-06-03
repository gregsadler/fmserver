package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 6/2/2017.
 */
public class AuthTokenTest {
    Model.AuthToken toke;

    @Before
    public void setUp() throws Exception {
        toke = new Model.AuthToken();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAuthToken() throws Exception {
        String value;
        value = "test1123";
        System.out.println("value: " + value);
        toke.setAuthToken(value);
        System.out.println("Object: " + toke.getAuthToken());
    }


    @Test
    public void setUserName() throws Exception {
        String value;
        value = "user11";
        System.out.println("value: " + value);
        toke.setUserName(value);
        System.out.println("Object: " + toke.getUserName());
    }



    @Test
    public void setPersonID() throws Exception {
        String value;
        value = "person99";
        System.out.println("value: " + value);
        toke.setPersonID(value);
        System.out.println("Object: " + toke.getPersonID());
    }


    @Test
    public void setDateTime() throws Exception {
        String value;
        value = Long.toString(System.currentTimeMillis());
        System.out.println("value: " + value);
        toke.setAuthToken(value);
        System.out.println("Object: " + toke.getAuthToken());
    }

    @Test
    public void validtoken() throws Exception {
        setDateTime();
        int duration = 100;
        if(duration+Integer.parseInt(toke.getDateTime())< System.currentTimeMillis() == toke.validtoken()){
            System.out.println("Worked!");
        }
        else{
            System.out.println("Failed");
        }
    }

}