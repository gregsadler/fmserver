package Services;

import DatabaseAccess.DBConnection;

/**
 * Handles methods that call Database access objects to service login requests.
 * Created by Greg on 5/19/2017.
 */

public class LoginService {
    /**
     * Login Request Object storing username and password
     */
    private LoginRequest request;



    /**
     * Constructor setting login request
     * @param request : login request object
     */
    public LoginService(LoginRequest request){
        this.request = request;
    }





    /**
     * Fucntion queries database (user table) to try and find user in the database, then checks password
     * If user and password is valid, creates a AuthToken object, stores authtoken object and returns the AuthToken object
     */
    public Model.AuthToken servicelogin(){
        Model.AuthToken toke = new Model.AuthToken();
        Model.User user;
        DBConnection dbobject = new DBConnection();

        user = dbobject.getUser(request.getUserName()); //get user from database based on username of fill request
        if(user.getUserName() == null){
            dbobject.CloseConnection();
            return toke;
            //throw user doesn't exist
        }
        else{
            if(!user.getPassword().equals(request.getPassword())){
                dbobject.CloseConnection();
                return toke;
                //throw exception
            }
            else {
                toke.setUserName(user.getUserName());
                toke.setPersonID(user.getpersonID());
                toke.setDateTime(Long.toString(System.currentTimeMillis()));
                dbobject.addAuthToken(toke); //add authtoken to database if username and password were valid
                dbobject.CloseConnection();
                return toke;
            }
        }
    }


}
