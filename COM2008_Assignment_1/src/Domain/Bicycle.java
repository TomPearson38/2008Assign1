package Domain;

public class Bicycle extends BicycleComponent {
	private Frameset _frame;
	private Handlebar _handlebar;
	private Wheel _wheels;
	
	public Bicycle(String _brandName, int _serialNumber, double _cost) {
		super(_brandName, _serialNumber, _cost);
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
	
	
}
