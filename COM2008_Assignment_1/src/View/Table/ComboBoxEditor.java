package View.Table;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * TableCellEditor implementation for editing arrays, enums and other such collection values
 * @author Alex Dobson
 *
 * @param <T> The type of the object in the collection
 */
public class ComboBoxEditor<T> extends AbstractCellEditor implements TableCellEditor {

	JComboBox<T> dropdown;

	public ComboBoxEditor(T[] values) {
		dropdown = new JComboBox<T>(values);
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
		dropdown.setSelectedItem(value);
		return dropdown;
	}
}
