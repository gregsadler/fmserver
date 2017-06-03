package Services;

import DatabaseAccess.DBConnection;

/**
 * Handles methods of Database Access class to clear database
 * Created by Greg on 5/19/2017.
 */

public class ClearService {

    /**
     * Clears Database
     * @return : true if clear succesful, false if errors
     */
    public boolean cleardb(){
        boolean errorcheck;
        DatabaseAccess.DBConnection dbConnection = new DatabaseAccess.DBConnection();
        errorcheck = dbConnection.ClearDB();
        dbConnection.CloseConnection();
        return errorcheck;
    }

}
