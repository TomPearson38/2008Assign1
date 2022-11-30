package View.AbstractCreator;

import java.awt.Component;

public interface IGridRow<T, ComponentType extends Component> {
	
	public String getRowLabel();
	
	public T getFieldValue();
	
	public void setFieldValue(T value);
	
	public ComponentType getGridRowComponent();
	
	public Boolean isRowValid();
	
}
