package View.AbstractCreator;

import java.util.Collection;

import javax.swing.JComboBox;

import View.UserControls.JDoubleField;

public class CollectionCreatorInputField<C> extends CreatorInputField<C, JComboBox<C>> {

	public CollectionCreatorInputField(C[] values) {
		super(new JComboBox<C>(values), component -> (C)component.getSelectedItem(), (component,value) -> component.setSelectedItem(value));
	}
}
