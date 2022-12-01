package View.Table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Listens to changes from a GenericAbstractTableModel<T> and adds any changes to the model's record of changed objects
 * @author Alex Dobson
 *
 * @param <T> The type of rows in the AbstractTable
 */
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
