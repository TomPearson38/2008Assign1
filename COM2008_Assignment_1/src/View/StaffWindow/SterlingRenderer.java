package View.StaffWindow;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SterlingRenderer extends JLabel implements TableCellRenderer {
	public SterlingRenderer() {
        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object value,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
    	
    	
    	setText("�" + value.toString());
    	
        return this;
    }


}
