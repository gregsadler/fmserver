package Services;

import DatabaseAccess.DBConnection;

/**
 * Created by Greg on 5/19/2017.
 */

public class LoadService {
    /**
     * Specific load request for this class
     */
    LoadRequest request;

    /**
     * constructor setting load request object
     * @param request : sets this load request object
     */
    public LoadService(LoadRequest request){
        this.request = request;
    }

    /**
     * Clears Database and then extracts objects from request object to load into database.
     */
    public void serviceload(){
        boolean clearworked;
        DBConnection dbobject = new DBConnection();
        clearworked = dbobject.ClearDB();
        if(clearworked){
            for(int i = 0; i < request.getusersize(); i++){ //extract and load users from load request object
                dbobject.addUser(request.getUser(i));
            }
            for(int i = 0; i < request.getpersonssize(); i++){ //extract and load persons from load request object
                dbobject.addPerson(request.getPerson(i));
            }
            for(int i = 0; i < request.geteventssize(); i++){ //extract and load events from load request object
                dbobject.addevent(request.getEvent(i));
            }
        }
        dbobject.CloseConnection();
    }


}
