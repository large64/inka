package windows;

import Card.Card;
import inka.database.DatabaseHandler;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author large64
 */
public final class ManageCardsWindow extends JPanel {
    private List<Card> cards;
    private DatabaseHandler databaseHandler;
    private JPanel innerJPanel;

    public JPanel getInnerJPanel() {
        return innerJPanel;
    }

    public void setInnerJPanel(JPanel innerJPanel) {
        this.innerJPanel = innerJPanel;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }
    
    public ManageCardsWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setDatabaseHandler(new DatabaseHandler());
        this.setInnerJPanel(new JPanel());
        
        this.setCards(this.getDatabaseHandler().select("SELECT * FROM cards"));
        
        for (int i = 0; i < cards.size(); ++i) {
            JPanel cardHolder = new JPanel();
            GridLayout gridLayout = new GridLayout(1, 3);
            gridLayout.setHgap(10);
            cardHolder.setLayout(gridLayout);
            
            // add ID
            JTextPane idPane = new JTextPane();
            idPane.setText(Integer.toString(this.getCards().get(i).getId()));
            cardHolder.add(idPane);
            
            // add en, hu
            JTextPane huPane = new JTextPane();
            huPane.setText(this.getCards().get(i).getHungarian());
            cardHolder.add(huPane);
            
            JTextPane enPane = new JTextPane();
            enPane.setText(this.getCards().get(i).getEnglish());    
            cardHolder.add(enPane);
            
            // add gridLayout to window
            this.getInnerJPanel().add(cardHolder);
        }
        // make list scrollable
        JScrollPane jScrollPane = new JScrollPane(this.getInnerJPanel());
        this.getInnerJPanel().setLayout(new BoxLayout(this.getInnerJPanel(), BoxLayout.Y_AXIS));
        jScrollPane.setPreferredSize(new Dimension(750, 550));
        this.add(jScrollPane);
    }
}
