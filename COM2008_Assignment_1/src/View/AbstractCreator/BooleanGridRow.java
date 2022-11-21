package View.AbstractCreator;

import java.util.function.Predicate;

import javax.swing.JCheckBox;

public class BooleanGridRow extends GridRow<Boolean, JCheckBox> {
	
	public BooleanGridRow(String labelText) {
		this(labelText, row -> row.getFieldValue() != null);
	}
	
	public BooleanGridRow(String labelText, Predicate<GridRow<Boolean, JCheckBox>> isRowValid) {
		super(labelText, new BooleanCreatorInputField(), isRowValid);
		
	}

}
