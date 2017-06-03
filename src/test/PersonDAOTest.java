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


public class PersonDAOTest {
    private Model.Person person;
    private Model.Person person2;

    DatabaseAccess.DBConnection dobj;

    public void setpers(String diff){
        person2 = new Model.Person();
        person2.setMother("123");
        person2.setFather("456");
        person2.setSpouse("789");
        person2.setLastName("Sadler");
        person2.setFirstName("Greg");
        person2.setPersonId(diff);
        person2.setDescendant("Greg");
        person2.setGender("M");
        dobj.addPerson(person2);
    }

    @Before
    public void setUp() throws Exception {
        person = new Model.Person();
        person.setMother("123");
        person.setFather("456");
        person.setSpouse("789");
        person.setLastName("Sadler");
        person.setFirstName("Greg");
        person.setPersonId("tk421");
        person.setDescendant("Greg");
        person.setGender("M");
        dobj = new DatabaseAccess.DBConnection();
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void getPerson() throws Exception {
        person2 = dobj.getPerson("tk421");
        System.out.println("Out:");
        System.out.println(person2.toString());
    }

    @Test
    public void addPerson() throws Exception {
        dobj.addPerson(person);
        System.out.println("IN:");
        System.out.println(person.toString());
    }

    @Test
    public void deleteUserinfo() throws Exception {
        dobj.deleteuserdata("Greg");
    }

    @Test
    public void getancesetors() throws Exception {
        deleteUserinfo();
        for(int i = 0; i < 10; i++){
            setpers(Integer.toString(i+30));
        }
        Model.Person[] peeps = dobj.getAncestors("1").getarray();
        for(int j = 0; j < peeps.length; j++){
            System.out.println(peeps[j].toString());
        }
    }

    @Test
    public void getlastid() throws Exception{
        System.out.println(dobj.getlastpersonid());
    }


}
