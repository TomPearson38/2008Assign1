package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Database.StaffOperations;
import Domain.Staff;

public class LoginPanel extends JDialog implements ActionListener {

	JTextField usernameField = new JTextField(20);
	JPasswordField passwordField = new JPasswordField(20);
	JButton loginButton = new JButton("Login");
	JPanel fillerPanel = new JPanel(new GridLayout(0,2));
	
	JPanel usernamePanel = new JPanel(new GridLayout(1,0));
	JPanel passwordPanel = new JPanel(new GridLayout(1,0));
	JPanel buttonPanel = new JPanel(new BorderLayout());

	Staff loggedInUser = null;
	
	JLabel name = new JLabel();
	
	
	public LoginPanel(String title) {		
		setTitle(title);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		setSize(screenSize.width/4, screenSize.height/4);
		setLocation(screenSize.width/8 * 3, screenSize.height/8 * 3);
		
		Container contentPanel = getContentPane();
		fillerPanel.setLayout(new GridLayout(3,0));

		usernamePanel.add(new JLabel("Username:"));
		usernamePanel.add(usernameField);
		
		passwordPanel.add(new JLabel("Password:"));
		passwordPanel.add(passwordField);
		
		buttonPanel.add(loginButton);
		
		fillerPanel.add(usernamePanel);
		fillerPanel.add(passwordPanel);
		fillerPanel.add(buttonPanel);		
		
		fillerPanel.setBorder(BorderFactory.createCompoundBorder(fillerPanel.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), BorderFactory.createEmptyBorder(0,20,0,20)));

		contentPanel.add(fillerPanel);

		loginButton.addActionListener(this);
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("Login")) {
			loggedInUser = StaffOperations.attemptLogin(usernameField.getText(), passwordField.getText());
			if(loggedInUser != null) {
				System.out.println(loggedInUser.toString());
			}
			else {
				System.out.println("INCORRECT LOGIN DETAILS");
			}
		}
	}
	
	
	public static void main(String[] args) {
		new LoginPanel("Staff Login");
	}
}
