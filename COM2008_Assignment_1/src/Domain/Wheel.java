package Domain;

public class Wheel extends BicycleComponent implements IDataRecord, IToUIString{
	private double _diameter;
	private TyreType _tyre;
	private BrakeType _brakes;
	
	public Wheel(int _id, String _brandName, int _serialNumber, double _cost, int _stockNum) {
		super(_id, _brandName, _serialNumber, _cost, _stockNum);
	}
	
	public Wheel(int _id, int _serialNumber, String _brandName, double _cost, double diameter, TyreType tyre, BrakeType brake, int _stockNum) {
		super(_id, _brandName, _serialNumber, _cost, _stockNum);
		_diameter = diameter;
		_tyre = tyre;
		_brakes = brake;
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
	
	@Override
	public String toUIString() {
		// TODO Auto-generated method stub
		return "<html>" + BrandName() + "<br>" + get_diameter() + "cm" + "</html>";
	}
	
	@Override
	public void reduceStockNum() {
		if(StockNum() > 1) {
			super.reduceStockNum();
			super.reduceStockNum();
		}
	}
}
