package View.AbstractCreator;

import java.awt.Component;

/**
 * Interface for a grid row in an AbstractCreator
 * @author Alex Dobson
 *
 * @param <T> The type of the value in the component, i.e. a JTextField will have String for T and a JComboBox<E> will have E for T 
 * @param <ComponentType> the type of the component in the row, i.e. JTextField, JComboBox, JCheckbox. Must inherit java.awt.Component
 */
public interface IGridRow<T, ComponentType extends Component> {
	/**
	 * @return The label text to display for the row
	 */
	public String getRowLabel();
	
	/**
	 * @return The value in the component for the grid row
	 */
	public T getFieldValue();
	
	/**
	 * sets the value in the component in the row
	 * @param value the value to set
	 */
	public void setFieldValue(T value);
	
	
	public ComponentType getGridRowComponent();
	
	/**
	 * is row valid for use in the database
	 * @return
	 */
	public Boolean isRowValid();
	
}
