package View.AbstractCreator;

import java.awt.Component;

public interface IGridRow<ReturnType, ComponentType extends Component> {
	
	public String getRowLabel();
	
	public ReturnType getFieldValue();
	
	public ComponentType getGridRowComponent();
	
	public Boolean isRowValid();
	
}
