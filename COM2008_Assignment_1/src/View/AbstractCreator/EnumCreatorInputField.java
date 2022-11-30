package View.AbstractCreator;

import javax.swing.JComboBox;

import View.JDoubleField;

public class EnumCreatorInputField<E extends Enum<E>> extends CreatorInputField<E, JComboBox<E>> {

	public EnumCreatorInputField(E[] enumValues) {
		super(new JComboBox<E>(enumValues), component -> (E)component.getSelectedItem(), (component,value) -> component.setSelectedItem(value));
	}

}
