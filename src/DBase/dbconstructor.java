package DBase;

import java.lang.*;
import java.sql.*;

/**
 * Created by Greg on 5/18/2017.
 * My class to create Database if one doesn't already exist and connect to
 */
public class dbconstructor {

    /**
     * Connect to or create a Database
     * @param fileName : filename to connect to or createDatabase
     */
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try {
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Temporary main to test database connection
     * @param args : command line arguments, none used
     */
    public static void main(String[] args){
        String db = "Testdb.sqlite";
        createNewDatabase(db);
        tableconstructor constr = new tableconstructor();
        constr.createtables();
    }
}
