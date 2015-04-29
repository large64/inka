/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import inka.database.DatabaseHandler;

/**
 *
 * @author large64
 */
public class Database {
    public static void main(String[] args) {
        DatabaseHandler handler = new DatabaseHandler();
        handler.select("select * from cards");
        String[] queries = {
            "insert into cards ('en', 'hu') values ('bag', 'táska')",
        };
        handler.query(queries);
        handler.select("select * from cards");
        String[] deleteQuery = {"delete from cards where en = 'bag'"};
        // delete all rows where en = bag
        handler.query(deleteQuery);
        handler.select("select * from cards");
    }
}
