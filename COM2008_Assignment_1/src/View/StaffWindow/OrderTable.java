package View.StaffWindow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;
import View.Table.AbstractTable;
import View.Table.Column;
import View.Table.ComboBoxEditor;
import View.Table.EnumRenderer;
import View.Table.GenericAbstractTableModel;
import View.Table.SterlingRenderer;

/**
 * Represents a Table that contains information about all the orders in the database
 * @author Alex Dobson
 */
public class OrderTable extends AbstractTable<OrderModelRow> {
	private OrderTableModel loadedOrderTableModel;
	
	
	@Override
	protected  List<Column<OrderModelRow, ?>> getColumns() {
		
		final Column<OrderModelRow, Integer> orderNumberColumn = new Column<OrderModelRow, Integer>("Order No", OrderModelRow::getOrderNumber, Integer.class);
		
		final Column<OrderModelRow, String> customerGivenNameColumn = new Column<OrderModelRow, String>("Given name", OrderModelRow::getCustomerGivenName, 200, String.class);
		
		final Column<OrderModelRow, Bicycle> bikeColumn = new Column<OrderModelRow, Bicycle>("Bicycle", OrderModelRow::getBicycle, Bicycle.class);
		
		final Column<OrderModelRow, Double> costColumn = new Column<OrderModelRow, Double>("Cost", OrderModelRow::getCost, Double.class);
		costColumn.setCustomRenderer(new SterlingRenderer());
		
		final Column<OrderModelRow, OrderStatus> orderStatusColumn = new Column<OrderModelRow, OrderStatus>("Status", OrderModelRow::getOrderStatus, OrderStatus.class);
		orderStatusColumn.setCustomRenderer(new EnumRenderer<OrderStatus>(OrderStatus.values()));
		orderStatusColumn.setCustomEditor(new ComboBoxEditor<OrderStatus>(OrderStatus.values()));
		orderStatusColumn.setEditable(true);
		orderStatusColumn.setValueSetter(OrderModelRow::setOrderStatus);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn, orderStatusColumn);
		
	}
	

	@Override
	protected GenericAbstractTableModel<OrderModelRow> getTableModel() {
		
		final Collection<Order> allOrders = OrderOperations.getAllOrders();
		
		loadedOrderTableModel = new OrderTableModel(allOrders, getColumns());
		
		return loadedOrderTableModel;
	}
	
	/*
	 * Create a pop up when a row is double clicked to show more information about that row
	 */
	@Override
	protected void doubleClicked(OrderModelRow row) {
		new ExpandedBikeView(row, true, loadedOrderTableModel, "Edit Order");
	}
	
	public class OrderTableModel extends GenericAbstractTableModel<OrderModelRow> {
		
		
		public OrderTableModel(Collection<Order> orders, List<Column<OrderModelRow, ?>> columns) {
			//Maps a list of domain objects (<Order>) into their ViewModel objects (<OrderModelRow>)
			super(orders.stream().map(OrderModelRow::new).collect(Collectors.toList()), columns);
		}
		
		@Override
		public void deleteRow() {
			((ManageOrdersPanel) getParent()).redraw();
		}
	}
	
}
