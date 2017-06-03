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


public class AuthTokenDAOTest {

    private Model.AuthToken person;
    private Model.AuthToken person2;

    DatabaseAccess.DBConnection dobj;

    @Before
    public void setUp() throws Exception {
        dobj = new DatabaseAccess.DBConnection();
        person = new Model.AuthToken();
        person.setAuthToken("1");
        person.setDateTime("200");
        person.setPersonID("Greg");
        person.setUserName("Greg");
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void getAuthToken() throws Exception {
        person2 = dobj.getAuthToken("Greg");
        System.out.println("OUT:");
        System.out.println(person2.toString());
    }

    @Test
    public void getAuthTokentoke() throws Exception {
        person2 = dobj.getAuthTokentoke("1");
        System.out.println("OUT:");
        System.out.println(person2.toString());
    }

    @Test
    public void addAuthToken() throws Exception {
        System.out.println("IN:");
        System.out.println(person.toString());
        dobj.addAuthToken(person);
    }

    @Test
    public void getlastid() throws Exception{
        System.out.println(dobj.getlastauthid());
    }


}
