package View.StaffWindow;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Panel for managing all customer's orders from a staff perspective
 * @author Alex Dobson
 */
public class ManageOrdersPanel extends JPanel {
	OrderTable ordersTable;
	final OrdersSaveButton saveButton = new OrdersSaveButton();

	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		
		final JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.add(new JLabel("----------------Double Click an order to Expand----------------"), BorderLayout.CENTER);
		
		bottomPanel.add(saveButton, BorderLayout.EAST);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		ordersTable = new OrderTable();
		ordersTable.addEditedObjectsChangedListener(saveButton);

		this.add(ordersTable, BorderLayout.CENTER);
		
		
		
	
	}

	public void redraw() {
		addComponents();
	}
		
	
}
