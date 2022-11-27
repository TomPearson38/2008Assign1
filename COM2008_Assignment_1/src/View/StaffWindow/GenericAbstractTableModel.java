package View.StaffWindow;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

abstract class GenericAbstractTableModel<T> extends AbstractTableModel {
	private List<T> objects;
	
	private List<Column<T, ?>> columns;

	public GenericAbstractTableModel(List<T> objects, List<Column<T, ?>> columns) {
		super();
		
		this.objects = objects;
		
		this.columns = columns;
	}
	
	public T getRowObjectFromIndex(int rowIndex) {
		return objects.get(rowIndex);
	}
	
	public Column<T, ?> getColumn(int columnIndex) {
		return columns.get(columnIndex);
	}
	

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

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	

	

	
}