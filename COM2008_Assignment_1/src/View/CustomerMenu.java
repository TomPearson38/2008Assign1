package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import View.BicycleDesigner.BicycleDesignerDialog;


public class CustomerMenu extends JFrame {
	
	JButton loginButton; 
	JButton buildBikeButton;
	JButton viewOrderButton;
	JButton chooseTemplateButton;
	
	public CustomerMenu(String title) {
		super(title);
		final Container contentPanel = this.getContentPane();
		
		contentPanel.setSize(500, 300);
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		loginButton = new JButton("Employee Login");
		c.weightx = 1.0;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,10,0,10);
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridy = 0;
		loginButton.addActionListener(e -> new LoginPanel(this));
		contentPanel.add(loginButton,c);
		
		buildBikeButton = new JButton("Build Bike");
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		c.ipadx = 40;
		c.ipady = 50;
		c.gridx = 0;
		c.gridy = 1;
		buildBikeButton.addActionListener(e ->  BicycleDesignerDialog.designBicycle(this));
		contentPanel.add(buildBikeButton,c);
		
		viewOrderButton = new JButton("View Orders");
		c.ipadx = 0;
		c.gridx = 1;
		c.gridy = 1;
		viewOrderButton.addActionListener(e -> new CustomerLogin(this));
		contentPanel.add(viewOrderButton,c);
		
		chooseTemplateButton = new JButton("Choose Template");
		c.gridx = 2;
		c.gridy = 1;
		//chooseTemplateButton.addActionListener(e -> new TemplatesPanel(this));
		contentPanel.add(chooseTemplateButton,c);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
