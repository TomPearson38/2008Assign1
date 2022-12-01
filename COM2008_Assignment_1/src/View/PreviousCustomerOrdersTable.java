package View;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;
import View.StaffWindow.ExpandedBikeView;
import View.StaffWindow.ManageOrdersPanel;
import View.StaffWindow.OrderModelRow;
import View.StaffWindow.OrderTable.OrderTableModel;
import View.Table.AbstractTable;
import View.Table.Column;
import View.Table.GenericAbstractTableModel;
import View.Table.SterlingRenderer;
import View.PreviousCustomerOrders;

public class PreviousCustomerOrdersTable extends AbstractTable<OrderModelRow>{
	private PastOrderTableModel loadedOrderTableModel;
	protected Collection<Order> allOrders;
	
	@Override
	protected  List<Column<OrderModelRow, ?>> getColumns() {
		
		final Column<OrderModelRow, Integer> orderNumberColumn = new Column<OrderModelRow, Integer>("Order No", OrderModelRow::getOrderNumber, Integer.class);
		
		final Column<OrderModelRow, String> customerGivenNameColumn = new Column<OrderModelRow, String>("Given name", OrderModelRow::getCustomerGivenName, 200, String.class);
		
		final Column<OrderModelRow, Bicycle> bikeColumn = new Column<OrderModelRow, Bicycle>("Bicycle", OrderModelRow::getBicycle, Bicycle.class);
		
		final Column<OrderModelRow, Double> costColumn = new Column<OrderModelRow, Double>("Cost", OrderModelRow::getCost, Double.class);
		costColumn.setCustomRenderer(new SterlingRenderer());
		
		final Column<OrderModelRow, OrderStatus> orderStatusColumn = new Column<OrderModelRow, OrderStatus>("Status", OrderModelRow::getOrderStatus, OrderStatus.class);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn, orderStatusColumn);
		
	}
	

	@Override
	protected GenericAbstractTableModel<OrderModelRow> getTableModel() {
		
		Collection<Order> allOrders = PreviousCustomerOrders.getCustomerOrders();
			
		loadedOrderTableModel = new PastOrderTableModel(allOrders, getColumns());
		
		return loadedOrderTableModel;
	}
	
	@Override
	protected void doubleClicked(OrderModelRow row) {
		new ExpandedBikeView(row, false, loadedOrderTableModel, "Selected Order");
	}
	
	
	public class PastOrderTableModel extends GenericAbstractTableModel<OrderModelRow> {
		
		
		public PastOrderTableModel(Collection<Order> orders, List<Column<OrderModelRow, ?>> columns) {
			super(orders.stream().map(OrderModelRow::new).collect(Collectors.toList()), columns);
		}
		
		@Override
		public void deleteRow(Order removeOrder) {
			allOrders.remove(removeOrder);
			loadedOrderTableModel = new PastOrderTableModel(allOrders, getColumns());
		}
	}
}
