package View;

public class JIntegerField extends JRegexField {

	@Override
	public String getRegex() {
		// TODO Auto-generated method stub
		return "^\\d+$";
	}
	
	public int getInt() {
		return Integer.parseInt(this.getText());
	}

}
