package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Database.OrderOperations;
import Domain.Customer;
import Domain.Order;

public class PreviousCustomerOrders extends JFrame implements WindowFocusListener, ActionListener{
	private Customer customer;
	private static Collection<Order> customersOrders;
	private PreviousCustomerOrdersTable previousOrders;
	private JButton redrawButton;

	public PreviousCustomerOrders(Customer _customer) {
		customer = _customer;
		initilise();
	}
	
	private void addComponents() {
		previousOrders = new PreviousCustomerOrdersTable();
		previousOrders.setPreferredSize(new Dimension(480, 83));
		this.setLayout(new BorderLayout());
		final JPanel fillterPanel = new JPanel(new GridBagLayout());
		this.add(fillterPanel);
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.5;
		c.weighty = 0.75;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 0;
		redrawButton = new JButton("Refresh Table");
		redrawButton.addActionListener(this);
		fillterPanel.add(redrawButton, c);
		
		JLabel messageToDoubleClick = new JLabel("Double click an order to expand it and configure");
		c.gridy = 1;
		fillterPanel.add(messageToDoubleClick, c);
		
		c.ipady = 70;
		c.weighty = 0.25;
		c.gridy = 2;
		fillterPanel.add(previousOrders, c);
		

	}

	private void initilise() {
		customersOrders = OrderOperations.getOrdersForCustomer(customer.get_id());
		setLocationRelativeTo(null); //Compact panel centring
    	setLayout(new GridBagLayout());
		
		if(!customersOrders.isEmpty()) {
			addComponents();
	        setLocationRelativeTo(getParent());
			setSize(500 ,300);
			this.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this, "No orders found for selected customer");
			this.dispose();
		}
	}
	
	public static Collection<Order> getCustomerOrders() {
		return customersOrders;
	}
	
	public static void removeOrder(Order orderToRemove) {
		customersOrders.remove(orderToRemove);
	}
	
	@Override
	public void setDefaultCloseOperation(int operation) {
		customersOrders = null;
		super.setDefaultCloseOperation(operation);
	}


	public void redraw() {
		initilise();
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Triggered");
		redraw();

	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		redraw();
	}
	
}
