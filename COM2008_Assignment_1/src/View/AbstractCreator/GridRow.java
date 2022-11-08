package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JComponent;

public class GridRow<T, ComponentType extends Component> {
	private String labelText;
	private JInputField<T, ComponentType> inputField;
	
	private Predicate<GridRow<T, ComponentType>> isRowValid;
	
	public GridRow(String labelText, JInputField<T, ComponentType> inputField) {
		this(labelText, inputField, row -> true);
	}
	
	public GridRow(String labelText, JInputField<T, ComponentType> inputField,  Predicate<GridRow<T, ComponentType>> isRowValid) {
		super();
		this.labelText = labelText;
		this.inputField = inputField;
		this.isRowValid = isRowValid;
	}
	
	

	public String getLabelText() {
		return labelText;
	}

	public JInputField<T, ComponentType> getInputField() {
		return inputField;
	}

	public Boolean getIsRowValid() {
		return isRowValid.test(this);
	}
	
	
}
