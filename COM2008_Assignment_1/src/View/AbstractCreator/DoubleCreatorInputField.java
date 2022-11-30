package View.AbstractCreator;

import View.JDoubleField;

public class DoubleCreatorInputField extends CreatorInputField<Double, JDoubleField> {

	public DoubleCreatorInputField() {
		super(new JDoubleField(), JDoubleField::getDouble, JDoubleField::setDouble);
	}

}
