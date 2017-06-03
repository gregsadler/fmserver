package Services;

import DatabaseAccess.DBConnection;
import Model.AuthToken;

/**
 * Created by Greg on 5/19/2017.
 */

public class RegisterService {
    private RegisterRequest request;

    /**
     * Sets Register Request object on construction
     * @param request
     */
    public RegisterService(RegisterRequest request){
        this.request = request;
    }

    /**
     * Checks if Username is already in use,
     * @return : returns true if username valid, false if username already in use
     */
    public boolean checkusername(){
        Model.User user;
        DBConnection dbobject = new DBConnection();

        user = dbobject.getUser(request.getUserName()); //get user from database based on username of fill request
        dbobject.CloseConnection();
        if(user.getUserName() != null){
            return false;
            //throw user existx
        }
        else{
            return true;
        }
    }

    /**
     * checks if fields in Register Request are valid.
     * @return : return true if fields valid, false otherwise
     */
    public boolean checkfields(){
        if(request.getPassword()!= null && !request.getPassword().equals("")){
            if(request.getUserName()!= null && !request.getUserName().equals("")){
                if(request.getEmail()!= null && !request.getEmail().equals("")){
                    if(request.getfirstName()!= null && !request.getfirstName().equals("")){
                        if(request.getGender()!= null && !request.getGender().equals("")){
                            if(request.getlastName()!= null && !request.getlastName().equals("")){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //throw missing field
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * services register request by returning AuthToken Object
     * @return
     */
    public AuthToken serviceregister(){
        AuthToken toke = new AuthToken();
        Model.Person pers = new Model.Person(); //create user and person objects to enter into database
        Model.User user = new Model.User();
        if(checkusername() && checkfields()){ //if fields are valid:
            user.addregisterdata(request); //add data to user object from request object
            DBConnection dbobject = new DBConnection();
            dbobject.addUser(user); //add objects to database
            toke.setUserName(user.getUserName());
            toke.setDateTime(Long.toString(System.currentTimeMillis()));
            dbobject.addAuthToken(toke);
            dbobject.CloseConnection();
            FillRequest request = new FillRequest();
            int numgenerations = 4;
            String username = toke.getUserName();
            GenerateData gen = new GenerateData(4, username);
            gen.generate(username);
        }
        else{
            //throw error
        }
        return toke; //return authtoken for user
    }



}
