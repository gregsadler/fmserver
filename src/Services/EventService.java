package Services;

import DatabaseAccess.DBConnection;
import Model.AuthToken;
import Model.Person;

/**
 * Created by Greg on 5/26/2017.
 */

public class EventService {

    /**
     * based on the current user, return ancestor result (array of all person objects who are ancestors)
     * @param authtokenid : authtoken of current user
     * @return : return ancestor result (array of all person objects who are ancestors)
     */
    public EventResult eventreturn(String authtokenid){
        DBConnection dbobject = new DBConnection();
        EventResult familyevents = new EventResult();
        if(checkauthtokentoke(dbobject, authtokenid)){
            familyevents = dbobject.getAncestorsEvents(authtokenid);
        }
        else{
            //throw invalid authtoken
        }
        dbobject.CloseConnection();
        return familyevents;
    }


    /**
     * based on the given event id, return event object of event id
     * @eventid : the event id of event to be returned
     * @authtoken : authtoken of current user
     * @return : return event object of interest
     */
    public Model.Event eventreturn(String eventid, String authtoken){
        Model.Event event = new Model.Event();
        DBConnection dbobject = new DBConnection();
        Model.AuthToken toke = dbobject.getAuthTokentoke(authtoken);
        if(checkauthtokentoke(dbobject, authtoken)) {
            event = dbobject.getEvent(eventid); //get user from database based on username of fill request
            if(event.getDescendant() == null || !event.getDescendant().equals(toke.getUserName())){
                dbobject.CloseConnection();
                return new Model.Event();
            }
        }
        else{
            //throw invalid authtoken
        }
        dbobject.CloseConnection();
        return event;
    }



    /**
     * check if authtoken is valid
     * @param dbobject : database connection object
     * @param authtoke : authtoke to check authtoken for
     * @return : true if valid authtoken, false if otherwise
     */
    private boolean checkauthtokentoke(DBConnection dbobject, String authtoke){
        AuthToken toke;
        toke = dbobject.getAuthTokentoke(authtoke);
        return toke.validtoken();
    }
}
