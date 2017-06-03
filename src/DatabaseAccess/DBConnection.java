package DatabaseAccess;
import java.sql.*;

import Services.AncestorsResult;
import Services.EventResult;

/**
 * Created by Greg on 5/19/2017.
 */

public class DBConnection {
    /**
     * Database access object to modify the User Table
     */
    private UserDAO udao = new UserDAO();
    /**
     * Database access object to modify the Person Table
     */
    private PersonDAO pdao = new PersonDAO();
    /**
     * Database access object to modify the Event Table
     */
    private EventDAO edao = new EventDAO();
    /**
     * Database access object to modify the AuthToken Table
     */
    private AuthTokenDAO adao = new AuthTokenDAO();

    /**
     * Database connection to be used by all Database Access Objects
     */
    private Connection conn;


    String URL = "jdbc:sqlite:Testdb.sqlite";

    public DBConnection(){
        OpenConnection();
    }




    /**
     * Sets conn to the opened Database connection
     * return void
     */
    public void OpenConnection(){
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    /**
     * Closes the connection conn.
     */
    public void CloseConnection(){
        if(conn != null){
            try {
                conn.close();
            }catch(java.sql.SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }



    /**
     * Clears Database from the connection
     * @return
     */
    public boolean ClearDB(){
        boolean error = true;
        String sql = "DELETE FROM User";
        String sql1 = "DELETE FROM Person";
        String sql2 = "DELETE FROM Event";
        String sql3 = "DELETE FROM AuthToken";

        if(conn!= null){
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql); //prepared statements for sql execution
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                pstmt.executeUpdate();
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
            }catch(java.sql.SQLException e){
                System.out.println(e.getMessage());
                return false;
            }
            //clear contents of all tables
        }
        return true;
    }


    /**
     * Queries Database and creates User object to return to Service request
     * @param userName   : The UserName, primary key of the User table
     * @return   : returns User object
     */
    public Model.User getUser(String userName){
            return udao.getUser(userName, conn);
    }




    /**
     * Adds a User to the User table
     * @param thisuser User object to extract attributes from
     */
    public void addUser(Model.User thisuser)
    {
        String idp = getlastpersonid();
        if(idp != null && !idp.equals("")) {
            try {
                int next = Integer.parseInt(idp);
                next++;
                thisuser.setpersonID(Integer.toString(next));
            } catch(NumberFormatException e){
                thisuser.setpersonID(Integer.toString((int)(Math.random()*100+100)));
            }catch(NullPointerException e) {
                thisuser.setpersonID(Integer.toString((int)(Math.random()*100+100)));
            }
        }
        else{
            thisuser.setpersonID("0");
        }
        udao.addUser(thisuser, conn);
    }




    /**
     * Gets Person Object from database based on a personID
     * @param personID : The personID, the primary key of the Person Table
     * @return   : Return person object of attributes extracted from Person Table
     */
    public Model.Person getPerson(String personID){
            return pdao.getPerson(personID, conn);
    }




    /**
     * Adds a Person object to Person table in database
     * @param pers : Person object to extract attributes from
     */
    public void addPerson(Model.Person pers){
        String idp = getlastpersonid();
        if(idp != null && !idp.equals("")) {
            try {
                int next = Integer.parseInt(idp);
                next++;
                pers.setpersonID(Integer.toString(next));
            }catch(NumberFormatException e){
                pers.setpersonID(Integer.toString((int)(Math.random()*100+100)));
            }catch(NullPointerException e) {
                pers.setpersonID(Integer.toString((int)(Math.random()*100+100)));
            }
        }
        else{
            pers.setpersonID("0");
        }
        pdao.addPerson(pers, conn);
    }




    /**
     * Adds a event to event table
     * @param event : event object to extract attributes from
     */
    public void addevent(Model.Event event){
        String idp = getlasteventid();
        if(idp != null && !idp.equals("")) {
            try {
                int next = Integer.parseInt(idp);
                next++;
                event.setEventID(Integer.toString(next));
            }catch(NumberFormatException e){
                event.setEventID(Integer.toString((int)(Math.random()*100+100)));
            }catch(NullPointerException e) {
                event.setEventID(Integer.toString((int)(Math.random()*100+100)));
            }
        }
        else{
            event.setEventID("0");
        }
        event.setEventID();
        edao.addEvent(event, conn);
    }




    /**
     * Returns Authtoken object
     * @param userName : the Username of the user, used to query AuthToken table
     * @return  : return Authtoken object to be used by service classes
     */
    public Model.AuthToken getAuthToken(String userName){
        return adao.getAuthToken(userName, conn);
    }

    /**
     * Returns Authtoken object
     * @param authtoke : the current authoke of the user, used to query AuthToken table
     * @return  : return Authtoken object to be used by service classes
     */
    public Model.AuthToken getAuthTokentoke(String authtoke){
        return adao.getAuthTokentoke(authtoke,conn);
    }



    /**
     * Adds Authtoken to Authtoken table
     * @param auth : Authtoken object from which attributes are extracted from
     */
    public void addAuthToken(Model.AuthToken auth){
        String idp = getlastauthid();
        if(idp != null && !idp.equals("")) {
            try {
                int next = Integer.parseInt(idp);
                next++;
                auth.setAuthToken(Integer.toString(next));
            }catch(NumberFormatException e){
                auth.setAuthToken(Integer.toString((int)(Math.random()*100+100)));
            }catch(NullPointerException e) {
                auth.setAuthToken(Integer.toString((int)(Math.random()*100+100)));
            }
        }
        else{
            auth.setAuthToken("0");
        }
        adao.addAuthToken(auth, conn);
    }




    /**
     * Returns event object to be used in other classes
     * @param eventID : The eventID to query the event table and extract attributes with
     * @return : returns event object
     */
    public Model.Event getEvent(String eventID){
        return edao.getEvent(eventID, conn);
    }



    /**
     * deletes all events and persons related to the given user.
     * @param username : given username
     */
    public void deleteuserdata(String username){
        edao.deleteUserinfo(username, conn);
        pdao.deleteUserinfo(username, conn);
    }

    /**
     * Get ancestor object to return with array of ancestors
     * @param authtoken : authtoken of current user.
     * @return
     */
    public Services.AncestorsResult getAncestors(String authtoken){
        Services.AncestorsResult ancestors = new AncestorsResult();
        String username;
        username = getAuthTokentoke(authtoken).getUserName();
        ancestors.addarray(pdao.getancesetors(username, conn));
        return ancestors;
    }


    /**
     * Get events of all ancestors of current user.
     * @param authtoken : authtoken of current user
     * @return : an object filled with event objects
     */
    public Services.EventResult getAncestorsEvents(String authtoken){
        Services.EventResult ancestorsevents = new EventResult();
        String username;
        username = getAuthTokentoke(authtoken).getUserName();
        ancestorsevents.addarray(edao.getancesetorsevents(username, conn));
        return ancestorsevents;
    }

    /**
     * Returns last authtoken in table
     * @return
     */
    public String getlastauthid(){
        return adao.getlastid(conn);
    }

    /**
     * Returns last person id in person table
     * @return
     */
    public String getlastpersonid(){
        return pdao.getlastid(conn);
    }

    /**
     * Returns last event id in person table
     * @return
     */
    public String getlasteventid(){
        return edao.getlastid(conn);
    }

    /*
    public boolean checkusername(String username){
        return udao.checkusername( username, conn);
    }
    */

}
