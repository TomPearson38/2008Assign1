package View.UserControls;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * JTextField that applies a regex filter to its contents
 * @author Alex Dobson
 *
 */
public abstract class JRegexField extends JTextField {
	
	public abstract String getRegex();

	public JRegexField() {
		super();
		configure();
		// TODO Auto-generated constructor stub
	}
	
	private void configure() {
        AbstractDocument document = (AbstractDocument) this.getDocument();
        final int maxCharacters = 10;
        document.setDocumentFilter(new DocumentFilter() {//adapted and generalised from Integer filter https://stackoverflow.com/a/11093360
            public void replace(FilterBypass fb, int offs, int length,
                    String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches(getRegex())) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches(getRegex())) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
	}

}
