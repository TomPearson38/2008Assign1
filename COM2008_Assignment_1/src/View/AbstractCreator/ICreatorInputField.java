package View.AbstractCreator;

import java.awt.Component;

public interface ICreatorInputField<T, ComponentType extends Component> {
	
	public T getValueFromComponent();
	
	public ComponentType getComponent();
}
