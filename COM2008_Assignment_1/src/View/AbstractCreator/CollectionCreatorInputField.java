package View.AbstractCreator;

import javax.swing.JComboBox;

public class CollectionCreatorInputField<C> extends CreatorInputField<C, JComboBox<C>> {

	@SuppressWarnings("unchecked")	// is safe - will always be of type C
	public CollectionCreatorInputField(C[] values) {
		super(new JComboBox<C>(values), component -> (C)component.getSelectedItem(), (component,value) -> component.setSelectedItem(value));
	}
}
