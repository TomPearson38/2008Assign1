package View;

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
		
		return (double)Integer.parseInt(fieldText);
	}

}
