package View.StaffWindow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;

public class OrderTable extends AbstractTable<OrderModelRow> {

	@Override
	protected  List<Column<OrderModelRow, ?>> getColumns() {
		
		final Column<OrderModelRow, Integer> orderNumberColumn = new Column<OrderModelRow, Integer>("Order No", OrderModelRow::getOrderNumber, Integer.class);
		
		final Column<OrderModelRow, String> customerGivenNameColumn = new Column<OrderModelRow, String>("Given name", OrderModelRow::getCustomerGivenName, String.class);
		
		final Column<OrderModelRow, Bicycle> bikeColumn = new Column<OrderModelRow, Bicycle>("Bicycle", OrderModelRow::getBicycle, Bicycle.class);
		
		final Column<OrderModelRow, Double> costColumn = new Column<OrderModelRow, Double>("Cost", OrderModelRow::getCost, Double.class);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn);
		
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
