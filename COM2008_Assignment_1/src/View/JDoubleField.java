package View;

public class JDoubleField extends JRegexField {


	@Override
	public String getRegex() {
		return "[0-9]*\\.?[0-9]*";
	}
	
	public double getDouble() {
		return Integer.parseInt(this.getText());
	}

}
