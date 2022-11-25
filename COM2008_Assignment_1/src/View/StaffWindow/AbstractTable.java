package View.StaffWindow;

import java.util.List;

import javax.swing.JTable;

public abstract class AbstractTable<T> extends JTable {
	public AbstractTable() {
		super();
		
		this.setModel(getTableModel());
	}

	protected abstract List<Column<T, ?>> getColumns();
	
	protected abstract AbstractTableModel<T> getTableModel();
}
