package View.StaffWindow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Domain.Bicycle;
import Domain.Order;
import Domain.OrderStatus;

class OrdersTableModel implements TableModel {
	private List<OrderModelRow> orders;
	
	private List<Column<OrderModelRow, ?>> columns;

	public OrdersTableModel(Collection<Order> orders) {
		super();
		
		this.orders = orders.stream().map(OrderModelRow::new).collect(Collectors.toList());
		columns = getColumns();
	}
	
	private List<Column<OrderModelRow, ?>> getColumns() {
		
		final Column<OrderModelRow, Integer> orderNumberColumn = new Column<OrderModelRow, Integer>("Order No", OrderModelRow::getOrderNumber, Integer.class);
		
		final Column<OrderModelRow, String> customerGivenNameColumn = new Column<OrderModelRow, String>("Given name", OrderModelRow::getCustomerGivenName, String.class);
		
		final Column<OrderModelRow, Bicycle> bikeColumn = new Column<OrderModelRow, Bicycle>("Bicycle", OrderModelRow::getBicycle, Bicycle.class);
		
		final Column<OrderModelRow, Double> costColumn = new Column<OrderModelRow, Double>("Cost", OrderModelRow::getCost, Double.class);
		
		final Column<OrderModelRow, OrderStatus> orderStatus = new Column<OrderModelRow, OrderStatus>("Order Status", OrderModelRow::getOrderStatus, OrderStatus.class);
		
		return Arrays.asList(orderNumberColumn, customerGivenNameColumn, bikeColumn, costColumn, orderStatus);
	}

	@Override
	public int getRowCount() {
		return orders.size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex).getName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columns.get(columnIndex).getUnderlyingClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final OrderModelRow row = orders.get(rowIndex);
		
		final Column<OrderModelRow, ?> column = columns.get(columnIndex);
		
		return column.getValue(row);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Column represents a column in the TableModel where O is the type of the underlying object and T the type of the return object
	 */
	private class Column<O, T> {
		private String name;
		private Function<O, T> getValueFromObject;
		private Class<T> underlyingType;
		
		public Column(String name, Function<O, T> getValueFromObject, Class<T> type) {
			super();
			this.name = name;
			this.getValueFromObject = getValueFromObject;
			this.underlyingType = type;
		}
		
		public String getName() {
			return name;
		}

		public T getValue(O object) {
			return getValueFromObject.apply(object);
		}
		
		public Class<?> getUnderlyingClass() {
			return underlyingType;
		}
		
	}
	
	private class OrderModelRow {
		private Order backingOrder;
		
		public OrderModelRow(Order backingOrder) {
			super();
			this.backingOrder = backingOrder;
		}

		public String getCustomerGivenName() {
			return backingOrder.get_customer_given_name();
		}
		
		public Double getCost() {
			return backingOrder.get_cost();
		}
		
		public Integer getOrderNumber() {
			return backingOrder.get_order_number();
		}
		
		public Bicycle getBicycle() {
			return backingOrder.get_bike();
		}
		
		public OrderStatus getOrderStatus() {
			return backingOrder.get_order_status();
		}
	}
	
}