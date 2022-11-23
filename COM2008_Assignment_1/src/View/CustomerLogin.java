package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Database.CustomerOperations;
import Database.StaffOperations;
import Domain.Customer;

public class CustomerLogin extends JDialog implements ActionListener {
	JTextField forenameInput = new JTextField(20);
	JTextField surenameInput = new JTextField(20);
	JTextField houseNumNameInput = new JTextField(20);
	JTextField streetNameInput = new JTextField(20);
	JTextField cityInput = new JTextField(20);
	JTextField postCodeInput = new JTextField(20);	
	
	JButton loginButton = new JButton("Login");
	JPanel fillerPanel = new JPanel(new GridLayout(0,2));
	
	JPanel inputPanel = new JPanel(new GridLayout(3,2));
	JPanel buttonPanel = new JPanel(new BorderLayout());

	private JFrame _parent;
	
	Customer foundCustomer = null;
	
	JLabel name = new JLabel();
	
	
	private void StartUp(String title) {		
		setTitle(title);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		setSize(500, 200);
		setLocationRelativeTo(null); //Compact panel centring
		
		
		Container contentPanel = getContentPane();
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
		
		
		buttonPanel.add(loginButton);
		
		fillerPanel.add(inputPanel);
		fillerPanel.add(buttonPanel);		
		
		inputPanel.setBorder(BorderFactory.createCompoundBorder(inputPanel.getBorder(), BorderFactory.createEmptyBorder(0,10,0,10)));
		fillerPanel.setBorder(BorderFactory.createCompoundBorder(fillerPanel.getBorder(), BorderFactory.createEmptyBorder(10,0,0,10)));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), BorderFactory.createEmptyBorder(10,50,10,50)));
		
		
		contentPanel.add(fillerPanel);

		loginButton.addActionListener(this);
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("Login")) {
			foundCustomer = CustomerOperations.findCustomer(forenameInput.getText(), surenameInput.getText(), houseNumNameInput.getText(), streetNameInput.getText(), cityInput.getText(),postCodeInput.getText());
			if(foundCustomer != null) {
				System.out.println("Welcome" + foundCustomer.get_forename());
			}
			else {
				System.out.println("INCORRECT LOGIN DETAILS");
			}
		}
	}
	
	public CustomerLogin(JFrame parent) {
		super(parent);
		_parent = parent;
		StartUp("Customer Login");
	}
}
