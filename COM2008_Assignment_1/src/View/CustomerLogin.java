package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.CustomerOperations;
import Database.OrderOperations;
import Domain.Customer;
import Domain.Order;
import View.StaffWindow.ExpandedBikeView;
import View.StaffWindow.OrderModelRow;

/**
 * Called when a user has requested to view orders in order to
 * identify which customer has requested details
 * @author tomap
 *
 */
public class CustomerLogin extends JDialog implements ActionListener {
	JTextField forenameInput = new JTextField(20);
	JTextField surenameInput = new JTextField(20);
	JTextField houseNumNameInput = new JTextField(20);
	JTextField streetNameInput = new JTextField(20);
	JTextField cityInput = new JTextField(20);
	JTextField postCodeInput = new JTextField(20);	
	
	JButton orderNumberLookupButton = new JButton("Lookup Order Number");
	JTextField orderNumberInput = new JTextField(20);
	JPanel orderNumberPanel = new JPanel(new GridLayout(3,0));
	
	JButton lookUpAddressButton = new JButton("Login");
	JPanel fillerPanel = new JPanel(new GridLayout(0,2));
	
	JPanel inputPanel = new JPanel(new GridLayout(3,2));
	JPanel buttonPanel = new JPanel(new BorderLayout());
	
	LoggedInCustomerMenu pco;

	
	Customer foundCustomer = null;
	
	JLabel name = new JLabel();
	
	/**
	 * Initialises the form
	 * @param title Form title
	 */
	private void StartUp(String title) {		
		setTitle(title);
		
		
		setSize(500, 400);
		setLocationRelativeTo(null); //Compact panel centring
		
		
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		orderNumberLookupButton.addActionListener(this);
		orderNumberPanel.add(new JLabel("Order Number:"));
		orderNumberPanel.add(orderNumberInput);
		orderNumberPanel.add(orderNumberLookupButton);
		c.weightx = 0.5;
		c.weighty = 0.25;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		
		
		contentPanel.add(orderNumberPanel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		c.weighty = 0.1;
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(4,0));
		middle.add(new JLabel("------------------------------------ OR ------------------------------------"));
		middle.add(new JLabel("Please Enter Your Details to Login"));
		middle.add(new JLabel("When Logged in you can view your existing orders"));
		middle.add(new JLabel("Cancel pending orders and edit your details."));
		
		contentPanel.add(middle, c);
		
		//Login with Details
		fillerPanel.setLayout(new GridLayout(2,0));

		inputPanel.add(new JLabel("Forename:"));
		inputPanel.add(forenameInput);
		inputPanel.add(new JLabel("Surname:"));
		inputPanel.add(surenameInput);
		
		inputPanel.add(new JLabel("House Name:"));
		inputPanel.add(houseNumNameInput);
		inputPanel.add(new JLabel("Street Name:"));
		inputPanel.add(streetNameInput);
		inputPanel.add(new JLabel("City:"));
		inputPanel.add(cityInput);
		inputPanel.add(new JLabel("Post Code:"));
		inputPanel.add(postCodeInput);
		
		buttonPanel.add(lookUpAddressButton);
		
		fillerPanel.add(inputPanel);
		fillerPanel.add(buttonPanel);		
		
		inputPanel.setBorder(BorderFactory.createCompoundBorder(inputPanel.getBorder(), BorderFactory.createEmptyBorder(0,10,0,10)));
		fillerPanel.setBorder(BorderFactory.createCompoundBorder(fillerPanel.getBorder(), BorderFactory.createEmptyBorder(10,0,0,10)));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), BorderFactory.createEmptyBorder(10,50,10,50)));
		
		c.ipady = 0;
		c.weighty = 0.65;
		c.gridy = 2;
		c.ipady = 50;


		contentPanel.add(fillerPanel, c);

		lookUpAddressButton.addActionListener(this);
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	/**
	 * Called when an action is performed on the page such as a user
	 * logging in with their details or supplying an order number
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("Login")) {
			foundCustomer = CustomerOperations.findCustomer(forenameInput.getText(), surenameInput.getText(), houseNumNameInput.getText(), streetNameInput.getText(), cityInput.getText(),postCodeInput.getText());
			if(foundCustomer != null) {
				pco = new LoggedInCustomerMenu(foundCustomer);
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect Customer Details.\nPlease try again.");
			}
		}
		else if(command.equals("Lookup Order Number")) {
			Order foundOrder = null;
			try {
				foundOrder = OrderOperations.getOrder(Integer.parseInt(orderNumberInput.getText()));
				if(foundOrder != null) {
					new ExpandedBikeView(new OrderModelRow(foundOrder), false, null, "Order Found");
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Order Number.\nPlease try again.");
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Incorrect Order Number Format.\nPlease enter a number.");
			}
		}
	}
	
	/**
	 * 
	 * @param parent
	 */
	public CustomerLogin(JFrame parent) {
		super(parent);
		StartUp("Customer Login");
	}
}
