package View.StaffWindow;

import java.awt.Container;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class StaffWindow extends JDialog {
	
	ManageStockPanel stockPanel;
	
	ManageOrdersPanel ordersPanel;
	
	
	private JTabbedPane tabPane = new JTabbedPane();

	public StaffWindow(JFrame owner) {
		super(owner);
		
		stockPanel = new ManageStockPanel(this);
		
		ordersPanel = new ManageOrdersPanel();
		
		addComponents();
	}
	
	private void addComponents() {
		
		tabPane.addTab("Orders", ordersPanel);
		
		tabPane.addTab("Manage stock", stockPanel);
		
		
		final Container dialogContentPane = this.getContentPane();
		
		dialogContentPane.add(tabPane);
		
	}
	
	public void showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
    }
}
