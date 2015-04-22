/**
 * This file helps with the handling of your sqlite database.
 * 
*/
package inka.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author large64
 */
public class DatabaseHandler {
    // initialize connection
    private Connection c = null;
    private Statement statement = null;
    
    public DatabaseHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:Inka.db");
            // disable autocommiting after each operation,
            // this way, we have to do it manually
            this.c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Database connection opened successfully.");
    }
}
