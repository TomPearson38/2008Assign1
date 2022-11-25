package View.StaffWindow;

import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Point;

import Database.OrderOperations;
import Domain.Order;

public class ManageOrdersPanel extends JPanel {
	
	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		
		final OrderTable ordersTable = new OrderTable();

		
		this.add(ordersTable);
	
	
		
	
	}
		
	
}
