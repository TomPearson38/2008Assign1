package View.StaffWindow;

import Domain.Bicycle;
import Domain.Customer;
import Domain.Order;
import Domain.OrderStatus;

/**
 * ViewModel object to mask interaction between UI and backing domain objects
 * @author Alex Dobson
 */
public class OrderModelRow {
	private Order backingOrder;
	
	public OrderModelRow(Order backingOrder) {
		super();
		this.backingOrder = backingOrder;
	}
	
	/*
	 * Only to be used when finished with the OrderModelRow, if you need information from the backing object during day-to-day usage, add a getter
	 */
	public Order getBackingOrder() {
		return backingOrder;
	}

	public String getCustomerGivenName() {
		return backingOrder.get_bike().getCustomerGivenName();
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
	
	public Customer getCustomer() {
		return backingOrder.get_customer();
	}
	public void setOrderStatus(OrderStatus value) {
		backingOrder.set_order_status(value);
	}
}
