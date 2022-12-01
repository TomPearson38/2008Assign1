package View.StaffWindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;


import Database.CustomerOperations;
import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;
import View.StaffWindow.*;
import View.StaffWindow.OrderTable.OrderTableModel;
import View.Table.Column;
import View.Table.EditedObjectsChangedListener;
import View.Table.EnumRenderer;

public class ExpandedBikeView extends JDialog implements ActionListener{
	private Order currentOrder;
	private static Bicycle currentBike;
	private OrderModelRow _row;
	private JComboBox orderStatusCombo;
	private boolean _staffMember;
	private JButton confirmButton;
	
	JPanel tablePanel = new JPanel(new BorderLayout());
	JPanel infoPanel = new JPanel(new GridLayout(3,4));
	JLabel orderNumLabel;
	JLabel orderStatusLabel;
	JLabel customerNameLabel;
	JLabel costLabel;
	JLabel orderDate;
	JLabel bikeName;
	
	OrderTableModel _loadedOrderTable;
	
	
	
	public ExpandedBikeView(OrderModelRow orderModelRow, boolean staffMember, OrderTableModel loadedOrderTableModel, String title) {
		_loadedOrderTable = loadedOrderTableModel;
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
		ExpandOrderTable bikeTable = new ExpandOrderTable();
		bikeTable.setPreferredSize(new Dimension(480, 83));
		tablePanel.add(bikeTable);

		
		Container contentPanel = getContentPane();
		GridBagConstraints c = new GridBagConstraints();
		
		infoPanel.add(new JLabel("Order Number:"));
		infoPanel.add(orderNumLabel);
		
		infoPanel.add(new JLabel("Order Status:"));
		if(_staffMember) {
			orderStatusCombo = new JComboBox(OrderStatus.values());
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
		
		if(_staffMember) {
			confirmButton = new JButton("Submit Changes");
			confirmButton.addActionListener(this);
			c.gridy = 2;
			contentPanel.add(confirmButton, c);
		}
	}
	
	public static Bicycle getBike() {
		return currentBike;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((OrderStatus)orderStatusCombo.getSelectedItem() != _row.getOrderStatus()) {
			_row.setOrderStatus((OrderStatus) orderStatusCombo.getSelectedItem());
			_loadedOrderTable.addToChanged(_row);
			_loadedOrderTable.fireTableDataChanged();
			this.dispose();
		}
	}
	
}
