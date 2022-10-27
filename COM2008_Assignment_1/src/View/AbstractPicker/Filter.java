package View.AbstractPicker;

import java.util.Collection;

public class Filter<T> {
	public String filterName;
	public Collection<FilterValue<T>> filterValues;
	
	public Filter(String filterName, Collection<FilterValue<T>> filterValues) {
		super();
		this.filterName = filterName;
		this.filterValues = filterValues;
	}
}