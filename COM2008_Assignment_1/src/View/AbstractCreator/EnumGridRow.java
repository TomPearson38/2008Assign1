package View.AbstractCreator;

import java.util.function.Predicate;

import javax.swing.JComboBox;

import View.JDoubleField;

public class EnumGridRow<E extends Enum<E>> extends GridRow<E, JComboBox<E>> {
	
	public EnumGridRow(String labelText, E[] enumValues) {
		this(labelText, enumValues, row -> row.getFieldValue() != null);
	}
	
	public EnumGridRow(String labelText, E[] enumValues, Predicate<GridRow<E, JComboBox<E>>> isRowValid) {
		super(labelText, new EnumCreatorInputField<E>(enumValues), isRowValid);
		
	}

}
