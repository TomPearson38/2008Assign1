package View.AbstractPicker;

import java.util.Collection;

/*
 * Each filter is equivalent to a JComboBox and each of the rows are represented by FilterValues
 * @param <T> The type being filtered
 */
public class Filter<T> {
	public String filterName;
	public Collection<FilterValue<T>> filterValues;
	
	/*
	 * @param filterName the label 
	 */
	public Filter(String filterName, Collection<FilterValue<T>> filterValues) {
		super();
		this.filterName = filterName;
		this.filterValues = filterValues;
	}
}