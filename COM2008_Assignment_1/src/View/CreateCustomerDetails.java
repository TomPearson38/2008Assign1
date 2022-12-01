package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.CustomerOperations;
import Database.FrameOperations;
import Database.HandlebarOperations;
import Database.OrderOperations;
import Database.WheelOperations;
import Domain.Bicycle;
import Domain.Customer;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Order;
import Domain.Wheel;
import View.StaffWindow.ExpandedBikeView;
import View.StaffWindow.OrderModelRow;

/**
 * Form for customers to enter their details when placing an order
 * @author tomap
 *
 */
public class CreateCustomerDetails extends JDialog implements ActionListener{
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	JTextField forenameInput = new JTextField(20);
	JTextField surenameInput = new JTextField(20);
	JTextField houseNumNameInput = new JTextField(20);
	JTextField streetNameInput = new JTextField(20);
	JTextField cityInput = new JTextField(20);
	JTextField postCodeInput = new JTextField(20);	
	
	JButton lookUpAddressButton = new JButton("Submit Order");
	
	JPanel fillerPanel = new JPanel(new GridBagLayout());
	JPanel inputPanel = new JPanel(new GridLayout(3,2));
	JPanel buttonPanel = new JPanel(new GridLayout(2,0));

//	JPanel bikeNamePanel = new JPanel(new GridLayout(0,2));
//	JLabel nameFieldLabel = new JLabel("Name Your Bike: ");
	JTextField nameField = new JTextField();
	
	JLabel costLabel;

	
	private Bicycle _bikeToOrder;
	
	/**
	 * Called on start up
	 * @param title Title of the form
	 */
	private void StartUp(String title) {		
		setTitle(title);
				
		setSize(500, 200);
		setLocationRelativeTo(null); //Compact panel centring
		
		setLayout(new BorderLayout());
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();		
		
		//Enter customer details
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
		
		costLabel = new JLabel("Final Price: " + calculateCost());
		
		buttonPanel.add(costLabel);
		buttonPanel.add(lookUpAddressButton);
		
//		bikeNamePanel.add(nameFieldLabel);
//		bikeNamePanel.add(nameField);
		
//		c.gridy = 0;
//		c.gridx = 0;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
//		fillerPanel.add(bikeNamePanel, c);
		
		c.gridy = 1;
		c.gridx = 0;
		c.ipady = 30;
		c.ipadx = 30;
		fillerPanel.add(inputPanel, c);
		
		c.gridy = 2;
		c.gridx = 0;
		c.ipady = 0;
		c.ipadx = 0;
		fillerPanel.add(buttonPanel, c);		
		
		c.ipady = 0;
		c.weighty = 0.65;
		c.gridy = 1;
		c.ipady = 50;
		contentPanel.add(fillerPanel, c);

		lookUpAddressButton.addActionListener(this);
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * calculates cost for the bike order
	 * @return the cost
	 */
	private double calculateCost() {
		double cost = (_bikeToOrder.get_frame().getCost()) + (_bikeToOrder.get_handlebar().getCost()) + (_bikeToOrder.get_Wheels().getCost()) + 10.0;
		return Double.parseDouble(df.format(cost));
	}
	
	/**
	 * Called when customer has successfully entered their details
	 */
	public void actionPerformed(ActionEvent event) {
		Customer selectedCustomer = CustomerOperations.findCustomer(forenameInput.getText(), surenameInput.getText(), houseNumNameInput.getText(), streetNameInput.getText(), cityInput.getText(),postCodeInput.getText());
		if(selectedCustomer == null) {
			selectedCustomer = CustomerOperations.customerCreatingOrder(forenameInput.getText(), surenameInput.getText(), houseNumNameInput.getText(), streetNameInput.getText(), cityInput.getText(),postCodeInput.getText());
		}
		Calendar calendar = Calendar.getInstance();
		
		Date orderDate = new java.sql.Date(calendar.getTime().getTime());
		int serialNumber = ThreadLocalRandom.current().nextInt(0, 999999999);

		double cost = calculateCost();
		
		Order createdOrder = OrderOperations.createNewOrder(selectedCustomer, _bikeToOrder, cost, orderDate, serialNumber);
		
		//Reduces the stock number of the parts used to stop errors in database.
		if(createdOrder != null) {
			Frameset decreasedFrame = _bikeToOrder.get_frame();
			decreasedFrame.reduceStockNum();
			try {
				FrameOperations.updateFrameset(decreasedFrame);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Wheel decreasedWheels = _bikeToOrder.get_Wheels();
			decreasedWheels.reduceStockNum();
			try {
				WheelOperations.updateWheel(decreasedWheels);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Handlebar decreasedHandlebars = _bikeToOrder.get_handlebar();
			decreasedHandlebars.reduceStockNum();
			try {
				HandlebarOperations.updateHandlebar(decreasedHandlebars);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Opens expanded window of new order
			new ExpandedBikeView(new OrderModelRow(createdOrder), false, null, "Order Placed");
		}
		
		this.dispose();
	}
	
	/**
	 * Form is initialised
	 * @param parent Parent frame
	 * @param bikeToOrder Bike to be ordered
	 */
	public CreateCustomerDetails(JFrame parent, Bicycle bikeToOrder) {
		super(parent);
		_bikeToOrder = bikeToOrder;
		StartUp("Enter your details.");
	}
}
