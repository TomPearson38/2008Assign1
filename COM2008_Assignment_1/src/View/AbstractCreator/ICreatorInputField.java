package View.AbstractCreator;

import java.awt.Component;

public interface ICreatorInputField<T, ComponentType extends Component> {
	
	public T getValueFromComponent();
	
	public void setValueOnComponent(T value);
	
	public ComponentType getComponent();
}
