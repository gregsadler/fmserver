package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 6/2/2017.
 */
public class EventTest {

    Model.Event event;

    @Before
    public void setUP(){
        event = new Model.Event();
    }

    @Test
    public void setEventID() throws Exception {
        String value;
        value = "test1123";
        System.out.println("value: " + value);
        event.setEventID(value);
        System.out.println("Object: " + event.getEventID());
    }

    @Test
    public void setDescendant() throws Exception {
        String value;
        value = "frank";
        System.out.println("value: " + value);
        event.setDescendant(value);
        System.out.println("Object: " + event.getDescendant());
    }


    @Test
    public void setPersonID() throws Exception {
        String value;
        value = "per33";
        System.out.println("value: " + value);
        event.setPersonID(value);
        System.out.println("Object: " + event.getPersonID());
    }

    @Test
    public void setLatitude() throws Exception {
        String value;
        value = "1211";
        System.out.println("value: " + value);
        event.setLatitude(value);
        System.out.println("Object: " + event.getLatitude());
    }

    @Test
    public void setLongitude() throws Exception {
        String value;
        value = "49393";
        System.out.println("value: " + value);
        event.setLongitude(value);
        System.out.println("Object: " + event.getLongitude());
    }

    @Test
    public void setCountry() throws Exception {
        String value;
        value = "kazhakstan";
        System.out.println("value: " + value);
        event.setCountry(value);
        System.out.println("Object: " + event.getCountry());
    }

    @Test
    public void setCity() throws Exception {
        String value;
        value = "melbourne";
        System.out.println("value: " + value);
        event.setCity(value);
        System.out.println("Object: " + event.getCity());
    }

    @Test
    public void setEventType() throws Exception {
        String value;
        value = "baptism";
        System.out.println("value: " + value);
        event.setEventType(value);
        System.out.println("Object: " + event.getEventType());
    }

    @Test
    public void setYear() throws Exception {
        String value;
        value = "1776";
        System.out.println("value: " + value);
        event.setEventID(value);
        System.out.println("Object: " + event.getEventID());
    }

}