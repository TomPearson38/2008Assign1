package View.UserControls;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A new text field that limits the length of the available string
 * Code is adapted from stack overflow see link:
 * https://stackoverflow.com/questions/10136794/limiting-the-number-of-characters-in-a-jtextfield
 *
 */
public class JLimitedTextField extends JTextField {

	
	public JLimitedTextField(int limit) {
		super();
		this.setDocument(new LimitedDocument(limit));
	}
	
	private class LimitedDocument  extends PlainDocument{
		  private int limit;
		  public LimitedDocument(int limit) {
		    super();
		    this.limit = limit;
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null)
		      return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
	}
}

