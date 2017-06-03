package test;

import Services.FillRequest;
import Services.FillService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Greg on 5/31/2017.
 */
public class FillServiceTest {
    Services.FillRequest request;
    Services.FillService service;

    @Before
    public void setUp() throws Exception {
        request = new FillRequest();
        request.setUserName("Greg");
        request.setGenerations("4");
        service = new FillService(request);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fillgenerations() throws Exception {
        Services.ClearService serve = new Services.ClearService();
        service.fillgenerations();
    }

}