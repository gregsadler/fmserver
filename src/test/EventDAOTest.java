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
 * Created by Greg on 5/29/2017.
 */
public class EventDAOTest {
    private Model.Event event;
    private Model.Event event2;

    DatabaseAccess.DBConnection dobj;

    public void setevents(String diff){
        event2 = new Model.Event();
        event2.setLongitude("23");
        event2.setLatitude("24");
        event2.setCity("FH");
        event2.setCountry("Bulgaria");
        event2.setPersonID("tk421");
        event2.setDescendant("Greg");
        event2.setEventID(diff);
        event2.setYear("1942");
        event2.setEventType("Baptism");
        dobj.addevent(event2);
    }


    @Before
    public void setUp() throws Exception {
        event = new Model.Event();
        event.setLongitude("23");
        event.setLatitude("24");
        event.setCity("FH");
        event.setCountry("Bulgaria");
        event.setPersonID("tk421");
        event.setDescendant("Greg");
        event.setEventID("42");
        event.setYear("1942");
        event.setEventType("Baptism");
        dobj = new DatabaseAccess.DBConnection();
    }

    @After
    public void tearDown() throws Exception {
        dobj.CloseConnection();
    }

    @Test
    public void getEvent() throws Exception {
        event2 = dobj.getEvent(event.getEventID());
        System.out.println("OUT:");
        System.out.println(event2);
    }

    @Test
    public void addEvent() throws Exception {
        dobj.addevent(event);
        System.out.println("IN:");
        System.out.println(event.toString());
    }

    @Test
    public void deleteUserinfo() throws Exception {
        dobj.deleteuserdata("Greg");
    }

    @Test
    public void getancesetorsevents() throws Exception {
        deleteUserinfo();
        for(int i = 0; i < 10; i++){
            setevents(Integer.toString(i+30));
        }
        Model.Event[] peeps = dobj.getAncestorsEvents("1").getarray();
        for(int j = 0; j < peeps.length; j++){
            System.out.println(peeps[j].toString());
        }
    }
    @Test
    public void getlastid() throws Exception{
        System.out.println(dobj.getlasteventid());
    }


}
