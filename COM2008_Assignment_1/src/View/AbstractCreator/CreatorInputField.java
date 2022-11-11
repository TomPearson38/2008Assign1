package View.AbstractCreator;

import java.util.function.Function;

import java.awt.Component;

public class CreatorInputField<T, ComponentType extends Component> {
	
	private ComponentType Component;
	private Function<ComponentType, T> GetValueFromComponent;
	
	public CreatorInputField(ComponentType component, Function<ComponentType, T> _getValueFromComponent) {
		super();
		Component = component;
		GetValueFromComponent = _getValueFromComponent;
		
	}
	public T getValueFromComponent() {
		return GetValueFromComponent.apply(Component);
	}
	
	public ComponentType getComponent() {
		return Component;
	}
}
