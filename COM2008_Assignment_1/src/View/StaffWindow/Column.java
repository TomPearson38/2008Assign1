package View.StaffWindow;

import java.util.function.Function;

/*
 * Column represents a column in the TableModel where O is the type of the underlying object and T the type of the return object
 */
class Column<O, T> {
	private String name;
	private Function<O, T> getValueFromObject;
	private Class<T> underlyingType;
	
	public Column(String name, Function<O, T> getValueFromObject, Class<T> type) {
		super();
		this.name = name;
		this.getValueFromObject = getValueFromObject;
		this.underlyingType = type;
	}
	
	public String getName() {
		return name;
	}

	public T getValue(O object) {
		return getValueFromObject.apply(object);
	}
	
	public Class<?> getUnderlyingClass() {
		return underlyingType;
	}
	
}