package View.AbstractCreator;

import View.UserControls.JIntegerField;

public class IntegerCreatorInputField extends CreatorInputField<Integer, JIntegerField> {

	public IntegerCreatorInputField() {
		super(new JIntegerField(), JIntegerField::getInt, JIntegerField::setInt);
	}

}
