package View.StaffWindow;

import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JTable;

import Database.OrderOperations;
import Domain.Order;

public class ManageOrdersPanel extends JPanel {
	private JTable ordersTable = new JTable();
	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		final Collection<Order> allOrders = OrderOperations.getAllOrders();
		
		final AbstractTableModel ordersTableModel = new AbstractTableModel(allOrders);
		
		ordersTable.setModel(ordersTableModel);
		
		this.add(ordersTable);
	}
	
}
