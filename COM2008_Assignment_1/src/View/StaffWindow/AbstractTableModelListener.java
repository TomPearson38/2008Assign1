package View.StaffWindow;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AbstractTableModelListener<T> implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {
		int rowIndex = e.getFirstRow();
        int columnIndex = e.getColumn();
        
        GenericAbstractTableModel<T> model = (GenericAbstractTableModel<T>)e.getSource();
        T row = model.getRowObjectFromIndex(rowIndex);
        Column<T, ?> column = model.getColumn(columnIndex);
        
        Class<?> typeOfData = column.getClass();
        Object data = model.getValueAt(rowIndex, columnIndex);
        
        column.setValueAsObject(row, data);

        
		
	}

}
