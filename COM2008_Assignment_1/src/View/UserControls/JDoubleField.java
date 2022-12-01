package View.UserControls;

/*
 * A version of JTextField that only allows for double values
 */
public class JDoubleField extends JRegexField {


	@Override
	public String getRegex() {
		return "[0-9]*\\.?[0-9]*";
	}
	
	public Double getDouble() {
		String fieldText = this.getText();
		if (fieldText.isEmpty()) {
			return null;
		}
		
		return Double.parseDouble(fieldText);
	}
	
	public void setDouble(Double value) {
		this.setText(value.toString());
	}

}
