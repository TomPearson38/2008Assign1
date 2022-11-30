package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.BicycleOperations;
import Domain.Bicycle;
import Domain.Customer;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Wheel;
import Resources.ResourceSingleton;
import View.BicycleDesigner.BicycleDesignerPanel;

public class CustomerMenu extends JFrame {
	BicycleDesignerPanel mainPanel;
	
	
	public CustomerMenu() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		this.setTitle("Bicycle Designer");
		final Container contentPanel = this.getContentPane();
		contentPanel.setLayout(new BorderLayout());
		
		final JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		final JButton loginButton  = new JButton("Employee Login");
		loginButton.addActionListener(e -> new LoginPanel(this));
		
		topPanel.add(loginButton, BorderLayout.EAST);
		
		
		mainPanel = new BicycleDesignerPanel(this);


		final JPanel rightContainerPanel = new JPanel(new BorderLayout());
		final JPanel bottomRightContainerPanel = new JPanel(new GridLayout(2,0));
		
		
		final JPanel customerButtonsPanel = new JPanel();
		customerButtonsPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		customerButtonsPanel.setLayout(new BoxLayout(customerButtonsPanel, BoxLayout.Y_AXIS));
		customerButtonsPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		final JButton viewOrderButton = new JButton("<html> View Orders </html>");
		viewOrderButton.setPreferredSize(new Dimension(100, 100));
		viewOrderButton.addActionListener(e -> new CustomerLogin(this));
		
		final JButton chooseTemplateButton = new JButton("<html> Choose Template </html>");
		chooseTemplateButton.setPreferredSize(new Dimension(100, 100));
		chooseTemplateButton.addActionListener(e -> {
			final Bicycle chosenBicycle = BicyclePicker.chooseBicycle(this, false);
			if (chosenBicycle != null) {
				mainPanel.setCurrentFrameset(chosenBicycle.get_frame());
				mainPanel.setCurrentHandlebars(chosenBicycle.get_handlebar());
				mainPanel.setCurrentWheels(chosenBicycle.get_Wheels());
				mainPanel.setName(chosenBicycle.getCustomerGivenName());
			}
			});
		
		
		customerButtonsPanel.add(chooseTemplateButton);
		customerButtonsPanel.add(viewOrderButton);
		
		final JButton saveDesignButton = new JButton("Save Design");
		saveDesignButton.setIcon(new ImageIcon(ResourceSingleton.getSaveIcon()));
		saveDesignButton.setEnabled(false);
		mainPanel.addDesignValidityListener(saveDesignButton::setEnabled);
		saveDesignButton.addActionListener(this::saveButtonClicked);
		
		final JButton submitOrderButton = new JButton("Order Design");
		Image shoppingImage = ResourceSingleton.getShoppingImage();
		submitOrderButton.setIcon(new ImageIcon(shoppingImage.getScaledInstance(32, 32, DO_NOTHING_ON_CLOSE)));
		submitOrderButton.setEnabled(false);
		mainPanel.addDesignValidityListener(submitOrderButton::setEnabled);
		submitOrderButton.addActionListener(this::orderButtonClicked);
		
		bottomRightContainerPanel.add(saveDesignButton);
		bottomRightContainerPanel.add(submitOrderButton);
		
		rightContainerPanel.add(bottomRightContainerPanel, BorderLayout.SOUTH);
		rightContainerPanel.add(customerButtonsPanel, BorderLayout.CENTER);
		
		
		contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(mainPanel, BorderLayout.WEST);
		contentPanel.add(rightContainerPanel, BorderLayout.CENTER);
	}
	
	private void saveButtonClicked(ActionEvent e) {
		BicycleOperations.addBicycle(mainPanel.get_currentFrameset(), mainPanel.get_currentHandlebars(), mainPanel.get_currentWheels(), mainPanel.getName());
	}
	
	private void orderButtonClicked(ActionEvent e) {
		Bicycle newBike = BicycleOperations.addBicycle(mainPanel.get_currentFrameset(), mainPanel.get_currentHandlebars(), mainPanel.get_currentWheels(), mainPanel.getName());
		
		boolean checkStock = stockChecker(newBike);
		
		if(checkStock) {
			CreateCustomerDetails cd = new CreateCustomerDetails(this, newBike);
		}
	}
	
	private boolean stockChecker(Bicycle proposedBike) {
		Frameset fr = proposedBike.get_frame();
		Wheel wh = proposedBike.get_Wheels();
		Handlebar hb = proposedBike.get_handlebar();
		
		if(fr.getStockNum() >= 1 && wh.getStockNum() >= 2 && hb.getStockNum() >= 1)
			return true;
		else {
			String stock = "Unfortunately the listed parts are out of stock:";
			
			if(fr.getStockNum() < 1)
				stock = stock + "\n-" + fr.getBrandName() + " Frame";
			if(wh.getStockNum() < 2)
				stock = stock + "\n-" + wh.getBrandName() + " Wheels";
			if(hb.getStockNum() < 1)
				stock = stock + "\n-" + hb.getBrandName() + " Handlebars";
			
			JOptionPane.showMessageDialog(this, stock, "Stock error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	
}
