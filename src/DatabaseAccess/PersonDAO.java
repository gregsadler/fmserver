package DatabaseAccess;

import java.sql.*;

/**
 * Created by Greg on 5/19/2017.
 */

public class PersonDAO {

    /**
     * Gets Person Object from database based on a personID
     * @param personID : The personID, the primary key of the Person Table
     * @param conn  : The Database connection
     * @return   : Return person object of attributes extracted from Person Table
     */
    public Model.Person getPerson(String personID, Connection conn){
        Model.Person pers = new Model.Person();
        String sql = "SELECT personid, descendant, firstname, lastname, gender, fatherid, motherid, spouseid FROM Person WHERE personid = \'" + personID + "\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            pers.setpersonID(rs.getString("personid"));
            pers.setDescendant(rs.getString("descendant"));
            pers.setFirstName(rs.getString("firstname"));
            pers.setLastName(rs.getString("lastname"));
            pers.setGender(rs.getString("gender"));
            pers.setFather(rs.getString("fatherid"));
            pers.setMother(rs.getString("motherid"));
            pers.setSpouse(rs.getString("spouseid"));
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        return pers;
    }


    /**
     * Adds a Person object to Person table in database
     * @param thispers : Person object to extract attributes from
     * @param conn  : Database Connection
     */
    public void addPerson(Model.Person thispers, Connection conn){
        String sql = "Insert INTO Person(personid, descendant, firstname, lastname, gender"; //first part of sql statement (columns to add to)
        String values = " VALUES(\"" + thispers.getpersonID() + "\",\"" + thispers.getDescendant() + "\",\"" + thispers.getFirstName()
                + "\",\"" + thispers.getLastName() + "\",\"" + thispers.getGender(); // second part of sql statement (values to add)
        StringBuilder sbsql = new StringBuilder(sql);
        StringBuilder sbvals = new StringBuilder(values);
        if(thispers.getFather()!= null){
            sbsql.append(", fatherid");
            sbvals.append("\",\"" + thispers.getFather());
        }
        if(thispers.getMother() != null){
            sbsql.append(", motherid");
            sbvals.append("\",\"" + thispers.getMother());
        }
        if(thispers.getSpouse() != null){
            sbsql.append(", spouseid");
            sbvals.append("\",\"" + thispers.getSpouse());
        }
        sbsql.append(") ");
        sbvals.append("\")");
        sbsql.append(sbvals.toString());
        sql = sbsql.toString();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); //prepare sql statement
            pstmt.execute();
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Delete all data in Event table relating to the given user
     * @param username : Username to delete info for
     * @param conn  : database connection
     */
    public void deleteUserinfo(String username, Connection conn){
        String sql = "DELETE FROM Person WHERE descendant = \'" + username + "\'";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); //prepare sql statement
            pstmt.execute();
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets array of person objects who are ancestors of current user
     * @param username : username of current user
     * @param conn : database connection
     * @return : array of person objects
     */
    public Model.Person[] getancesetors(String username, Connection conn) {
        Model.Person pers;
        int count = 0;
        Model.Person[] family;
        String sql = "SELECT personid, descendant, firstname, lastname, gender, fatherid, motherid, spouseid FROM Person WHERE descendant = \'" + username + "\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs2  = stmt.executeQuery("SELECT COUNT(*)  AS count FROM Person WHERE descendant = \'" + username  + "\'");
            int amount = rs2.getInt("count");
            ResultSet rs  = stmt.executeQuery(sql);
            family = new Model.Person[amount];
            while(rs.next()) { //querying database and extracting each row as an object
                pers = new Model.Person();
                pers.setpersonID(rs.getString("personid"));
                pers.setDescendant(rs.getString("descendant"));
                pers.setFirstName(rs.getString("firstname"));
                pers.setLastName(rs.getString("lastname"));
                pers.setGender(rs.getString("gender"));
                pers.setFather(rs.getString("fatherid"));
                pers.setMother(rs.getString("motherid"));
                pers.setSpouse(rs.getString("spouseid"));
                family[count] = pers;
                count++;
            }
            return family;
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        family = new Model.Person[0];
        return family;
    }

    /**
     * @param conn : database connection object
     * @return  : return string of last value that was recorded in person table
     */
    public String getlastid(Connection conn){
        String value = "";
        String sql = "SELECT personid FROM Person ORDER BY rowid DESC LIMIT 1;";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            value = rs.getString("personid");
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
            return "0";
        }
        return value;
    }


}
