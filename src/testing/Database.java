package testing;

import inka.database.DatabaseHandler;

/**
 *
 * @author large64
 */
public class Database {
    public static void main(String[] args) {
        // get all rows from cards
        DatabaseHandler.selectCards("SELECT * FROM cards");
        
        // construct an array of queries
        String[] queries = {"INSERT INTO cards ('en', 'hu') VALUES ('bag', 'táska')"};
        // perform all queries inside the array
        DatabaseHandler.query(queries);
        // check if insertion was successful
        DatabaseHandler.selectCards("SELECT * FROM cards WHERE en = 'bag'");
        
        String[] updateQuery = {"UPDATE cards SET hu = 'szatyor' WHERE en = 'bag'"};
        DatabaseHandler.query(updateQuery);
        // check if updating was successful
        DatabaseHandler.selectCards("SELECT * FROM cards WHERE en = 'bag'");
        
        String[] deleteQuery = {"DELETE FROM cards WHERE en = 'bag'"};
        // delete all rows where en = bag
        DatabaseHandler.query(deleteQuery);
        DatabaseHandler.selectCards("SELECT * FROM cards");
    }
}
