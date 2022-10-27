package View.AbstractPicker;

import java.util.Collection;

public class Filter<T> {
	public String filterName;
	public Collection<FilterValue> filterValues;
	
	public Filter(String filterName, Collection<FilterValue> filterValues) {
		super();
		this.filterName = filterName;
		this.filterValues = filterValues;
	}
}