package test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Greg on 5/30/2017.
 */
public class UserDAOTest {

    private Model.User user;
    private Model.User user2;

    DatabaseAccess.DBConnection dobj;


    @Before
    public void setUp() throws Exception {
        dobj = new DatabaseAccess.DBConnection();
        user = new Model.User();
        user.setUserName("Greg");
        user.setpersonID("12");
        user.setPassword("secretsecret");
        user.setEmail("cool@g.com");
        user.setGender("M");
        user.setfirstName("Greg");
        user.setlastName("Sadler");
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void getUser() throws Exception {
        user2  = dobj.getUser("tk421");
        System.out.println("OUT:");
        System.out.println(user2.toString());
    }

    @Test
    public void addUser() throws Exception {
        System.out.println("IN:");
        System.out.println(user.toString());
        dobj.addUser(user);
    }



}
