package Domain;

public class Frameset extends BicycleComponent {
	private double _size;
	private boolean _shocks;
	private Gearset _gearset;
	
	public Frameset(String _brandName, int _serialNumber, double _cost) {
		super(_brandName, _serialNumber, _cost);
	}
	
	/*
	 * returns the size of the frame in cm
	 */
	public double get_size() {
		return _size;
	}
	void set_size(double value) {
		_size = value;
	}
	
	/*
	 * whether the frame has shock absorbers
	 */
	public boolean get_shocks() {
		return _shocks;
	}
	void set_shocks(boolean value) {
		_shocks = value;
	}
	
	public Gearset get_gearset() {
		return _gearset;
	}
	void set_gearset(Gearset _gearset) {
		this._gearset = _gearset;
	}

}
