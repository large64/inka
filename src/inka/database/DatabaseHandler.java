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
    
    // @TODO: rewrite function to return with List<Card>
    public String select(String query) {
        try {
            this.statement = this.c.createStatement();
            // execute query provided by user
            ResultSet resultSet = this.statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String english = resultSet.getString("en");
                String hungarian = resultSet.getString("hu");
                
                System.out.print("id: " + id + " ");
                System.out.print("en: " + english + " ");
                System.out.print("hu: " + hungarian);
            }
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        // @TODO: return List<Card> object
        return "";
    }
    
    public boolean insert(String[] queries) {
        try {
            this.statement = this.c.createStatement();
        
            for (int i = 0; i < queries.length; ++i) {
                this.statement.executeUpdate(queries[i]);
            }
            
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
