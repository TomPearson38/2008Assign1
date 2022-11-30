package View.AbstractPicker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import View.FramesetPicker;


class StaffPanel<T> extends JPanel {
	private T _currentObject;
	
	private GridLayout staffPanelLayout = new GridLayout(1,2);
	
	JButton editButton;
	JButton deleteButton;
	
	Runnable refreshPicker;
	
	public StaffPanel(AttemptDatabaseOperation<T> updateComponent, AttemptDatabaseOperation<T> deleteComponent, Runnable refreshPicker) {
		super();
		this.setLayout(staffPanelLayout);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		editButton = new JButton("Edit");
		editButton.addActionListener(e -> {
			try {
				updateComponent.apply(_currentObject);
				refreshPicker.run();
			} catch (SQLIntegrityConstraintViolationException e1) {
				// TODO Display error message for foreign key constrain 
				
				e1.printStackTrace();
			} 
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(e -> {
			try {
				deleteComponent.apply(_currentObject);
				refreshPicker.run();
			} catch (SQLIntegrityConstraintViolationException e1) {
				// Display error message for foreign key violation
				JOptionPane.showMessageDialog(this, "Component In Use!",
			               "Error!", JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
		});
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(editButton);
		this.add(deleteButton);
		this.refreshPicker = refreshPicker;
	}

	public void set_currentObject(T _currentObject) {
		this._currentObject = _currentObject;
		
		//Activate Staff buttons once an object is selected
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}	
	
	public interface AttemptDatabaseOperation<T>{
		public boolean apply(T Object) throws SQLIntegrityConstraintViolationException;
	}
}