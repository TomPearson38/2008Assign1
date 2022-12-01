package View.Table;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EnumRenderer<E extends Enum<E>> extends JComboBox<E> implements TableCellRenderer {
	public EnumRenderer(E[] values) {
		super(values);
        setOpaque(true); //MUST do this for background to show up.
        
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object value,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
    	
    	if (isSelected)
        {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }
        else
        {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
    	this.setSelectedItem(value);
    	
        return this;
    }


}
