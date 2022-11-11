package View.AbstractCreator;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Domain.HandlebarStyles;

public class EnumCreatorInputField<E extends Enum<E>> extends CreatorInputField<E, JComboBox<E>> {

	public EnumCreatorInputField(E _enum) {
		super(new JComboBox<E>(_enum.values()), Field -> (E)Field.getSelectedItem());
	}

}
