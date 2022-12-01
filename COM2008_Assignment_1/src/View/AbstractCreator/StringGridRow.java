package View.AbstractCreator;

import java.util.function.Predicate;

import javax.swing.JTextField;

/**
 * GridRow extension for handling Strings with StringCreatorInputField
 * @author Alex Dobson
 */
public class StringGridRow extends GridRow<String, JTextField> {
	
	public StringGridRow(String labelText) {
		this(labelText, row -> row.getFieldValue() != null && !row.getFieldValue().isEmpty());
	}
	
	public StringGridRow(String labelText, Predicate<GridRow<String, JTextField>> isRowValid) {
		super(labelText, new StringCreatorInputField(), isRowValid);
		
	}

}
