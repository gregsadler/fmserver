package DatabaseAccess;

/**
 * Created by Greg on 5/19/2017.
 */

import java.sql.*;

public class UserDAO {

    /**
     * Queries Database and creates User object to return to Service request
     * @param userName   : The UserName, primary key of the User table
     * @param conn    : The database Connection
     * @return         : returns User object
     */
   public Model.User getUser(String userName, Connection conn){
       Model.User thisuser = new Model.User();
       String sql = "SELECT username, password, personid, email, firstname, lastname, gender FROM User WHERE username = \'" + userName + "\'";
       try{
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql);
           thisuser.setUserName(rs.getString("username"));
           thisuser.setPassword(rs.getString("password"));
           thisuser.setpersonID(rs.getString("personid"));
           thisuser.setEmail(rs.getString("email"));
           thisuser.setfirstName(rs.getString("firstname"));
           thisuser.setlastName(rs.getString("lastname"));
           thisuser.setGender(rs.getString("gender"));
       }catch(java.sql.SQLException e){
           System.out.println(e.getMessage());
       }
       return thisuser;
   }


    /**
     * Adds a User to the User table
     * @param thisuser User object to extract attributes from
     * @param conn  Database Connection
     */
   public void addUser(Model.User thisuser, Connection conn){
       String sql = "Insert INTO User(username, password, personid, email, firstname, lastname, gender) "; //first part of sql statement (columns to add to)
       String values = " VALUES(\"" + thisuser.getUserName() + "\",\"" + thisuser.getPassword() + "\",\"" +
               thisuser.getpersonID() + "\",\"" + thisuser.getEmail() + "\",\"" + thisuser.getfirstName()
               + "\",\"" + thisuser.getlastName() + "\",\"" + thisuser.getGender() + "\")"; // second part of sql statement (values to add)
       sql = sql + values;
       try {
           PreparedStatement pstmt = conn.prepareStatement(sql); //prepare sql statement
           pstmt.execute();
       }catch(java.sql.SQLException e){
           System.out.println(e.getMessage());
       }
   }

    /*
    public boolean checkusername(String username, Connection conn){
       String user;
        Model.User thisuser = new Model.User();
        String sql = "SELECT username FROM User WHERE username = \'" + username + "\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            user = rs.getString("username");
            return !(user==null);
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    */


}
