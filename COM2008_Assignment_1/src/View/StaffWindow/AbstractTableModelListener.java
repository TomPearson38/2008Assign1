package View.StaffWindow;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AbstractTableModelListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        AbstractTableModel model = (AbstractTableModel)e.getSource();
        
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

        
		
	}

}
