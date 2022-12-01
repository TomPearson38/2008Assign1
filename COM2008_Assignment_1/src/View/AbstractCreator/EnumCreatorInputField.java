package View.AbstractCreator;

import javax.swing.JComboBox;

/*
 * CreatorInputField extension for handling enums with JComboBox
 */
public class EnumCreatorInputField<E extends Enum<E>> extends CreatorInputField<E, JComboBox<E>> {

	@SuppressWarnings("unchecked")	// is safe - will always be of type E
	public EnumCreatorInputField(E[] enumValues) {
		super(new JComboBox<E>(enumValues), component -> (E)component.getSelectedItem(), (component,value) -> component.setSelectedItem(value));
	}

}
