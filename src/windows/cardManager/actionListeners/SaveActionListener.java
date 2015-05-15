package windows.cardManager.actionListeners;

import inka.database.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.cardManager.WordPair;

/**
 *
 * @author large64
 */
public final class SaveActionListener implements ActionListener {
    private WordPair oldPair;
    private WordPair newPair;
    private DatabaseHandler databaseHandler1;

    public DatabaseHandler getDatabaseHandler1() {
        return databaseHandler1;
    }

    public void setDatabaseHandler1(DatabaseHandler databaseHandler1) {
        this.databaseHandler1 = databaseHandler1;
    }

    public WordPair getOldPair() {
        return oldPair;
    }

    public void setOldPair(WordPair oldPair) {
        this.oldPair = oldPair;
    }

    public WordPair getNewPair() {
        return newPair;
    }

    public void setNewPair(WordPair newPair) {
        this.newPair = newPair;
    }

    public SaveActionListener(DatabaseHandler databaseHandler, WordPair oldPair, WordPair newPair) {
        this.setOldPair(oldPair);
        this.setNewPair(newPair);
        this.setDatabaseHandler1(databaseHandler);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oldEnString = this.getOldPair().en;
        String oldHuString = this.getOldPair().hu;

        String newEnString = this.getNewPair().enTextField.getText();
        String newHuString = this.getNewPair().huTextField.getText();

        // if both en and hu have been modified
        if (!oldEnString.equals(newEnString) && !oldHuString.equals(newHuString)) {
            String[] queryStrings = {"UPDATE cards set en='" + newEnString + "', hu='" + newHuString + "' WHERE hu='" + oldHuString + "' AND en='" + oldEnString + "';"};
            DatabaseHandler.query(queryStrings);
        }
        // if only en have been modified
        else if (!oldEnString.equals(newEnString) && oldHuString.equals(oldHuString)) {
            String[] queryStrings = {"UPDATE cards set en='" + newEnString + "' WHERE en='" + oldEnString + "';"};
            DatabaseHandler.query(queryStrings);
        }
        // if only hu have been modified
        else if (!oldHuString.equals(newHuString) && oldEnString.equals(oldEnString)) {
            String[] queryStrings = {"UPDATE cards set hu='" + newHuString + "' WHERE hu='" + oldHuString + "';"};
            DatabaseHandler.query(queryStrings);
        }
    }
}
