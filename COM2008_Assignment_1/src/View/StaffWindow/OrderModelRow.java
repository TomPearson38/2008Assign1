package View.StaffWindow;

import Domain.Bicycle;
import Domain.Order;

public class OrderModelRow {
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
}
