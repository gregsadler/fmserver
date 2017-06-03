package DBase;

import java.sql.*;

/**
 * Created by Greg on 5/18/2017.
 * Class to create tables if not already in database
 */
public class tableconstructor {

    tableconstructor(){};

    /**
     * Function to write SQL code in and construct tables in
     */
    public static void createtables(){

        /**
         * Address of database
         */
        String url = "jdbc:sqlite:Testdb.sqlite";

        // SQL statement for creating a new table
        String sql1 = "CREATE TABLE IF NOT EXISTS User (\n"
                + "	username varchar(255) NOT NULL,\n"
                + " password varchar(255) NOT NULL,\n"
                + " email varchar(255) NOT NULL,\n"
                + " firstname varchar(255) NOT NULL,\n"
                + " lastname varchar(255) NOT NULL,\n"
                + " gender varchar(255) NOT NULL,\n"
                + "	personid varchar(255) NOT NULL\n"
                + ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS Person (\n"
                + "	personid varchar(255) NOT NULL,\n"
                + "	descendant varchar(255) NOT NULL,\n"
                + " firstname varchar(255) NOT NULL,\n"
                + " lastname varchar(255) NOT NULL,\n"
                + " gender varchar(255) NOT NULL,\n"
                + "	fatherid varchar(255),\n"
                + "	motherid varchar(255),\n"
                + "	spouseid varchar(255)\n"
                + ");";

        String sql3 = "CREATE TABLE IF NOT EXISTS Event (\n"
                + "	eventid varchar(255) NOT NULL,\n"
                + " username varchar(255) NOT NULL,\n"
                + " personid varchar(255) NOT NULL,\n"
                + " latitude varchar(255),\n"
                + " longitude varchar(255),\n"
                + " country varchar(255),\n"
                + " city varchar(255),\n"
                + " eventtype varchar(255) NOT NULL,\n"
                + " year varchar(255)"
                + ");";

        String sql4 = "CREATE TABLE IF NOT EXISTS AuthToken (\n"
                + "	authid varchar(255) NOT NULL,\n"
                + " username varchar(255) NOT NULL,\n"
                + "	logintime varchar(255) NOT NULL"
                + ");";
        wrapcreate(url, sql1);
        wrapcreate(url, sql2);
        wrapcreate(url, sql3);
        wrapcreate(url, sql4);
    }


    /**
     * the function that gets connection and creates tables in database.
     * @param url : address of database
     * @param sql : sql statement to execute
     */
    private static void wrapcreate(String url, String sql){
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
