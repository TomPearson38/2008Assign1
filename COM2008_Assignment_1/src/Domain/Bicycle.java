package Domain;

public class Bicycle {
	private int _id;
	private Frameset _frame;
	private Handlebar _handlebar;
	private Wheel _wheels;
	private String _frameName;

	public Bicycle(int _id, Frameset frame, Handlebar handBar, Wheel wheels, String frameName) {
		_frame = frame;
		_handlebar = handBar;
		_wheels = wheels;
		_frameName = frameName;
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
	public void set_Wheels(Wheel _frontWheel) {
		this._wheels = _frontWheel;
	}
	
	public String bicycleBrand() {
		return get_frame().BrandName() + " " + _wheels.get_tyre().toString();
	}
	
	public String getFrameName() {
		return _frameName;
	}
	
	public void setFrameName(String newName) {
		_frameName = newName;
	}
	
	
}
