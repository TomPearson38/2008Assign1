package View.Table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AbstractTableModelListener<T> implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {
		int rowIndex = e.getFirstRow();
        
        @SuppressWarnings("unchecked")	//will always be of type GenericAbstractTableModel, unless you add this as a listener to the wrong JTable implementation
		GenericAbstractTableModel<T> model = (GenericAbstractTableModel<T>)e.getSource();
        
        T data = model.getRowObjectFromIndex(rowIndex);
        
        model.addToChanged(data);

        
		
	}

}
