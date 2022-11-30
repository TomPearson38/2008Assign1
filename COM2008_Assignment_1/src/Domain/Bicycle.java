package Domain;

public class Bicycle implements IToUIString {
	private int _id;
	private Frameset _frame;
	private Handlebar _handlebar;
	private Wheel _wheels;
	private String _frameName;

	public Bicycle(int id, Frameset frame, Handlebar handBar, Wheel wheels, String frameName) {
		_id = id;
		_frame = frame;
		_handlebar = handBar;
		_wheels = wheels;
		_frameName = frameName;
	}
	
	public int get_id() {
		return _id;
	}
	
	public Frameset get_frame() {
		return _frame;
	}
	public void set_frame(Frameset _frame) {
		this._frame = _frame;
	}
	
	public Handlebar get_handlebar() {
		return _handlebar;
	}
	public void set_handlebar(Handlebar _handlebar) {
		this._handlebar = _handlebar;
	}
	
	public Wheel get_Wheels() {
		return _wheels;
	}
	
	public Double getCost() {
		final double assemblyCharge = 10.0;
		return get_frame().Cost() + get_handlebar().Cost() + get_Wheels().Cost() + assemblyCharge;
	}
	
	public void set_Wheels(Wheel _frontWheel) {
		this._wheels = _frontWheel;
	}
	
	public String bicycleBrand() {
		return get_frame().BrandName() + " " + _wheels.get_tyre().toString();
	}
	
	@Override
	public String toString() {
		return get_frame().BrandName();
	}

	public String getFrameName() {
		return _frameName;
	}
	
	public void setFrameName(String newName) {
		_frameName = newName;
	}

	@Override
	public String toUIString() {
		return "<html> " + bicycleBrand() + "</html>";
	}
	
	
}
