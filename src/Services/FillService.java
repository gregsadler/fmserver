package Services;

import DatabaseAccess.DBConnection;

/**
 * Created by Greg on 5/19/2017.
 */

public class FillService {
    /**
     * Fill request object to store fill info
     */
    private FillRequest fill;



    /**
     * Set this fillrequest object
     * @param fill : setter for this fillrequest object
     */
    public FillService(FillRequest fill){
        this.fill =fill;
    }


    /**
     * Check if username in fillrequest is valid
     * @return : true if valid username, false otherwise
     */
    private boolean checkuserName(){
        Model.User user;

        DBConnection dbobject = new DBConnection();

        user = dbobject.getUser(fill.getUserName()); //get user from database based on username of fill request
        dbobject.CloseConnection();
        if(user.getUserName() == null){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     * Call generate data clase to generate data in database.
     */
    public boolean fillgenerations(){
        if(checkuserName()) {
            DBConnection dbobject = new DBConnection();

            boolean check = checkuserName();
            if(!check){ //check for valid username
                return false;
            }
            dbobject.deleteuserdata(fill.getUserName());
            dbobject.CloseConnection();
            GenerateData gendata = new GenerateData(Integer.parseInt(fill.getGenerations()),fill.getUserName());
            gendata.generate(fill.getUserName());

            return true;
        }
        else{
            return false;
        }
    }


}
