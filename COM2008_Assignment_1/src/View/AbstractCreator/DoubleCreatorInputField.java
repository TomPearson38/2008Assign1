package View.AbstractCreator;

import View.UserControls.JDoubleField;

public class DoubleCreatorInputField extends CreatorInputField<Double, JDoubleField> {

	public DoubleCreatorInputField() {
		super(new JDoubleField(), JDoubleField::getDouble, JDoubleField::setDouble);
	}

}
