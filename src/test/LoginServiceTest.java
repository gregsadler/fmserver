package test;

import Services.LoginRequest;
import Services.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 5/31/2017.
 */
public class LoginServiceTest {
    DatabaseAccess.DBConnection dobj;
    LoginRequest request;
    LoginService service;

    @Before
    public void setUp() throws Exception {
        dobj = new DatabaseAccess.DBConnection();
        request = new LoginRequest();
        request.setUserName("Greg");
        request.setPassword("flop");
        service = new LoginService(request);
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void servicelogin() throws Exception {
        Model.AuthToken toke = service.servicelogin();
        System.out.println(toke);
    }

}