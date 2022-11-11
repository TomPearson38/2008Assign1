package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JComponent;

public class GridRow<T, ComponentType extends Component> implements IGridRow<T, ComponentType> {
	private String labelText;
	private CreatorInputField<T, ComponentType> inputField;
	
	private Predicate<GridRow<T, ComponentType>> isRowValid;
	
	public GridRow(String labelText, CreatorInputField<T, ComponentType> inputField) {
		this(labelText, inputField, row -> true);
	}
	
	public GridRow(String labelText, CreatorInputField<T, ComponentType> inputField,  Predicate<GridRow<T, ComponentType>> isRowValid) {
		super();
		this.labelText = labelText;
		this.inputField = inputField;
		this.isRowValid = isRowValid;
	}
	
	@Override
	public Boolean isRowValid() {
		return isRowValid.test(this);
	}

	@Override
	public String getRowLabel() {
		return labelText;
	}

	@Override
	public T getFieldValue() {
		return inputField.getValueFromComponent();
	}

	@Override
	public ComponentType getGridRowComponent() {
		return inputField.getComponent();
	}
	
	
}
