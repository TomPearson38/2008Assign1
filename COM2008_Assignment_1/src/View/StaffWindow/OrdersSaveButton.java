package View.StaffWindow;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Database.OrderOperations;
import Domain.Order;
import Resources.ResourceSingleton;
import View.Table.EditedObjectsChangedListener;

public class OrdersSaveButton extends JButton implements EditedObjectsChangedListener<OrderModelRow>, ActionListener {
	
	final static String buttonText = "Save";
	
	private Collection<OrderModelRow> editedOrderModelRows;
	
	public OrdersSaveButton() {
		super(buttonText);
		
		this.setEnabled(false);
		
		this.addActionListener(this);
		
		this.setIcon(new ImageIcon(ResourceSingleton.getSaveIcon()));
		
	}

	@Override
	public void editedObjectsChanged(Object source, Collection<OrderModelRow> editedObjects) {
		boolean anyObjectsInEditedObjectsCollection = editedObjects.size() > 0;
		
		editedOrderModelRows = editedObjects;
		
		this.setEnabled(anyObjectsInEditedObjectsCollection);
	}
	
	private Collection<Order> mapOrderModelRowsToOrders(Collection<OrderModelRow> modelRows) {
		return modelRows.stream().<Order>map(OrderModelRow::getBackingOrder).collect(Collectors.toList());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final Collection<Order> ordersToUpdate = mapOrderModelRowsToOrders(editedOrderModelRows);
		
		OrderOperations.updateOrders(ordersToUpdate);
		
	}
	
}