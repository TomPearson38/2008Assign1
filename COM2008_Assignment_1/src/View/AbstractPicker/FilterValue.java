package View.AbstractPicker;

import java.util.function.Predicate;

public class FilterValue<T> {
	private String _label;
	
	private Predicate<T> _predicate;
	
	public String getLabel() {
		return _label;
	}

	public Predicate<T> getPredicate() {
		return _predicate;
	}

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