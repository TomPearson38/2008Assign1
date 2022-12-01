package View.AbstractPicker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
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

import View.Pickers.FramesetPicker;


class StaffPanel<T> extends JPanel {
	private T _currentObject;
	
	private GridLayout staffPanelLayout = new GridLayout(1,2);
	
	JButton editButton;
	JButton deleteButton;
	
	Runnable refreshPicker;
	
	public StaffPanel(EditorOpener<T> editor, AttemptDatabaseDelete<T> deleteComponent, Runnable refreshPicker) {
		super();
		this.setLayout(staffPanelLayout);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		editButton = new JButton("Edit");
		editButton.addActionListener(e -> {
			editor.editObject(_currentObject);
			
			refreshPicker.run();
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(e -> {
			try {
				deleteComponent.delete(_currentObject);
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
	
	public interface AttemptDatabaseDelete<T>{
		public boolean delete(T Object) throws SQLIntegrityConstraintViolationException;
	}
	public interface AttemptDatabaseUpdate<T> {
		public boolean update(T Object) throws SQLException;
	}
	public interface EditorOpener<T> {
		public T editObject(T object);
	}
}