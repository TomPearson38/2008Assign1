package View.StaffWindow;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ManageOrdersPanel extends JPanel {
	
	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		this.setLayout(new BorderLayout());
		
		final SaveButton saveButton = new SaveButton();
		
		this.add(saveButton, BorderLayout.SOUTH);
		
		final OrderTable ordersTable = new OrderTable();
		ordersTable.addEditedObjectsChangedListener(saveButton);

		this.add(ordersTable, BorderLayout.CENTER);
		
		
		
	
	}
		
	
}
