package View;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Collection;

import javax.swing.JDialog;

import Database.OrderOperations;
import Domain.Customer;
import Domain.Order;

public class PreviousCustomerOrders extends JDialog{
	private static Collection<Order> customersOrders;

	public PreviousCustomerOrders(Customer customer) {
		customersOrders = OrderOperations.getOrdersForCustomer(customer.get_id());
		setLocationRelativeTo(null); //Compact panel centring
    	setLayout(new GridBagLayout());
		
		if(customersOrders != null) {
			addComponents();
	        setLocationRelativeTo(getParent());
			setSize(500 ,300);
			this.setVisible(true);
		}
	}

	
	private void addComponents() {
		PreviousCustomerOrdersTable previousOrders = new PreviousCustomerOrdersTable();
		previousOrders.setPreferredSize(new Dimension(480, 83));
		this.add(previousOrders);
	}


	public static Collection<Order> getCustomerOrders() {
		return customersOrders;
	}
	
	@Override
	public void setDefaultCloseOperation(int operation) {
		// TODO Auto-generated method stub
		customersOrders = null;
		super.setDefaultCloseOperation(operation);
	}
	
}
