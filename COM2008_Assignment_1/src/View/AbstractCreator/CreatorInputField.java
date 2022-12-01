package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Function;

/**
 * Represents a field in each row of an AbstractCreator
 * Several pre-defined helpers for common types have been created (IntegerCreatorInputField for Integer, StringCreatorInputFied for String, etc...)
 * Alternatively you can define your own one by making a new CreatorInputField(RowComponentHere, FunctionToGetValueFromComponent, FunctionToSetValueOnComponent)
 * @author Alex Dobson
 */
public class CreatorInputField<T, ComponentType extends Component> implements ICreatorInputField<T, ComponentType> {
	
	private ComponentType Component;
	private Function<ComponentType, T> GetValueFromComponent;
	private Setter<ComponentType, T> SetValueFromComponent;
	
	/**
	 * @param component    the component to display, i.e. a JCheckBox, JComboBox, JTextField. Must be of same type as generic argument ComponentType 
	 * @param _getValueFromComponent   Function to get value from the component, e.g. for JTextField it would be JTextField::getText
	 * @param _setValueFromComponent   Function to set a value on the component e.g. for JTextField it would be JTextField::setText
	 */
	public CreatorInputField(ComponentType component, Function<ComponentType, T> _getValueFromComponent, Setter<ComponentType, T> _setValueFromComponent) {
		super();
		Component = component;
		GetValueFromComponent = _getValueFromComponent;
		SetValueFromComponent = _setValueFromComponent;
	}
	
	@Override
	public T getValueFromComponent() {
		return GetValueFromComponent.apply(Component);
	}
	
	@Override
	public void setValueOnComponent(T value) {
		SetValueFromComponent.set(Component, value);
	}
	
	@Override
	public ComponentType getComponent() {
		return Component;
	}
	
	/*
	 * Represents a set method that sets a value of type T on an object on type O.
	 */
	public interface Setter<O, T> {
		public void set(O Object, T value);
	}
}
