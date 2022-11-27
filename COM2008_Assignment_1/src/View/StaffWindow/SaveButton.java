package View.StaffWindow;

import java.util.Collection;

import javax.swing.JButton;

import View.Table.EditedObjectsChangedListener;

public class SaveButton extends JButton implements EditedObjectsChangedListener {
	
	final static String buttonText = "Save";
	
	public SaveButton() {
		super(buttonText);
		
		this.setEnabled(false);
		
	}

	@Override
	public void editedObjectsChanged(Object source, Collection editedObjects) {
		boolean anyObjectsInEditedObjectsCollection = editedObjects.size() > 0;
		
		this.setEnabled(anyObjectsInEditedObjectsCollection);
		
		
	}
	
}
