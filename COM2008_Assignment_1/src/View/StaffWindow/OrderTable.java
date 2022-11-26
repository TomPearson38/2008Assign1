package View.StaffWindow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;

public class OrderTable extends AbstractTable<OrderModelRow> {

	@Override
	protected  List<Column<OrderModelRow, ?>> getColumns() {
		
		final Column<OrderModelRow, Integer> orderNumberColumn = new Column<OrderModelRow, Integer>("Order No", OrderModelRow::getOrderNumber, Integer.class);
		
		final Column<OrderModelRow, String> customerGivenNameColumn = new Column<OrderModelRow, String>("Given name", OrderModelRow::getCustomerGivenName, 200, String.class);
		
		final Column<OrderModelRow, Bicycle> bikeColumn = new Column<OrderModelRow, Bicycle>("Bicycle", OrderModelRow::getBicycle, Bicycle.class);
		
		final Column<OrderModelRow, Double> costColumn = new Column<OrderModelRow, Double>("Cost", OrderModelRow::getCost, Double.class);
		costColumn.setCustomRenderer(new SterlingRenderer());
		
		final Column<OrderModelRow, OrderStatus> orderStatusColumn = new Column<OrderModelRow, OrderStatus>("Status", OrderModelRow::getOrderStatus, OrderStatus.class);
		orderStatusColumn.setCustomRenderer(new EnumRenderer<OrderStatus>(OrderStatus.values()));
		orderStatusColumn.setCustomEditor(new DefaultCellEditor(new JComboBox<OrderStatus>(OrderStatus.values())));
		orderStatusColumn.setEditable(true);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn, orderStatusColumn);
		
	}
	

	@Override
	protected AbstractTableModel<OrderModelRow> getTableModel() {
		
		final Collection<Order> allOrders = OrderOperations.getAllOrders();
		
		return new OrderTableModel(allOrders, getColumns());
	}
	
	
	public class OrderTableModel extends AbstractTableModel<OrderModelRow> {
		
		
		public OrderTableModel(Collection<Order> orders, List<Column<OrderModelRow, ?>> columns) {
			super(orders.stream().map(OrderModelRow::new).collect(Collectors.toList()), columns);
			
		}
	}
	
}
