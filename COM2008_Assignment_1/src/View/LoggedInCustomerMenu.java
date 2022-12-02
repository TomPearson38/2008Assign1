package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class LoggedInCustomerMenu extends JFrame {
	private Customer customer;
	private static Collection<Order> customersOrders;
	private PreviousCustomerOrdersTable previousOrders;
	private JButton redrawButton;
	private JButton editDetailsButton;

	public LoggedInCustomerMenu(Customer _customer) {
		customer = _customer;
		initilise();
		setLocationRelativeTo(null); //Compact panel centring
	}
	
	private void addComponents() {
		previousOrders = new PreviousCustomerOrdersTable();
		previousOrders.setPreferredSize(new Dimension(480, 83));
		this.setLayout(new BorderLayout());
		final JPanel fillterPanel = new JPanel(new GridBagLayout());
		JPanel topPanel = new JPanel(new GridLayout(0,2));
		this.add(fillterPanel);
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.5;
		c.weighty = 0.75;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 0;
		redrawButton = new JButton("Refresh Table");
		redrawButton.addActionListener(this::redrawButtonClicked);
		
		editDetailsButton = new JButton("Edit Your Details");
		editDetailsButton.addActionListener(this::editDetailsButtonClicked);
		
		topPanel.add(redrawButton);
		topPanel.add(editDetailsButton);
		
		fillterPanel.add(topPanel, c);
		
		JLabel messageToDoubleClick = new JLabel("Double click an order to expand it and configure");
		c.gridy = 1;
		fillterPanel.add(messageToDoubleClick, c);
		
		c.ipady = 70;
		c.weighty = 0.25;
		c.gridy = 2;
		fillterPanel.add(previousOrders, c);
		

	}

	private void initiliseTable() {
		previousOrders = new PreviousCustomerOrdersTable();
		previousOrders.setPreferredSize(new Dimension(480, 83));
	}
	
	private void initilise() {
		customersOrders = OrderOperations.getOrdersForCustomer(customer.get_id());
		setLocationRelativeTo(null); //Compact panel centring
    	setLayout(new GridBagLayout());
		
		if(!customersOrders.isEmpty()) {
			initiliseTable();
		}
		addComponents();
        setLocationRelativeTo(getParent());
		setSize(500 ,300);
		this.setVisible(true);
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

	
	private void redrawButtonClicked(ActionEvent e) {
		redraw();
	}
	private void editDetailsButtonClicked(ActionEvent e) {
		CustomerEditDetails ced = new CustomerEditDetails(customer);
	}
	
}
