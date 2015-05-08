package windows;

import Card.Card;
import inka.Inka;
import inka.database.DatabaseHandler;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;

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
        this.getInnerJPanel().setLayout(new BoxLayout(this.getInnerJPanel(), BoxLayout.Y_AXIS));
        this.getInnerJPanel().setBorder(new EmptyBorder(10, 20, 20, 10));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Card manager");
        title.setFont(new Font("Verdana", 1, 20));
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.add(title);
        this.add(titlePanel);
        
        
        //Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        addButton("Menu", menuPanel, new changeWindowlListener(), "goToMenuButton");
        addButton("Ask", menuPanel, new changeWindowlListener(), "goToAskButton");
        addButton("Grammar", menuPanel, new changeWindowlListener(), "goToGrammarButton");
        this.add(menuPanel);
        
        // Add card panel
        JPanel addCardPanel = new JPanel(new FlowLayout());
        addCardPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        addButton("Add new card", addCardPanel, null, "addNewCardButton");
        this.add(addCardPanel);
        
        this.setCards(this.getDatabaseHandler().select("SELECT * FROM cards"));
        
        if (this.getCards().isEmpty()) {
            JLabel noCards = new JLabel("No cards yet.");
            noCards.setAlignmentX(CENTER_ALIGNMENT);
            noCards.setBorder(new EmptyBorder(10, 0, 0, 0));
            this.getInnerJPanel().add(noCards);
        }
        else {
            // Upload the window with cards
            for (int i = 0; i < cards.size(); ++i) {
                int cardId = this.getCards().get(i).getId();
                String enString = this.getCards().get(i).getEnglish();
                String huString = this.getCards().get(i).getHungarian();

                JPanel cardHolder = new JPanel();
                GridLayout gridLayout = new GridLayout(1, 4);
                gridLayout.setHgap(10);
                gridLayout.setVgap(5);
                cardHolder.setLayout(gridLayout);

                // add ID
                JPanel idPanel = new JPanel(new FlowLayout());
                idPanel.setBackground(new Color(221, 221, 221));
                idPanel.setBorder(new BasicBorders.FieldBorder(Color.WHITE, Color.WHITE, Color.lightGray, Color.lightGray));
                JLabel idPane = new JLabel();
                idPane.setText(Integer.toString(cardId));
                idPanel.add(idPane);
                cardHolder.add(idPanel);

                // add en, hu
                JTextField huPane = new JTextField();
                huPane.setText(huString);
                cardHolder.add(huPane);

                JTextField enPane = new JTextField();
                enPane.setText(enString);    
                cardHolder.add(enPane);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout());
                // add action listener to button, this way the clicked button will know
                // which row to modify in the database
                saveActionListener saveActionListener =
                        new saveActionListener(
                                this.getDatabaseHandler(),
                                new WordPair(enString, huString),
                                new WordPair(enPane, huPane)
                        );
                // add save button
                addButton("Save", buttonPanel, saveActionListener, Integer.toString(cardId));
                addButton("Delete", buttonPanel, saveActionListener, "Delete");
                cardHolder.add(buttonPanel);

                // add gridLayout to window
                this.getInnerJPanel().add(cardHolder);
            }
        }
        // make list scrollable
        this.getInnerJPanel().setBorder(new BasicBorders.MarginBorder());
        JScrollPane jScrollPane = new JScrollPane(this.getInnerJPanel());
        jScrollPane.setPreferredSize(new Dimension(750, 550));
        this.add(jScrollPane);
    }
    
    private static void addButton(String text, Container container, ActionListener actionListener, String name){
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setName(name);
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
    
    private final class saveActionListener implements ActionListener {
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
        
        public saveActionListener(DatabaseHandler databaseHandler, WordPair oldPair, WordPair newPair) {
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
                this.getDatabaseHandler1().query(queryStrings);
            }
            // if only en have been modified
            else if (!oldEnString.equals(newEnString) && oldHuString.equals(oldHuString)) {
                String[] queryStrings = {"UPDATE cards set en='" + newEnString + "' WHERE en='" + oldEnString + "';"};
                this.getDatabaseHandler1().query(queryStrings);
            }
            // if only hu have been modified
            else if (!oldHuString.equals(newHuString) && oldEnString.equals(oldEnString)) {
                String[] queryStrings = {"UPDATE cards set hu='" + newHuString + "' WHERE hu='" + oldHuString + "';"};
                this.getDatabaseHandler1().query(queryStrings);
            }
        }
    }
    
    private final class WordPair {
        public String en;
        public String hu;
        
        public JTextField enTextField;
        public JTextField huTextField;
        
        public WordPair(String en, String hu) {
            this.en = en;
            this.hu = hu;
            this.enTextField = null;
            this.huTextField = null;
        }
        
        public WordPair(JTextField en, JTextField hu) {
            this.enTextField = en;
            this.huTextField = hu;
            this.hu = null;
            this.en = null;
        }
    }
}
