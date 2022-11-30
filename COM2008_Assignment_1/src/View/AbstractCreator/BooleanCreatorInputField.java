package View.AbstractCreator;

import javax.swing.JCheckBox;

public class BooleanCreatorInputField extends CreatorInputField<Boolean, JCheckBox> {

	public BooleanCreatorInputField() {
		super(new JCheckBox(), JCheckBox::isSelected, JCheckBox::setSelected);
	}

}
