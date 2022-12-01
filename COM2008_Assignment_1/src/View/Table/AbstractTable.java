package View.Table;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public abstract class AbstractTable<T> extends JScrollPane implements EditedObjectsChangedListener<T> {
	private InternalTable interiorTable = new InternalTable();
	private GenericAbstractTableModel<T> tableModel = getTableModel();
	
	private int previousClick = -1;
	
	private final static int row_height = 20;
	
	private Collection<EditedObjectsChangedListener<T>> editedObjectsChangedListeners = new ArrayList<EditedObjectsChangedListener<T>>();
	
	public AbstractTable() {
		super();
		
		interiorTable.setModel(tableModel);
		tableModel.addEditedObjectsChangedListener(this);
		
		
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
	
	protected void doubleClicked(T objectClicked) {
		//do nothing, this is an optional method which can be overriden by subclasses
	}
	protected void addDoubleClickListener() {
		interiorTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if(previousClick == row) {
		        	doubleClicked(tableModel.getRowObjectFromIndex(row));
		        	previousClick = -1;
		        }
		        else {
		        	previousClick = row;
		        }
		        
		    }
		});
	}

	protected abstract List<Column<T, ?>> getColumns();
	
	protected abstract GenericAbstractTableModel<T> getTableModel();
	
	public T getSelectedRow() {
		int rowIndex = interiorTable.getSelectedRow();
		return tableModel.getRowObjectFromIndex(rowIndex);
	}
	
	public Collection<T> getEditedObjects() {
		return tableModel.getEditedObjects();
	}
	public void addEditedObjectsChangedListener(EditedObjectsChangedListener<T> newListener) { editedObjectsChangedListeners.add(newListener); }
	public void removeEditedObjectsChangedListener(EditedObjectsChangedListener<T> listenerToRemove) { editedObjectsChangedListeners.remove(listenerToRemove); }
	/*
	 * down-propagate event from model to external listeners
	 */
	public void editedObjectsChanged(Object source, Collection<T> editedObjects) {
		editedObjectsChangedListeners.forEach(l -> l.editedObjectsChanged(source, editedObjects));
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
