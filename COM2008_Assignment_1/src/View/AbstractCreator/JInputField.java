package View.AbstractCreator;

import java.util.function.Function;

import javax.swing.JComponent;

public class JInputField<T> {
	
	private JComponent Component;
	private Function<JComponent, T> GetValueFromComponent;
	
	public JInputField(JComponent component, Function<JComponent, T> _getValueFromComponent) {
		super();
		Component = component;
		GetValueFromComponent = _getValueFromComponent;
		
	}
	public T getValueFromComponent() {
		return GetValueFromComponent.apply(Component);
	}
}
