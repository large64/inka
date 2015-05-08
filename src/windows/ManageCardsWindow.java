package windows;

import Card.Card;
import inka.Inka;
import inka.database.DatabaseHandler;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
        
        //Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        
        addButton("Menu", menuPanel, new changeWindowlListener());
        addButton("Ask", menuPanel, new changeWindowlListener());
        addButton("Grammar", menuPanel, new changeWindowlListener());
        this.getInnerJPanel().add(menuPanel);
        
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
    
    private static void addButton(String text, Container container, ActionListener actionListener){
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        container.add(button);        
    }
    
    //This helps to navigate between the windows
    private static class changeWindowlListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();
            System.out.println(e.getActionCommand());
            switch (buttonText) {
                case "Menu":
                    Inka.getWindow().changePanel(0);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Ask":
                    Inka.getWindow().changePanel(1);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Grammar":
                    Inka.getWindow().changePanel(2);
                    Inka.getWindow().setTitle(buttonText);
                    break;
                case "Card Manager":
                    Inka.getWindow().changePanel(3);
                    Inka.getWindow().setTitle(buttonText);
                    break;
            }
        }
    }
}
