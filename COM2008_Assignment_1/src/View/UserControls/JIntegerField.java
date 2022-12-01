package View.UserControls;

/**
 * A version of JTextField that only allows for integer values
 */
public class JIntegerField extends JRegexField {

	@Override
	public String getRegex() {
		// TODO Auto-generated method stub
		return "^\\d+$";
	}
	
	public Integer getInt() {
		String fieldText = this.getText();
		if (fieldText.isEmpty()) {
			return null;
		}
		
		return Integer.parseInt(fieldText);
	}
	
	public void setInt(int value) {
		this.setText(Integer.toString(value));
	}

}
