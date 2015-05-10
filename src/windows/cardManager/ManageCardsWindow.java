package windows.cardManager;

import windows.cardManager.actionListeners.DeleteActionListener;
import windows.cardManager.actionListeners.SaveActionListener;
import windows.cardManager.actionListeners.ChangeWindowListener;
import Card.Card;
import inka.database.DatabaseHandler;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author large64
 */
public final class ManageCardsWindow extends JPanel {
    private static List<Card> cards;
    private static DatabaseHandler databaseHandler;
    private static JPanel innerJPanel;
    private static JScrollPane jScrollPane;

    public static JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public static void setjScrollPane(JScrollPane jScrollPane) {
        ManageCardsWindow.jScrollPane = jScrollPane;
    }

    public static JPanel getInnerJPanel() {
        return innerJPanel;
    }

    public static void setInnerJPanel(JPanel innerJPanel) {
        ManageCardsWindow.innerJPanel = innerJPanel;
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static void setCards(List<Card> cards) {
        ManageCardsWindow.cards = cards;
    }

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static void setDatabaseHandler(DatabaseHandler databaseHandler) {
        ManageCardsWindow.databaseHandler = databaseHandler;
    }
    
    public ManageCardsWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ManageCardsWindow.setDatabaseHandler(new DatabaseHandler());
        ManageCardsWindow.setInnerJPanel(new JPanel());
        ManageCardsWindow.getInnerJPanel().setLayout(new BoxLayout(ManageCardsWindow.getInnerJPanel(), BoxLayout.Y_AXIS));
        ManageCardsWindow.getInnerJPanel().setBorder(new EmptyBorder(10, 20, 20, 10));
        
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
        addButton("Menu", menuPanel, new ChangeWindowListener(), "goToMenuButton");
        addButton("Ask", menuPanel, new ChangeWindowListener(), "goToAskButton");
        addButton("Grammar", menuPanel, new ChangeWindowListener(), "goToGrammarButton");
        this.add(menuPanel);
        
        // Add card panel
        JPanel addCardPanel = new JPanel(new FlowLayout());
        addCardPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        addButton("Add new card", addCardPanel, null, "addNewCardButton");
        this.add(addCardPanel);
        
        ManageCardsWindow.getInnerJPanel().setBorder(new BasicBorders.MarginBorder());
        ManageCardsWindow.setjScrollPane(new JScrollPane(ManageCardsWindow.getInnerJPanel()));
        ManageCardsWindow.getjScrollPane().setPreferredSize(new Dimension(750, 550));
        this.add(jScrollPane);
        
        paintWindow();
    }
    
    public static void paintWindow() {
        ManageCardsWindow.setCards(ManageCardsWindow.getDatabaseHandler().select("SELECT * FROM cards"));
        
        if (ManageCardsWindow.getCards().isEmpty()) {
            JLabel noCards = new JLabel("No cards yet.");
            noCards.setAlignmentX(CENTER_ALIGNMENT);
            noCards.setBorder(new EmptyBorder(10, 0, 0, 0));
            ManageCardsWindow.getInnerJPanel().add(noCards);
        }
        else {
            ManageCardsWindow.getInnerJPanel().removeAll();
            // Upload the window with cards
            for (int i = 0; i < cards.size(); ++i) {
                int cardId = ManageCardsWindow.getCards().get(i).getId();
                String enString = ManageCardsWindow.getCards().get(i).getEnglish();
                String huString = ManageCardsWindow.getCards().get(i).getHungarian();

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
                SaveActionListener saveActionListener =
                        new SaveActionListener(
                                ManageCardsWindow.getDatabaseHandler(),
                                new WordPair(enString, huString),
                                new WordPair(enPane, huPane)
                        );
                DeleteActionListener deleteActionListener = new DeleteActionListener(ManageCardsWindow.getCards().get(i).getId(), ManageCardsWindow.getDatabaseHandler());
                // add delete and save buttons
                addButton("Save", buttonPanel, saveActionListener, Integer.toString(cardId));
                addButton("Delete", buttonPanel, deleteActionListener, "Delete");
                cardHolder.add(buttonPanel);

                // add gridLayout to window
                ManageCardsWindow.getInnerJPanel().add(cardHolder);
            }
        }
        // make list scrollable
        
        // repaint if content has been modified (eg. a Card has been deleted)
        ManageCardsWindow.getInnerJPanel().revalidate();
        ManageCardsWindow.getInnerJPanel().repaint();
        ManageCardsWindow.getjScrollPane().revalidate();
        ManageCardsWindow.getjScrollPane().repaint();
    }
    
    private static void addButton(String text, Container container, ActionListener actionListener, String name){
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setName(name);
        container.add(button);
    }
}
