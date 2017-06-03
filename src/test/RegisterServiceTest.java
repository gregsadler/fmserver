package test;

import Model.AuthToken;
import Services.RegisterRequest;
import Services.RegisterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 5/31/2017.
 */
public class RegisterServiceTest {
    DatabaseAccess.DBConnection dobj;
    RegisterRequest request;
    RegisterService service;

    @Before
    public void setUp() throws Exception {
        dobj = new DatabaseAccess.DBConnection();
        request = new RegisterRequest();
        request.setUserName("George");
        request.setPassword("secretsecret");
        request.setEmail("cool@g.com");
        request.setGender("M");
        request.setfirstName("Greg");
        request.setlastName("Sadler");
        service = new RegisterService(request);
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void serviceregister() throws Exception {
        AuthToken toke = service.serviceregister();
        System.out.println(toke.toString());

    }

}