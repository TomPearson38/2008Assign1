package View.AbstractCreator;

import java.util.Collection;
import java.util.function.Predicate;

import javax.swing.JComboBox;

import View.UserControls.JDoubleField;

public class CollectionGridRow<C> extends GridRow<C, JComboBox<C>> {
	
	public CollectionGridRow(String labelText, C[] values) {
		this(labelText, values, row -> row.getFieldValue() != null);
	}
	
	public CollectionGridRow(String labelText, C[] values, Predicate<GridRow<C, JComboBox<C>>> isRowValid) {
		super(labelText, new CollectionCreatorInputField<C>(values), isRowValid);
		
	}

}
