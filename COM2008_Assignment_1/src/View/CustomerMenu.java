package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.BicycleDesigner.BicycleDesignerPanel;

public class CustomerMenu extends JFrame {
	
	public CustomerMenu() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		this.setTitle("Bicycle Designer");
		final Container contentPanel = this.getContentPane();
//		setSize(100, 100);
//		contentPanel.setMinimumSize(new Dimension(100, 100));
		contentPanel.setLayout(new BorderLayout());
		
		final JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		final JButton loginButton  = new JButton("Employee Login");
		loginButton.addActionListener(e -> new LoginPanel(this));
		
		topPanel.add(loginButton, BorderLayout.EAST);
		
		
		final BicycleDesignerPanel mainPanel = new BicycleDesignerPanel(this);
		

		final JPanel rightContainerPanel = new JPanel(new BorderLayout());
		
		final JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		final JButton viewOrderButton = new JButton("<html> View Orders </html>");
		viewOrderButton.setPreferredSize(new Dimension(100, 100));
		viewOrderButton.addActionListener(e -> new CustomerLogin(this));
		
		final JButton chooseTemplateButton = new JButton("<html> Choose Template </html>");
		chooseTemplateButton.setPreferredSize(new Dimension(100, 100));
		
		
		rightPanel.add(chooseTemplateButton);
		rightPanel.add(viewOrderButton);
		
		final JButton saveDesignButton = new JButton("Save Design");
		saveDesignButton.setIcon(new ImageIcon(ResourceSingleton.getSaveIcon()));
		
		rightContainerPanel.add(saveDesignButton, BorderLayout.SOUTH);
		rightContainerPanel.add(rightPanel, BorderLayout.CENTER);
		
		
		contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(mainPanel, BorderLayout.WEST);
		contentPanel.add(rightContainerPanel, BorderLayout.CENTER);
	}
}
