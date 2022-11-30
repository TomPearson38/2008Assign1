package Domain;

public class Order {
	private int _order_number;
	private Customer _customer;
	private double _cost;
	private OrderStatus _order_status;
	private Bicycle _bike;
	private int _serial_number;
	private String _date;
	
	public Order(int orderNum, Customer customer,
			double cost, OrderStatus os, Bicycle bike, int serNum, String date) {
		_order_number = orderNum;
		_customer = customer;
		_cost = cost;
		_order_status = os;
		_bike = bike;
		_serial_number = serNum;
		_date = date;
	}
	
	public int get_order_number() {
		return _order_number;
	}
	public void set_order_number(int _order_number) {
		this._order_number = _order_number;
	}
	public Customer get_customer() {
		return _customer;
	}
	public void set_customer(Customer _customer) {
		this._customer = _customer;
	}

	public double get_cost() {
		return _cost;
	}
	public void set_cost(double _cost) {
		this._cost = _cost;
	}
	public OrderStatus get_order_status() {
		return _order_status;
	}
	public void set_order_status(OrderStatus _order_status) {
		this._order_status = _order_status;
	}
	public Bicycle get_bike() {
		return _bike;
	}
	public void set_bike(Bicycle _bike) {
		this._bike = _bike;
	}
	public int get_serial_number() {
		return _serial_number;
	}
	public void set_serial_number(int _serial_number) {
		this._serial_number = _serial_number;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}
	
}
