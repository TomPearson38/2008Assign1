package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.function.Supplier;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Database.BicycleOperations;
import Domain.Bicycle;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Wheel;
import Resources.ResourceSingleton;
import View.BicycleDesigner.BicycleDesignerPanel;
import View.BicycleDesigner.BicycleDesignerPanel.DesignNotSavedException;
import View.BicycleDesigner.SaveBicycleDesignButton;
import View.Pickers.BicyclePicker;

/**
 * Main menu of the application
 * @author tomap
 *
 */
public class CustomerMenu extends JFrame {
	BicycleDesignerPanel mainPanel;
	
	private JPanel rightSide = new JPanel(new BorderLayout());
	
	public CustomerMenu() {
		super();
		
		addComponents();
	}
	
	/**
	 * Called on start up
	 */
	private void addComponents() {
		this.setTitle("Bicycle Designer");
		final Container contentPanel = this.getContentPane();
		contentPanel.setLayout(new BorderLayout());
		
		//final JPanel topPanel = new JPanel();
		//rightSide.setLayout(new BorderLayout());
		final JButton loginButton  = new JButton("Employee Login");
		loginButton.addActionListener(e -> new LoginPanel(this));		
		
		mainPanel = new BicycleDesignerPanel(this);


		final JPanel rightContainerPanel = new JPanel(new BorderLayout());
		final JPanel bottomRightContainerPanel = new JPanel(new GridLayout(2,0));
		
		final JPanel customerButtonsPanel = new JPanel();
		customerButtonsPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		customerButtonsPanel.setLayout(new BoxLayout(customerButtonsPanel, BoxLayout.Y_AXIS));
		customerButtonsPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		final JButton viewOrderButton = new JButton("<html> View Orders / Customer Login</html>");
		viewOrderButton.setPreferredSize(new Dimension(100, 100));
		viewOrderButton.addActionListener(e -> new CustomerLogin(this));
		viewOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		final JButton chooseTemplateButton = new JButton("<html> Choose Template </html>");
		chooseTemplateButton.setPreferredSize(new Dimension(100, 100));
		chooseTemplateButton.addActionListener(e -> {
			final Bicycle chosenBicycle = BicyclePicker.chooseBicycle(this, false);
			if (chosenBicycle != null) {
				mainPanel.setDesign(chosenBicycle);
			}
			});
		
		
		customerButtonsPanel.add(chooseTemplateButton);
		customerButtonsPanel.add(viewOrderButton);
		
		final JButton saveDesignButton = new SaveBicycleDesignButton("Save New Design", mainPanel);
		
		final JButton submitOrderButton = new JButton("Order Design");
		Image shoppingImage = ResourceSingleton.getShoppingImage();
		submitOrderButton.setIcon(new ImageIcon(shoppingImage.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		submitOrderButton.setEnabled(false);
		mainPanel.addDesignSavedListener(submitOrderButton::setEnabled); 
		submitOrderButton.addActionListener(this::orderButtonClicked);
				
		bottomRightContainerPanel.add(saveDesignButton);
		bottomRightContainerPanel.add(submitOrderButton);
		
		rightContainerPanel.add(bottomRightContainerPanel, BorderLayout.SOUTH);
		rightContainerPanel.add(customerButtonsPanel, BorderLayout.CENTER);
		
		//rightSide.add(topPanel, BorderLayout.NORTH);
		rightSide.add(loginButton, BorderLayout.NORTH);

		rightSide.add(rightContainerPanel, BorderLayout.CENTER);
			
		//contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(mainPanel, BorderLayout.WEST);
		//contentPanel.add(rightContainerPanel, BorderLayout.CENTER);
		contentPanel.add(rightSide, BorderLayout.EAST);
		
	}
	
	
	/**
	 * Orders bike if valid
	 * @param e
	 */
	private void orderButtonClicked(ActionEvent e) {
		Bicycle bikeToOrder;
		try {
			bikeToOrder = mainPanel.getSavedBicycle();
			
			boolean checkStock = stockChecker(bikeToOrder);
			
			if(checkStock) {
				new CreateCustomerDetails(this, bikeToOrder);
			}
			
		} catch (DesignNotSavedException ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	/**
	 * Checks that all the bike parts are in stock before it orders them
	 * @param proposedBike Bike to be ordered
	 * @return All parts in stock
	 */
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
