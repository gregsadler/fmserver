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
 * Created by Greg on 5/31/2017.
 */
public class ClearServiceTest {


    DatabaseAccess.DBConnection dbobj;

    @Before
    public void setUp() throws Exception {
        dbobj = new DatabaseAccess.DBConnection();
    }

    @After
    public void tearDown() throws Exception {
        dbobj.CloseConnection();
    }

    @Test
    public void cleardb() throws Exception {
        dbobj.ClearDB();
    }

}
