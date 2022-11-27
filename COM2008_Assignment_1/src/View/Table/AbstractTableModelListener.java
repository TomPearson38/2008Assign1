package View.Table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AbstractTableModelListener<T> implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {
		int rowIndex = e.getFirstRow();
        int columnIndex = e.getColumn();
        
        GenericAbstractTableModel<T> model = (GenericAbstractTableModel<T>)e.getSource();
        
        T data = model.getRowObjectFromIndex(rowIndex);
        
        model.addToChanged(data);

        
		
	}

}
