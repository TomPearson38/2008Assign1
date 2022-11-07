package View.AbstractCreator;

public class GridRow<T> {
	private String labelText;
	private JInputField<T> inputField;
	
	public GridRow(String labelText, JInputField<T> inputField) {
		super();
		this.labelText = labelText;
		this.inputField = inputField;
	}
}
