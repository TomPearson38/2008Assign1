package View.StaffWindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;
import View.LoggedInCustomerMenu;
import View.PreviousCustomerOrdersTable.PastOrderTableModel;
import View.StaffWindow.OrderTable.OrderTableModel;
import View.Table.GenericAbstractTableModel;

/**
 * Pop up window to display information on the current order that can't be displayed in the table
 * @author Tom Pearson
 */
public class ExpandedBikeView extends JDialog implements ActionListener{
	private Order currentOrder;
	private static Bicycle currentBike;
	private OrderModelRow _row;
	private JComboBox<OrderStatus> orderStatusCombo;
	private boolean _staffMember;
	private JButton confirmButton;
	private JButton deleteOrder;
	
	JPanel tablePanel = new JPanel(new BorderLayout());
	JPanel infoPanel = new JPanel(new GridLayout(3,4));
	JLabel orderNumLabel;
	JLabel orderStatusLabel;
	JLabel customerNameLabel;
	JLabel costLabel;
	JLabel orderDate;
	JLabel bikeName;
	
	GenericAbstractTableModel _loadedParentTable;
	
	
	
	public ExpandedBikeView(OrderModelRow orderModelRow, boolean staffMember, GenericAbstractTableModel parentTable, String title) {
		_loadedParentTable = parentTable;
		_row = orderModelRow;
		_staffMember = staffMember;
		currentOrder = OrderOperations.getOrder(_row.getOrderNumber());
		currentBike = currentOrder.get_bike();

		setLocationRelativeTo(null); //Compact panel centring
    	setLayout(new GridBagLayout());
    	
    	orderDate = new JLabel(currentOrder.get_date());
    	bikeName = new JLabel(currentOrder.get_bike().getCustomerGivenName());
    	
    	if(bikeName.getText().length() > 15)
    		bikeName.setText(bikeName.getText().substring(0, 13) + "...");
    	
    	orderNumLabel = new JLabel(orderModelRow.getOrderNumber().toString());
    	orderStatusLabel = new JLabel(orderModelRow.getOrderStatus().toString());
    	customerNameLabel = new JLabel(currentOrder.get_customer().get_full_name());
    	costLabel = new JLabel(orderModelRow.getCost().toString());
    	
		addComponents();
		setTitle(title);
		showDialog();	
	}
	
	public void showDialog() {
        pack();
        setLocationRelativeTo(getParent());
		setSize(500 ,300);
        setVisible(true);
    }
	
	private void addComponents() {
		BicycleComponentTable bikeTable = new BicycleComponentTable();
		bikeTable.setPreferredSize(new Dimension(480, 83));
		tablePanel.add(bikeTable);

		
		Container contentPanel = getContentPane();
		GridBagConstraints c = new GridBagConstraints();
		
		infoPanel.add(new JLabel("Order Number:"));
		infoPanel.add(orderNumLabel);
		
		infoPanel.add(new JLabel("Order Status:"));
		if(_staffMember) {
			orderStatusCombo = new JComboBox<OrderStatus>(OrderStatus.values());
			orderStatusCombo.setSelectedItem(currentOrder.get_order_status());			
			infoPanel.add(orderStatusCombo);
		}
		else {
			infoPanel.add(orderStatusLabel);
		}
		
		infoPanel.add(new JLabel("Order Date:"));
		infoPanel.add(orderDate);
		
		infoPanel.add(new JLabel("Frame Name:"));
		infoPanel.add(bikeName);
				
		infoPanel.add(new JLabel("Customer Name:"));
		infoPanel.add(customerNameLabel);
		
		infoPanel.add(new JLabel("Cost:"));
		infoPanel.add(costLabel);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.weighty = 0.75;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 40;
		
		contentPanel.add(infoPanel, c);
		
		c.ipady = 0;
		c.weighty = 0.25;
		c.gridy = 1;

		contentPanel.add(tablePanel, c);
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		
		c.gridy = 2;
		contentPanel.add(buttonPanel, c);

		if(_staffMember) {
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(0,10,0,10);
			confirmButton = new JButton("Submit Changes");
			confirmButton.addActionListener(this);
			buttonPanel.add(confirmButton, c);
		}
		
		if(currentOrder.get_order_status() == OrderStatus.PENDING && _loadedParentTable != null) {
			c.gridx = 1;
			deleteOrder = new JButton("Delete Order");
			deleteOrder.addActionListener(this);
			buttonPanel.add(deleteOrder, c);
		}
	}
	
	public static Bicycle getBike() {
		return currentBike;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == deleteOrder) {
				Object[] options = {"Yes", "No"};
				int n = JOptionPane.showOptionDialog(this,
					"Are you sure you want to delete this order?\nIt cannot be undone.",
					"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
				if(n == 0) {
					boolean result = OrderOperations.deleteOrder(currentOrder);
					
					String message;
					
					if(result) {
						message = "Successful";
					}
					else {
						message = "Unsuccessful";
					}
					
					JOptionPane.showMessageDialog(this, "Order Deletion Was " + message + ".");
					
					if(_loadedParentTable != null && _loadedParentTable.getClass() == OrderTableModel.class) {
						_loadedParentTable.deleteRow();
					}
					else if(_loadedParentTable != null && _loadedParentTable.getClass() == PastOrderTableModel.class) {
						LoggedInCustomerMenu.removeOrder(currentOrder);
					}
					
					this.dispose();
				}
		}
		else {
			if((OrderStatus)orderStatusCombo.getSelectedItem() != _row.getOrderStatus()) {
				_row.setOrderStatus((OrderStatus) orderStatusCombo.getSelectedItem());
				_loadedParentTable.addToChanged(_row);
				_loadedParentTable.fireTableDataChanged();
				this.dispose();
			}
		}
	}
	
}
