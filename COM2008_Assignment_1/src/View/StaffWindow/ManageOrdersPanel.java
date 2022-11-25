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
	private int previousClick = -1;
	
	public ManageOrdersPanel() {
		super();
		
		addComponents();
	}
	
	private void addComponents() {
		
		final OrderTable ordersTable = new OrderTable();

		
		this.add(ordersTable);
	
	
		ordersTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if(previousClick == row) {
			        System.out.println(row);
			        previousClick = -1;
		        }
		        else {
		        	previousClick = row;
		        }
		        
		    }
		});
	
	}
		
	
}
