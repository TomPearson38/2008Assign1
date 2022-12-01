package View.AbstractCreator;

import javax.swing.JTextField;

/**
 * CreatorInputField extension for handling Strings with JTextField
 * @author Alex Dobson
 *
 */
public class StringCreatorInputField extends CreatorInputField<String, JTextField> {

	public StringCreatorInputField() {
		super(new JTextField(), JTextField::getText, JTextField::setText);
	}

}
