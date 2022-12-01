package View.StaffWindow;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Panel for managing all customer's orders from a staff perspective
 * @author Alex Dobson
 */
public class ManageOrdersPanel extends JPanel {
	
	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		this.setLayout(new BorderLayout());
		
		final JPanel bottomPanel = new JPanel(new BorderLayout());
		final OrdersSaveButton saveButton = new OrdersSaveButton();

		bottomPanel.add(saveButton, BorderLayout.EAST);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		final OrderTable ordersTable = new OrderTable();
		ordersTable.addEditedObjectsChangedListener(saveButton);

		this.add(ordersTable, BorderLayout.CENTER);
		
		
		
	
	}
		
	
}
