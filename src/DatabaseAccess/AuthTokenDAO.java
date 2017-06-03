package DatabaseAccess;

import java.sql.*;

/**
 * Created by Greg on 5/19/2017.
 */

public class AuthTokenDAO {

    /**
     * Returns Authtoken object
     * @param userName : the Username of the user, used to query AuthToken table
     * @param conn : The database connection
     * @return  : return Authtoken object to be used by service classes
     */
    public Model.AuthToken getAuthToken(String userName, Connection conn){
        Model.AuthToken auth = new Model.AuthToken();
        String sql = "SELECT authid, username, logintime FROM AuthToken WHERE username = \'" + userName +"\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            auth.setUserName(rs.getString("username"));
            auth.setAuthToken(rs.getString("authid"));
            auth.setDateTime(rs.getString("logintime"));
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        return auth;
    }

    public Model.AuthToken getAuthTokentoke(String authtoken, Connection conn){
        Model.AuthToken auth = new Model.AuthToken();
        String sql = "SELECT authid, username, logintime FROM AuthToken WHERE authid = \'" + authtoken +"\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            auth.setUserName(rs.getString("username"));
            auth.setAuthToken(rs.getString("authid"));
            auth.setDateTime(rs.getString("logintime"));
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        return auth;
    }

    /**
     * Adds Authtoken to Authtoken table
     * @param thisauth : Authtoken object from which attributes are extracted from
     * @param conn : Database Connection
     */
    public void addAuthToken(Model.AuthToken thisauth, Connection conn){
        String sql = "Insert INTO AuthToken(authid, username, logintime) "; //first part of sql statement (columns to add to)
        String values = " VALUES(\"" + thisauth.getAuthToken() + "\",\"" + thisauth.getUserName() + "\",\""
                        + thisauth.getDateTime() + "\")";

        sql = sql + values;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); //prepare sql statement
            pstmt.execute();
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns last authtokenid stored in database
     * @param conn : database connection object
     * @return : last authtoken id as string
     */
    public String getlastid(Connection conn){
        String value = "";
        String sql = "SELECT authid FROM AuthToken ORDER BY authid DESC LIMIT 1;";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            value = rs.getString("authid");
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
            return "";
        }
        return value;
    }

}
