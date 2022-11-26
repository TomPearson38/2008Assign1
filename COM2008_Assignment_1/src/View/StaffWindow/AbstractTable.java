package View.StaffWindow;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public abstract class AbstractTable<T> extends JScrollPane {
	private InternalTable interiorTable = new InternalTable();
	private AbstractTableModel<T> tableModel = getTableModel();
	
	private int previousClick = -1;
	
	private final static int row_height = 20;
	
	public AbstractTable() {
		super();
		
		interiorTable.setModel(tableModel);
		
		setColumnWidth();
		addDoubleClickListener();
		setColumnEditors();
		interiorTable.setRowHeight(row_height);
		
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
	
	private void setColumnEditors() {
		final List<Column<T, ?>> columns = getColumns();
		for (int i=0; i < columns.size(); i++) {
			TableColumn column = interiorTable.getColumnModel().getColumn(i);
			TableCellEditor customEditor = columns.get(i).getCustomEditor();
			
			if (customEditor != null) {
				column.setCellEditor(customEditor);
			}
		}
	}
	
	private void addDoubleClickListener() {
		interiorTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if(previousClick == row) {
			        System.out.println(row);
			        previousClick = -1;
		        }
		        else {
		        	previousClick = row;
		        }
		        
		    }
		});
	}

	protected abstract List<Column<T, ?>> getColumns();
	
	protected abstract AbstractTableModel<T> getTableModel();
	
	public T getSelectedRow() {
		int rowIndex = interiorTable.getSelectedRow();
		return tableModel.getRowObjectFromIndex(rowIndex);
	}
	
	private class InternalTable extends JTable {
		@Override
		public TableCellRenderer getCellRenderer(int row, int column) {
			final List<Column<T, ?>> columns = getColumns();
			
			Column<T, ?> col = columns.get(column);
			TableCellRenderer customRender = col.getCustomRenderer();
			if (customRender != null) {
				return customRender;
			} else {
				return super.getCellRenderer(row, column);
			}
		}
	}
}
