package View.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

/**
 * Reusable table model for representing a list of types T with the columns controlled by Column objects
 * @author Alex Dobson
 *
 * @param <T>
 */
public abstract class GenericAbstractTableModel<T> extends AbstractTableModel {
	private List<T> objects;
	
	private List<Column<T, ?>> columns;
	
	private Set<T> changedObjects = new HashSet<T>();
	
	/**
	 * External listeners to be informed if the list of objects that have been edited by the user have changed
	 */
	private Collection<EditedObjectsChangedListener<T>> editedObjectsChangedListeners = new ArrayList<EditedObjectsChangedListener<T>>();
	
	

	public GenericAbstractTableModel(List<T> objects, List<Column<T, ?>> columns) {
		super();
		
		this.objects = objects;
		
		this.columns = columns;
		
		addTableModelListener(new AbstractTableModelListener<T>());
	}
	
	public T getRowObjectFromIndex(int rowIndex) {
		return objects.get(rowIndex);
	}
	
	public Column<T, ?> getColumn(int columnIndex) {
		return columns.get(columnIndex);
	}
	
	public void addToChanged(T row) {
		T changedObject = row;
		
		if (!changedObjects.contains(changedObject)) {
			changedObjects.add(row);
			editedObjectsChangedListeners.forEach(l -> l.editedObjectsChanged(this, changedObjects));
		}
	}
	
	
	public Collection<T> getEditedObjects() {
		return changedObjects;
	}
	public void addEditedObjectsChangedListener(EditedObjectsChangedListener<T> newListener) { editedObjectsChangedListeners.add(newListener); }
	public void removeEditedObjectsChangedListener(EditedObjectsChangedListener<T> listenerToRemove) { editedObjectsChangedListeners.remove(listenerToRemove); }
	

	@Override
	public int getRowCount() {
		return objects.size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex).getName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columns.get(columnIndex).getUnderlyingClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columns.get(columnIndex).getEditable();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final T row = objects.get(rowIndex);
		
		final Column<T, ?> column = columns.get(columnIndex);
		
		return column.getValue(row);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		T row = objects.get(rowIndex);
		Column<T, ?> column = columns.get(columnIndex);
		
		column.setValueAsObject(row, aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	

	

	
}