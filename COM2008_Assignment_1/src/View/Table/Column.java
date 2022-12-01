package View.Table;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/*
 * Column represents a column in the TableModel where O is the type of the underlying object and T the type of the return object
 */
public class Column<O, T> {
	private String name;
	private Function<O, T> getValueFromObject;
	private Class<T> underlyingType;
	private int columnWidth;
	private TableCellRenderer customRenderer;
	private TableCellEditor customEditor;
	private boolean editable;
	private ValueSetter<O, T> rowValueSetter;
	
	public void setCustomRenderer(TableCellRenderer value) { customRenderer = value; }
	public void setCustomEditor(TableCellEditor value) { customEditor = value; }
	public void setEditable(boolean value) { editable = value; }
	public void setValueSetter(ValueSetter<O, T> value) { rowValueSetter = value; }
	
	public final static int default_width = 100;
	
	public Column(String name, Function<O, T> getValueFromObject) {
		this(name, getValueFromObject, default_width);	//have to put null here to denote to not use a column width if one isn't provided
	}
	
	public Column(String name, Function<O, T> getValueFromObject, Class<T> type) {
		this(name, getValueFromObject, default_width, type);
	}
	
	public Column(String name, Function<O, T> getValueFromObject, int columnWidth) {
		this(name, getValueFromObject, columnWidth, null);	//have to put null here as can't call instance methods (getClass()) in an intra-constructor call
	}
	
	public Column(String name, Function<O, T> getValueFromObject, int columnWidth, Class<T> type) {
		this(name, getValueFromObject, columnWidth, type, null);
	}
	
	@SuppressWarnings("unchecked")	//only a failover in the event that a type isn't provided
	public Column(String name, Function<O, T> getValueFromObject, int columnWidth, Class<T> type, ValueSetter<O, T> setValueOnObject) {
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
		
		this.rowValueSetter = setValueOnObject;
		
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
	
	public int getColumnWidth() {
		return columnWidth;
	}
	
	public TableCellRenderer getCustomRenderer() {
		return customRenderer;
	}
	
	public boolean getEditable() {
		return editable;
	}
	
	public TableCellEditor getCustomEditor() {
		return customEditor;
	}
	
	public void setValue(O object, T value) {
		rowValueSetter.setValueOnObject(object, value);
	}
	
	//I couldn't think of another way to handle getting the data from a JTable on change (which ONLY returns as Object) to safely type into T as the AbstractModelListener doesn't know the type of every column
	//(you can get the Class<?> as it's stored in each Column<O,T> but it can't be used as doing Class<T>.cast(object) returns an Object not T
	//If you have a better solution please replace
	public void setValueAsObject(O object, Object value) {
		T castValue = castObjectToType(value);
		rowValueSetter.setValueOnObject(object, castValue);
	}
	
	//As the Column object is the only one that know the correct type of the values in the respective column and JTable's events don't it will handle casting to the correct type
	public T castObjectToType(Object value) {
		return (T) underlyingType.cast(value);
	}
	
}