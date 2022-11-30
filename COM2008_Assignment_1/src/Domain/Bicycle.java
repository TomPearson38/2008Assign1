package Domain;

public class Bicycle implements IToUIString, ICost, IBrandName {
	private int _id;
	private Frameset _frame;
	private Handlebar _handlebar;
	private Wheel _wheels;
	private String _givenName;

	public Bicycle(int id, Frameset frame, Handlebar handBar, Wheel wheels, String givenName) {
		_id = id;
		_frame = frame;
		_handlebar = handBar;
		_wheels = wheels;
		_givenName = givenName;
	}
	
	public int get_id() {
		return _id;
	}
	
	public Frameset get_frame() {
		return _frame;
	}
	
	public Handlebar get_handlebar() {
		return _handlebar;
	}
	
	public Wheel get_Wheels() {
		return _wheels;
	}
	
	public double getCost() {
		final double assemblyCharge = 10.0;
		return get_frame().getCost() + get_handlebar().getCost() + get_Wheels().getCost() + assemblyCharge;
	}
	
	public String getBrandName() {
		return get_frame().getBrandName() + " " + _wheels.get_tyre().toString();
	}
	
	@Override
	public String toString() {
		return get_frame().getBrandName();
	}

	public String getCustomerGivenName() {
		return _givenName;
	}

	@Override
	public String toUIString() {
		return "<html> " + getCustomerGivenName() + "</html>";
	}
	
	
}
