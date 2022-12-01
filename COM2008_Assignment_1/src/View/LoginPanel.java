package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Database.StaffOperations;
import Domain.Staff;
import View.StaffWindow.StaffWindow;

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
	
	private JFrame _parent;
	
	private void startUp(String title) {		
		setTitle(title);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		setSize(screenSize.width/4, screenSize.height/4);
		setLocationRelativeTo(null); //Compact panel centring
		
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
		
		passwordField.addKeyListener(keyListener);

		loginButton.addActionListener(this);
						
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent event) {
		
		String command = event.getActionCommand();
		if(command.equals("Login")) {
			attemptLogin();
		}
	}
	
	KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					attemptLogin();
			}
	
		}

			@Override
			public void keyTyped(KeyEvent e) {	
				//Mandatory Override for key listener
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//Mandatory Override for key listener
			};
	};
	
	private void attemptLogin() {
		loggedInUser = StaffOperations.attemptLogin(usernameField.getText(), passwordField.getPassword());
		if(loggedInUser != null) {
			StaffWindow newStaffWindow = new StaffWindow(_parent);
			newStaffWindow.showDialog();
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this,
				    "Invalid Login Credentials",
				    "Incorrect Details",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public LoginPanel(JFrame parent) {
		super(parent);
		_parent = parent;
		startUp("Staff Login");
	}
}
