package DatabaseAccess;

import java.sql.*;

/**
 * Created by Greg on 5/19/2017.
 */

public class EventDAO {

    /**
     * Returns event object to be used in other classes
     * @param eventID : The eventID to query the event table and extract attributes with
     * @param conn : The database connection
     * @return : returns event object
     */
    public Model.Event getEvent(String eventID, Connection conn){
        Model.Event ev = new Model.Event();
        String sql = "SELECT eventid, username, personid, eventtype, latitude, longitude, country, year, city FROM Event WHERE eventid = \'" + eventID + "\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            ev.setEventID(rs.getString("eventid"));
            ev.setDescendant(rs.getString("username"));
            ev.setPersonID(rs.getString("personid"));
            ev.setEventType(rs.getString("eventtype"));
            ev.setLatitude(rs.getString("latitude"));
            ev.setLongitude(rs.getString("longitude"));
            ev.setCountry(rs.getString("country"));
            ev.setCity(rs.getString("city"));
            ev.setYear(rs.getString("year"));
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        return ev;
    }

    /**
     * Adds a event to event table
     * @param eve : event object to extract attributes from
     * @param conn : database connection
     */
    public void addEvent(Model.Event eve, Connection conn){
        String sql = "Insert INTO Event(eventid, username, personid, eventtype"; //first part of sql statement (columns to add to)
        String values = " VALUES(\"" + eve.getEventID() + "\", \"" + eve.getDescendant() + "\", \"" + eve.getPersonID()
                + "\", \"" + eve.getEventType() + "\""; // second part of sql statement (values to add)
        StringBuilder sbsql = new StringBuilder(sql);
        StringBuilder sbvals = new StringBuilder(values);
        if(eve.getLatitude()!= null){
            sbsql.append(", latitude");
            sbvals.append(",\"" + eve.getLatitude());
        }
        if(eve.getLongitude() != null){
            sbsql.append(", longitude");
            sbvals.append("\",\"" + eve.getLongitude());
        }
        if(eve.getCountry() != null){
            sbsql.append(", country");
            sbvals.append("\",\"" + eve.getCountry());
        }
        if(eve.getCity() != null){
            sbsql.append(", city");
            sbvals.append("\",\"" + eve.getCity());
        }
        if(eve.getYear() != null){
            sbsql.append(", year");
            sbvals.append("\",\"" + eve.getYear());
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
        String sql = "DELETE FROM Event \n WHERE username = \'" + username + "\'";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); //prepare sql statement
            pstmt.execute();
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Model.Event[] getancesetorsevents(String username, Connection conn){
        Model.Event ev;
        Model.Event events[];
        int count = 0;

        String sql = "SELECT eventid, username, personid, eventtype, latitude, longitude, country, year, city FROM Event WHERE username = \'" + username + "\'";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs2  = stmt.executeQuery("SELECT COUNT(*)  AS count FROM Event WHERE username = \'" + username  + "\'");
            int amount = rs2.getInt("count");
            ResultSet rs    = stmt.executeQuery(sql);
            events = new Model.Event[amount];
            while(rs.next()) {
                ev = new Model.Event();
                ev.setEventID(rs.getString("eventid"));
                ev.setDescendant(rs.getString("username"));
                ev.setPersonID(rs.getString("personid"));
                ev.setEventType(rs.getString("eventtype"));
                ev.setLatitude(rs.getString("latitude"));
                ev.setLongitude(rs.getString("longitude"));
                ev.setCountry(rs.getString("country"));
                ev.setCity(rs.getString("city"));
                ev.setYear(rs.getString("year"));
                events[count] = ev;
                count++;
            }
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
            events = new Model.Event[0];
        }
        return events;
    }


    /**
     * Function to return last value stored in id column of event table
     * @param conn : database connection object
     * @return  : returns last value stored in eventid column
     */
    public String getlastid(Connection conn){
        String value = "";
        String sql = "SELECT eventid FROM Event ORDER BY rowid DESC LIMIT 1;";
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            value = rs.getString("eventid");
        }catch(java.sql.SQLException e){
            System.out.println(e.getMessage());
            return "0";
        }
        return value;
    }

}
