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
import View.Table.AbstractTable;
import View.Table.Column;
import View.Table.ComboBoxEditor;
import View.Table.EnumRenderer;
import View.Table.GenericAbstractTableModel;
import View.Table.SterlingRenderer;

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
		orderStatusColumn.setCustomEditor(new ComboBoxEditor<OrderStatus>(OrderStatus.values()));
		orderStatusColumn.setEditable(true);
		orderStatusColumn.setValueSetter(OrderModelRow::setOrderStatus);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn, orderStatusColumn);
		
	}
	

	@Override
	protected GenericAbstractTableModel<OrderModelRow> getTableModel() {
		
		final Collection<Order> allOrders = OrderOperations.getAllOrders();
		
		return new OrderTableModel(allOrders, getColumns());
	}
	
	
	public class OrderTableModel extends GenericAbstractTableModel<OrderModelRow> {
		
		
		public OrderTableModel(Collection<Order> orders, List<Column<OrderModelRow, ?>> columns) {
			super(orders.stream().map(OrderModelRow::new).collect(Collectors.toList()), columns);
			
		}
	}
	
}
