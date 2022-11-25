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
	private Integer columnWidth;
	
	public final static int default_width = 100;
	
	public Column(String name, Function<O, T> getValueFromObject) {
		this(name, getValueFromObject, default_width);	//have to put null here to denote to not use a column width if one isn't provided
	}
	
	public Column(String name, Function<O, T> getValueFromObject, Class<T> type) {
		this(name, getValueFromObject, null, type);
	}
	
	public Column(String name, Function<O, T> getValueFromObject, Integer columnWidth) {
		this(name, getValueFromObject, columnWidth, null);	//have to put null here as can't call instance methods (getClass()) in an intra-constructor call
	}
	
	@SuppressWarnings("unchecked")	//only a failover in the event that a type isn't provided
	public Column(String name, Function<O, T> getValueFromObject, Integer columnWidth, Class<T> type) {
		super();
		this.name = name;
		this.getValueFromObject = getValueFromObject;
		this.columnWidth = columnWidth;
		
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
	
	public Integer getColumnWidth() {
		return columnWidth;
	}
	
}