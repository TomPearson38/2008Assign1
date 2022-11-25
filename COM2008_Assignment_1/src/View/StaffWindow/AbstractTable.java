package View.StaffWindow;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public abstract class AbstractTable<T> extends JScrollPane {
	private JTable interiorTable = new JTable();
	
	public AbstractTable() {
		super();
		
		interiorTable.setModel(getTableModel());
		
		setColumnWidth();
		
		this.setViewportView(interiorTable);
	}
	
	private void setColumnWidth() {
		final List<Column<T, ?>> columns = getColumns();
		for (int i=0; i < columns.size(); i++) {
			TableColumn column = interiorTable.getColumnModel().getColumn(i);
			Column<T, ?> modelColumn = columns.get(i);
			
			column.setPreferredWidth(modelColumn.getColumnWidth());
			
		}
	}

	protected abstract List<Column<T, ?>> getColumns();
	
	protected abstract AbstractTableModel<T> getTableModel();
}
