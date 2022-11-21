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
	JTextField postCodeInput = new JTextField(20);	
	
	JButton loginButton = new JButton("Login");
	JPanel fillerPanel = new JPanel(new GridLayout(0,2));
	
	JPanel namesPanel = new JPanel(new GridLayout(1,1));
	JPanel addressPanel = new JPanel(new GridLayout(1,3));
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
		fillerPanel.setLayout(new GridLayout(3,0));

		namesPanel.add(new JLabel("Forename:"));
		namesPanel.add(forenameInput);
		namesPanel.add(new JLabel("Surname:"));
		namesPanel.add(surenameInput);
		
		addressPanel.add(new JLabel("House Name:"));
		addressPanel.add(houseNumNameInput);
		addressPanel.add(new JLabel("Street Name:"));
		addressPanel.add(streetNameInput);
		addressPanel.add(new JLabel("Post Code:"));
		addressPanel.add(postCodeInput);
		
		
		buttonPanel.add(loginButton);
		
		fillerPanel.add(namesPanel);
		fillerPanel.add(addressPanel);
		fillerPanel.add(buttonPanel);		
		
		fillerPanel.setBorder(BorderFactory.createCompoundBorder(fillerPanel.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), BorderFactory.createEmptyBorder(0,50,0,50)));
		
		
		contentPanel.add(fillerPanel);

		loginButton.addActionListener(this);
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("Login")) {
			foundCustomer = CustomerOperations.findCustomer(forenameInput.getText(), surenameInput.getText(), houseNumNameInput.getText(), streetNameInput.getText(), postCodeInput.getText());
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
