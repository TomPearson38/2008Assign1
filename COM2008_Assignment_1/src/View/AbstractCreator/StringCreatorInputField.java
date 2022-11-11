package View.AbstractCreator;

import javax.swing.JTextField;

public class StringCreatorInputField extends CreatorInputField<String, JTextField> {

	public StringCreatorInputField() {
		super(new JTextField(), JTextField::getText);
	}

}
