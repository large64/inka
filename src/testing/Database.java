package testing;

import inka.database.DatabaseHandler;

/**
 *
 * @author large64
 */
public class Database {
    public static void main(String[] args) {
        DatabaseHandler handler = new DatabaseHandler();
        // get all rows from cards
        handler.select("SELECT * FROM cards");
        
        // construct an array of queries
        String[] queries = {"INSERT INTO cards ('en', 'hu') VALUES ('bag', 'táska')"};
        // perform all queries inside the array
        handler.query(queries);
        // check if insertion was successful
        handler.select("SELECT * FROM cards WHERE en = 'bag'");
        
        String[] updateQuery = {"UPDATE cards SET hu = 'szatyor' WHERE en = 'bag'"};
        handler.query(updateQuery);
        // check if updating was successful
        handler.select("SELECT * FROM cards WHERE en = 'bag'");
        
        String[] deleteQuery = {"DELETE FROM cards WHERE en = 'bag'"};
        // delete all rows where en = bag
        handler.query(deleteQuery);
        handler.select("SELECT * FROM cards");
    }
}
