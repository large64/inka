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
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;
import windows.cardManager.actionListeners.SaveNewCardListener;

/**
 *
 * @author large64
 */
public final class ManageCardsWindow extends JPanel {
    private static ArrayList<Card> cards;
    private static DatabaseHandler databaseHandler;
    private static JPanel innerJPanel;
    private static JScrollPane jScrollPane;
    private static JPanel menuPanel;
    private static JPanel addCardPanel;

    public static JPanel getAddCardPanel() {
        return addCardPanel;
    }

    public static void setAddCardPanel(JPanel addCardPanel) {
        ManageCardsWindow.addCardPanel = addCardPanel;
    }

    public static JPanel getMenuPanel() {
        return menuPanel;
    }

    public static void setMenuPanel(JPanel menuPanel) {
        ManageCardsWindow.menuPanel = menuPanel;
    }

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
        ManageCardsWindow.cards = new ArrayList<>(cards);
    }

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static void setDatabaseHandler(DatabaseHandler databaseHandler) {
        ManageCardsWindow.databaseHandler = databaseHandler;
    }
    
    public ManageCardsWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ManageCardsWindow.setInnerJPanel(new JPanel());
        ManageCardsWindow.getInnerJPanel().setLayout(new BoxLayout(ManageCardsWindow.getInnerJPanel(), BoxLayout.Y_AXIS));
        ManageCardsWindow.getInnerJPanel().setBorder(new EmptyBorder(10, 20, 20, 10));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Card Manager");
        title.setFont(new Font("Verdana", 1, 20));
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.add(title);
        this.add(titlePanel);
        
        
        //Menu panel
        setMenuPanel(new JPanel());
        getMenuPanel().setLayout(new FlowLayout());
        getMenuPanel().setBorder(new EmptyBorder(0, 0, 20, 0));
        addButton("Menu", getMenuPanel(), new ChangeWindowListener(), "goToMenuButton");
        addButton("Ask", getMenuPanel(), new ChangeWindowListener(), "goToAskButton");
        addButton("Grammar", getMenuPanel(), new ChangeWindowListener(), "goToGrammarButton");
        this.add(getMenuPanel());
        
        // Add card panel
        // ActionListener for addNewCardButton
        class NewCardListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageCardsWindow.paintWindow(true);
            }
        }
        
        setAddCardPanel(new JPanel(new FlowLayout()));
        getAddCardPanel().setBorder(new EmptyBorder(0, 0, 10, 0));
        addButton("Add new card", getAddCardPanel(), new NewCardListener(), "addNewCardButton");
        this.add(getAddCardPanel());
        
        ManageCardsWindow.getInnerJPanel().setBorder(new BasicBorders.MarginBorder());
        ManageCardsWindow.setjScrollPane(new JScrollPane(ManageCardsWindow.getInnerJPanel()));
        this.add(jScrollPane);
        
        paintWindow(false);
    }
    
    public static void paintWindow(boolean showAddCardPanel) {
        // If repainting, remove old content
        ManageCardsWindow.getInnerJPanel().removeAll();
        
        // When user clicks on new card button, show this panel
        if (showAddCardPanel == true) {
            getMenuPanel().setVisible(false);
            getAddCardPanel().setVisible(false);
            
            GridLayout addCardGrid = new GridLayout(2, 4);
            addCardGrid.setHgap(20);
            JPanel innerAddCardPanel = new JPanel(addCardGrid);
            
            ManageCardsWindow.getInnerJPanel().setBorder(new EmptyBorder(0, 0, 200, 0));
            ManageCardsWindow.getjScrollPane().setBorder(new EmptyBorder(10, 10, 10, 10));
            ManageCardsWindow.getjScrollPane().setSize(new Dimension(800, 120));
            
            JLabel subTitle = new JLabel("Add a new card by writing the English and the Hungarian meanings of the word");
            subTitle.setAlignmentX(CENTER_ALIGNMENT);
            subTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
            
            JLabel enLabel = new JLabel("English");
            JLabel huLabel = new JLabel("Hungarian");
            JTextField enTextField = new JTextField();
            JTextField huTextField = new JTextField();
            
            innerAddCardPanel.add(enLabel);
            innerAddCardPanel.add(huLabel);
            // add empty label to keep grid layout consistent
            innerAddCardPanel.add(new JLabel());
            innerAddCardPanel.add(new JLabel());
            innerAddCardPanel.add(enTextField);
            innerAddCardPanel.add(huTextField);
            
            SaveNewCardListener newCardListener = new SaveNewCardListener(enTextField, huTextField, databaseHandler);
            class CancelAddCardListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ManageCardsWindow.paintWindow(false);
                }
            }
            
            ManageCardsWindow.addButton("Save new card", innerAddCardPanel, newCardListener, "saveCardButton");
            ManageCardsWindow.addButton("Cancel", innerAddCardPanel, new CancelAddCardListener(), "cancelAddCardButton");
            
            ManageCardsWindow.getInnerJPanel().add(subTitle);
            ManageCardsWindow.getInnerJPanel().add(innerAddCardPanel);
        }
        else {
            // Put cards into memory
            ManageCardsWindow.setCards(DatabaseHandler.selectCards("SELECT * FROM cards"));
            ManageCardsWindow.getjScrollPane().setSize(new Dimension(800, 445));
            ManageCardsWindow.getjScrollPane().setBorder(new EmptyBorder(0, 0, 10, 0));
            
            //getMenuPanel().removeAll();
            getMenuPanel().setVisible(true);
            getAddCardPanel().setVisible(true);
            ManageCardsWindow.getInnerJPanel().setBorder(new EmptyBorder(0, 0, 0, 0));

            if (ManageCardsWindow.getCards().isEmpty()) {
                JLabel noCards = new JLabel("No cards yet.");
                noCards.setAlignmentX(CENTER_ALIGNMENT);
                noCards.setBorder(new EmptyBorder(10, 0, 0, 0));
                ManageCardsWindow.getInnerJPanel().add(noCards);
            }
            else {
                JPanel counterHolderPanel = new JPanel(new FlowLayout(1));
                JLabel cardCounterLabel = new JLabel("Number of cards is :" + ManageCardsWindow.getCards().size());
                cardCounterLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
                counterHolderPanel.add(cardCounterLabel);
                ManageCardsWindow.getInnerJPanel().add(counterHolderPanel);

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
        }
        
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
