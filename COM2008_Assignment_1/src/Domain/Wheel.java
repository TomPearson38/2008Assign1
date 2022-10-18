package Domain;

public class Wheel extends BicycleComponent {
	private double _diameter;
	private TyreType _tyre;
	private BrakeType _brakes;
	
	public Wheel(String _brandName, int _serialNumber, double _cost) {
		super(_brandName, _serialNumber, _cost);
	}
	
	/*
	 * wheel diameter in cm
	 */
	public double get_diameter() {
		return _diameter;
	}

	void set_diameter(double _diameter) {
		this._diameter = _diameter;
	}

	public TyreType get_tyre() {
		return _tyre;
	}

	public void set_tyre(TyreType _tyre) {
		this._tyre = _tyre;
	}

	public BrakeType get_brakes() {
		return _brakes;
	}

	public void set_brakes(BrakeType _brakes) {
		this._brakes = _brakes;
	}
}
