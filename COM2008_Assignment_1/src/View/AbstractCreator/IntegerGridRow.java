package View.AbstractCreator;

import java.util.function.Predicate;

import View.JIntegerField;

public class IntegerGridRow extends GridRow<Integer, JIntegerField> {
	
	public IntegerGridRow(String labelText) {
		this(labelText, row -> row.getFieldValue() != null);
	}
	
	public IntegerGridRow(String labelText, Predicate<GridRow<Integer, JIntegerField>> isRowValid) {
		super(labelText, new IntegerCreatorInputField(), isRowValid);
		
	}

}
