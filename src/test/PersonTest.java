package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 6/2/2017.
 */
public class PersonTest {

    Model.Person pers;

    @Before
    public void setup(){
        pers = new Model.Person();
    }

    @Test
    public void setpersonID() throws Exception {
        String value;
        value = "test1123";
        System.out.println("value: " + value);
        pers.setpersonID(value);
        System.out.println("Object: " + pers.getpersonID());
    }

    @Test
    public void setDescendant() throws Exception {
        String value;
        value = "greg";
        System.out.println("value: " + value);
        pers.setDescendant(value);
        System.out.println("Object: " + pers.getDescendant());
    }

    @Test
    public void setFirstName() throws Exception {
        String value;
        value = "Isaac";
        System.out.println("value: " + value);
        pers.setFirstName(value);
        System.out.println("Object: " + pers.getFirstName());
    }

    @Test
    public void setLastName() throws Exception {
        String value;
        value = "of Ur";
        System.out.println("value: " + value);
        pers.setLastName(value);
        System.out.println("Object: " + pers.getLastName());
    }

    @Test
    public void setGender() throws Exception {
        String value;
        value = "m";
        System.out.println("value: " + value);
        pers.setGender(value);
        System.out.println("Object: " + pers.getGender());
    }

    @Test
    public void setFather() throws Exception {
        String value;
        value = "abraham";
        System.out.println("value: " + value);
        pers.setFather(value);
        System.out.println("Object: " + pers.getFather());
    }

    @Test
    public void setMother() throws Exception {
        String value;
        value = "sarah";
        System.out.println("value: " + value);
        pers.setMother(value);
        System.out.println("Object: " + pers.getMother());
    }

    @Test
    public void setSpouse() throws Exception {
        String value;
        value = "rachel";
        System.out.println("value: " + value);
        pers.setSpouse(value);
        System.out.println("Object: " + pers.getSpouse());
    }

}