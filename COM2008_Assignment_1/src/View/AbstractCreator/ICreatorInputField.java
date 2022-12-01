package View.AbstractCreator;

import java.awt.Component;

/**
 * represents a component field in the right hand column of an AbstractCreator
 * @author Alex Dobson
 *
 * @param <T>   the type of the value in the component (i.e. JCheckBox will contain Boolean values)
 * @param <ComponentType> the type of the component, must extend java.awt.component
 */
public interface ICreatorInputField<T, ComponentType extends Component> {
	
	public T getValueFromComponent();
	
	public void setValueOnComponent(T value);
	
	public ComponentType getComponent();
}
