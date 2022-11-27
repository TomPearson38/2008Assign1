package View;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerMenu2 extends JFrame {
	
	
	public CustomerMenu2() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		final Container contentPanel = this.getContentPane();
		contentPanel.setSize(500, 300);
		
		final JPanel topPanel = new JPanel();
		final JPanel mainPanel = new JPanel();
		
		final JButton loginButton  = new JButton("Employee Login");
		topPanel.add(loginButton, BorderLayout.EAST);
		
		contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(mainPanel, BorderLayout.CENTER);
	}
}
