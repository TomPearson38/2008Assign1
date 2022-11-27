package View.StaffWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboBoxEditor<T> extends AbstractCellEditor implements TableCellEditor, ActionListener {
//	Color currentColor;
//	JButton button;
//	JColorChooser colorChooser;
//	protected static final String EDIT = "edit";
	
	JComboBox<T> dropdown;

	public ComboBoxEditor(T[] values) {
		dropdown = new JComboBox<T>(values);
		
//		button = new JButton();
//		button.setActionCommand(EDIT);
		dropdown.addActionListener(this);
//		button.setBorderPainted(false);
		
		//Set up the dialog that the button brings up.
//		colorChooser = new JColorChooser();
	}
		
	public void actionPerformed(ActionEvent e) {
//		this.stopCellEditing();
	}
	
	public boolean stopCellEditing() {
		fireEditingStopped();
        return true;
	}
	

	@SuppressWarnings("unchecked")
	public Object getCellEditorValue() {
		return (T)dropdown.getSelectedItem();	//even though it gets autocasted back to Object it's better to cast it to T first
	}
	
	//Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(
							JTable table,
	                        Object value,
	                        boolean isSelected,
	                        int row,
	                        int column) 
	{
//		currentColor = (Color)value;
		return dropdown;
	}
}
