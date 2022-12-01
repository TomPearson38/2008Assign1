package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Predicate;

/**
 * Represents a row in an AbstractCreator
 * Several helpers have been provided for several common types such as DoubleGridRow for Double and BooleanGridRow for Boolean
 * You can however create your own GridRow by passing a String for the label, the ICreatorInputField for the right column and optionally an input validator
 * @author Alex Dobson
 *
 * @param <T>   the type of the value in the field
 * @param <ComponentType>   the type of the component to display in the row, must extend java.awt.component
 * 
 */
public class GridRow<T, ComponentType extends Component> implements IGridRow<T, ComponentType> {
	private String labelText;
	private ICreatorInputField<T, ComponentType> inputField;
	
	private Predicate<GridRow<T, ComponentType>> isRowValid;
	
	public GridRow(String labelText, ICreatorInputField<T, ComponentType> inputField) {
		this(labelText, inputField, row -> true);
	}
	
	/**
	 * @param labelText The label text to go on the label in the left hand column
	 * @param inputField An ICreatorField implementation to go in the right hand column
	 * @param isRowValid optional validation predicate that checks if it's valid before saving it to the database 
	 */
	public GridRow(String labelText, ICreatorInputField<T, ComponentType> inputField,  Predicate<GridRow<T, ComponentType>> isRowValid) {
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

	@Override
	public void setFieldValue(T value) {
		// TODO Auto-generated method stub
		inputField.setValueOnComponent(value);
	}
	
	
}
