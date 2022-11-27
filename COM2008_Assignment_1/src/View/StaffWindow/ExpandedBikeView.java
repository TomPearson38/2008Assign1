package View.StaffWindow;

import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;

import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;

public class ExpandedBikeView extends JDialog{
	private static Order currentOrder;
	private OrderModelRow _row;
	
	
	public ExpandedBikeView(OrderModelRow row) {
		_row = row;
		currentOrder = OrderOperations.getOrder(row.getOrderNumber());
        setLocationRelativeTo(null); //Compact panel centring
        setSize(500, 300);
		addComponents();
		showDialog();		
	}
	
	public void showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
	
	private void addComponents() {
		final ExpandOrderTable bikeTable = new ExpandOrderTable();
		this.add(bikeTable);
	}
	
	public static Bicycle getBike() {
		return currentOrder.get_bike();
	}

}
