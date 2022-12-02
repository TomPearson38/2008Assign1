package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.CustomerOperations;
import Domain.Address;
import Domain.Customer;

public class CustomerEditDetails extends JFrame implements ActionListener{
	private Customer currentCustomer;
	
	JTextField forenameInput = new JTextField(20);
	JTextField surenameInput = new JTextField(20);
	JTextField houseNumNameInput = new JTextField(20);
	JTextField streetNameInput = new JTextField(20);
	JTextField cityInput = new JTextField(20);
	JTextField postCodeInput = new JTextField(20);
	JPanel fillerPanel = new JPanel(new GridLayout(0,2));
	JButton updateButton = new JButton("Update Details");
	JButton cancelButton = new JButton("Cancel");
	
	JPanel inputPanel = new JPanel(new GridLayout(3,2));
	JPanel buttonPanel = new JPanel(new GridLayout(0,2));
	
	public CustomerEditDetails(Customer customer) {
		currentCustomer = customer;
		setTitle("Edit Details");
		setSize(500, 400);
		startUp();
		setLocationRelativeTo(null); //Compact panel centring
		populateFields();
		setVisible(true);
	}
	
	private void startUp() {
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
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
		
		buttonPanel.add(updateButton);
		buttonPanel.add(cancelButton);
		
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

		updateButton.addActionListener(this);
		cancelButton.addActionListener(this);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updateButton) {
			if(!checkNullValues()) {
				JOptionPane.showMessageDialog(this, "One or more fields are empty.\nPlease Fix or cancel operations.");
			}
			else {
				if(namesChanged() || addressChanged()) {
					if(namesChanged()) {
						currentCustomer.set_forename(forenameInput.getText());
						currentCustomer.set_surname(surenameInput.getText());
					}
					if(addressChanged()) {
						Address currentAddress = currentCustomer.get_address();
						currentAddress.set_houseNumName(houseNumNameInput.getText());
						currentAddress.set_streetName(streetNameInput.getText());
						currentAddress.set_city(cityInput.getText());
						currentAddress.set_postCode(postCodeInput.getText());
					}
					
					currentCustomer = CustomerOperations.UpdateCustomer(currentCustomer);
					populateFields();
					JOptionPane.showMessageDialog(this, "Update Successful");
				}
				
			}
		}
		else {
			this.dispose();
		}
		
	}
	
	private void populateFields() {
		forenameInput.setText(currentCustomer.get_forename());
		surenameInput.setText(currentCustomer.get_surname());
		houseNumNameInput.setText(currentCustomer.get_address().get_houseNumName()); 
		streetNameInput.setText(currentCustomer.get_address().get_streetName());
		cityInput.setText(currentCustomer.get_address().get_city());
		postCodeInput.setText(currentCustomer.get_address().get_postCode());
	}
	
	private Boolean namesChanged() {
		if(forenameInput.getText() != currentCustomer.get_forename() && 
				surenameInput.getText() != currentCustomer.get_surname()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private Boolean addressChanged() {
		if(houseNumNameInput.getText() != currentCustomer.get_address().get_houseNumName() 
				|| streetNameInput.getText() != currentCustomer.get_address().get_streetName()
				|| cityInput.getText() != currentCustomer.get_address().get_streetName()
				|| postCodeInput.getText() != currentCustomer.get_address().get_postCode())
			return true;
		else
			return false;
	}
	
	private boolean checkNullValues(){
		if(forenameInput.getText().equals("") || surenameInput.getText().equals("") || houseNumNameInput.getText().equals("")
				|| streetNameInput.getText().equals("") || cityInput.getText().equals("") || postCodeInput.getText().equals("")) {
			return false;
		}
		else
			return true;
	}

}
