package windows.cardManager.actionListeners;

import inka.database.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import windows.cardManager.ManageCardsWindow;

/**
 *
 * @author large64
 */
public final class SaveNewCardListener implements ActionListener {
    private JTextField huMeaning;
    private JTextField enMeaning;
    private DatabaseHandler databaseHandler;

    public JTextField getHuMeaning() {
        return huMeaning;
    }

    public void setHuMeaning(JTextField huMeaning) {
        this.huMeaning = huMeaning;
    }

    public JTextField getEnMeaning() {
        return enMeaning;
    }

    public void setEnMeaning(JTextField enMeaning) {
        this.enMeaning = enMeaning;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }
    
    public SaveNewCardListener(JTextField enMeaning, JTextField huMeaning, DatabaseHandler databaseHandler) {
        this.setHuMeaning(huMeaning);
        this.setEnMeaning(enMeaning);
        this.setDatabaseHandler(databaseHandler);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enMeaningString = this.getEnMeaning().getText();
        String huMeaningString = this.getHuMeaning().getText();
        
        if (!enMeaningString.equals("") && !huMeaningString.equals("")) {
            String[] queries = {"INSERT INTO cards ('en', 'hu') VALUES ('" + this.getEnMeaning().getText() + "', '" + this.getHuMeaning().getText() + "');"};
            this.getDatabaseHandler().query(queries);
            System.out.println(queries[0]);
            ManageCardsWindow.paintWindow(false);
        }
        else {
            JOptionPane.showMessageDialog(ManageCardsWindow.getInnerJPanel(), "None of the fields can be empty.");
        }
    }
    
}
