package View.UserControls;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A new text field that limits the length of the available string
 * Code is adapted from stack overflow see link:
 * https://stackoverflow.com/questions/10136794/limiting-the-number-of-characters-in-a-jtextfield
 *
 */
public class LimitedJTextField  extends PlainDocument{
	  private int limit;
	  public LimitedJTextField(int limit) {
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
