package View.AbstractCreator;

import java.awt.Component;
import java.util.function.Function;

public class CreatorInputField<T, ComponentType extends Component> implements ICreatorInputField<T, ComponentType> {
	
	private ComponentType Component;
	private Function<ComponentType, T> GetValueFromComponent;
	private Setter<ComponentType, T> SetValueFromComponent;
	
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
