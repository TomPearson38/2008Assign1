package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JComponent;

public class GridRow<T, ComponentType extends Component> {
	private String labelText;
	private JInputField<T, ComponentType> inputField;
	
	
	public GridRow(String labelText, JInputField<T, ComponentType> inputField) {
		super();
		this.labelText = labelText;
		this.inputField = inputField;
	}
	
	

	public String getLabelText() {
		return labelText;
	}

	public JInputField<T, ComponentType> getInputField() {
		return inputField;
	}
}
