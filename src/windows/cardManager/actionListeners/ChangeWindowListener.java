package windows.cardManager.actionListeners;

import inka.Inka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author large64
 */
//This helps to navigate between the windows
public final class ChangeWindowListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
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
