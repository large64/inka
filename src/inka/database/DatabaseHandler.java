/**
 * This file helps with the handling of your sqlite database.
 * 
*/
package inka.database;

import Card.Card;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sentence.Grammar;

/**
 *
 * @author large64
 */
public class DatabaseHandler {
    // initialize connection
    private static Connection c = null;
    private static Statement statement = null;
    
    public DatabaseHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Inka.db");
            // disable autocommiting after each operation,
            // this way, we have to do it manually
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Database connection opened successfully.");
    }
    
    public static List selectCards(String query) {
        List<Card> results = new ArrayList<>();
        try {
            statement = c.createStatement();
            // execute query provided by user
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String english = resultSet.getString("en");
                String hungarian = resultSet.getString("hu");
                
                Card card = new Card(id, english, hungarian);
                results.add(card);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return results;
    }
    
    public static List selectGrammar(String query) {
        List<Grammar> results = new ArrayList<>();
        try {
            statement = c.createStatement();
            // execute query provided by user
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String sentence = resultSet.getString("sentence");
                String opt1 = resultSet.getString("opt1");
                String opt2 = resultSet.getString("opt2");
                String opt3 = resultSet.getString("opt3");
                int correct = resultSet.getInt("correct");
                
                Grammar grammar = new Grammar(id, sentence, opt1, opt2, opt3, correct);
                results.add(grammar);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return results;
    }
    
    // Use this function to perform all types of queries, eg. insert, update, delete etc.
    public static boolean query(String[] queries) {
        try {
            statement = c.createStatement();
        
            for (int i = 0; i < queries.length; ++i) {
                statement.executeUpdate(queries[i]);
            }
            
            c.commit();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static void close() {
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
