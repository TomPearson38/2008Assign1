package View.AbstractCreator;

import View.UserControls.JIntegerField;

/**
 * CreatorInputField extension for handling Integers with JIntegerField
 * @author Alex Dobson
 *
 */
public class IntegerCreatorInputField extends CreatorInputField<Integer, JIntegerField> {

	public IntegerCreatorInputField() {
		super(new JIntegerField(), JIntegerField::getInt, JIntegerField::setInt);
	}

}
