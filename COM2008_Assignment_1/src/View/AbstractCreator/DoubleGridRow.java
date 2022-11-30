package View.AbstractCreator;

import java.util.function.Predicate;

import View.UserControls.JDoubleField;

public class DoubleGridRow extends GridRow<Double, JDoubleField> {
	
	public DoubleGridRow(String labelText) {
		this(labelText, row -> row.getFieldValue() != null);
	}
	
	public DoubleGridRow(String labelText, Predicate<GridRow<Double, JDoubleField>> isRowValid) {
		super(labelText, new DoubleCreatorInputField(), isRowValid);
		
	}

}
