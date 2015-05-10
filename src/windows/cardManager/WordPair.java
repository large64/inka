package windows.cardManager;

import javax.swing.JTextField;

/**
 *
 * @author large64
 */
public final class WordPair {
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
