package View.AbstractPicker;

import java.util.function.Function;

public class PropertyDescriptor<T> {
	public String label;
	public Function<T, String> propertySelector;
	
	public PropertyDescriptor(String label, Function<T, String> propertySelector) {
		super();
		this.label = label;
		this.propertySelector = propertySelector;
	}
}