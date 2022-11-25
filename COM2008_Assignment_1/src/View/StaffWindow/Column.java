package View.StaffWindow;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/*
 * Column represents a column in the TableModel where O is the type of the underlying object and T the type of the return object
 */
class Column<O, T> {
	private String name;
	private Function<O, T> getValueFromObject;
	private Class<T> underlyingType;
	
	public Column(String name, Function<O, T> getValueFromObject) {
		this(name, getValueFromObject, null);	//have to put null here as can't call instance methods (getClass()) in an intra-constructor call
	}
	
	@SuppressWarnings("unchecked")	//only a failover in the event that a type isn't provided
	public Column(String name, Function<O, T> getValueFromObject, Class<T> type) {
		super();
		this.name = name;
		this.getValueFromObject = getValueFromObject;
		
		if (type != null) {
			this.underlyingType = type;
		} else {
			this.underlyingType = (Class<T>) ((ParameterizedType) getClass()
	                .getGenericSuperclass()).getActualTypeArguments()[0];
		}
		
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