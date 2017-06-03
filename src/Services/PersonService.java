package Services;

import DatabaseAccess.DBConnection;
import Model.AuthToken;
import Model.Person;

/**
 * Created by Greg on 5/26/2017.
 */

public class PersonService {


    /**
     * based on the current user, return ancestor result (array of all person objects who are ancestors)
     * @param authtokenid : authtoken of current user
     * @return : return ancestor result (array of all person objects who are ancestors)
     */
    public AncestorsResult familyreturn(String authtokenid){
        DBConnection dbobject = new DBConnection();
        AncestorsResult family = new AncestorsResult();
        if(checkauthtokentoke(dbobject, authtokenid)){
            family = dbobject.getAncestors(authtokenid);
        }
        else{
            //throw invalid authtoken
        }
        dbobject.CloseConnection();
        return family;
    }


    /**
     * based on given personid, return person object of interest
     * @param personid : personid of person of interest
     * @return : return Person object of interest
     */
    public Person personreturn(String personid, String authtoke){
        Model.Person person = new Person();
        DBConnection dbobject = new DBConnection();
        Model.AuthToken toke = dbobject.getAuthTokentoke(authtoke);
        if(checkauthtokentoke(dbobject, authtoke)) {
            person = dbobject.getPerson(personid); //get person object from database based on personid of request
            if(person.getDescendant()==null || !toke.getUserName().equals(person.getDescendant())){
                dbobject.CloseConnection();
                return new Person();
            }
        }
        else{
            //throw invalid authtoken
        }
        dbobject.CloseConnection();
        return person;
    }


    /**
     * check if authtoken is valid
     * @param dbobject : database connection object
     * @param username : username to check authtoken for
     * @return : true if valid authtoken, false if otherwise
     */
    private boolean checkauthtokenuser(DBConnection dbobject, String username){
        AuthToken toke;
        toke = dbobject.getAuthToken(username);
        return toke.validtoken();
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
