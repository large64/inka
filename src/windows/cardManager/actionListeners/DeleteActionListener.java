package windows.cardManager.actionListeners;

import inka.database.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.cardManager.ManageCardsWindow;

/**
 *
 * @author large64
 */
public final class DeleteActionListener implements ActionListener {
    private int id;
    private DatabaseHandler databaseHandler1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DatabaseHandler getDatabaseHandler1() {
        return databaseHandler1;
    }

    public void setDatabaseHandler1(DatabaseHandler databaseHandler1) {
        this.databaseHandler1 = databaseHandler1;
    }

    public DeleteActionListener(int id, DatabaseHandler databaseHandler) {
        this.setId(id);
        this.setDatabaseHandler1(databaseHandler);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] queries = {"DELETE FROM cards WHERE id='" + this.getId() + "';"};
        DatabaseHandler.query(queries);
        ManageCardsWindow.paintWindow(false);
    }
}
