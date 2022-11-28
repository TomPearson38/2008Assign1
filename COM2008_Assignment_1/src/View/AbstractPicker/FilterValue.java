package View.AbstractPicker;

import java.util.function.Predicate;

/*
 * Represents a row in a filter dropdown
 */
public class FilterValue<T> {
	private String _label;
	
	private Predicate<T> _predicate;
	
	public String getLabel() {
		return _label;
	}

	public Predicate<T> getPredicate() {
		return _predicate;
	}
	
	/*
	 * @param label the label to display in the row of the dropdown
	 * @param predicate the predicate that will be applied as a filter when this is selected in the droddown
	 */
	public FilterValue(String label, Predicate<T> predicate) {
		super();
		this._predicate = predicate;
		this._label = label;
	}
	
	@Override
	public String toString() {
		return _label;
	}

}