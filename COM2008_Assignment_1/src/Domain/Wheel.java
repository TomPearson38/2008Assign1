package Domain;

/**
 * Wheel is a bicycle component used to assemble a bike
 * @author Alex Dobson
 *
 */
public class Wheel extends BicycleComponent implements IDataRecord, IToUIString{
	private double _diameter;
	private TyreType _tyre;
	private BrakeType _brakes;
	
	/**
	 * A new wheel is initialised
	 * @param _id
	 * @param _serialNumber 
	 * @param _brandName
	 * @param _cost
	 * @param diameter
	 * @param tyre
	 * @param brake
	 * @param _stockNum
	 */
	public Wheel(int _id, int _serialNumber, String _brandName, double _cost, double diameter, TyreType tyre, BrakeType brake, int _stockNum) {
		super(_id, _brandName, _serialNumber, _cost, _stockNum);
		_diameter = diameter;
		_tyre = tyre;
		_brakes = brake;
	}

	/**
	 * Wheel diameter in CM
	 * @return Diameter
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
		return "<html>" + getBrandName() + "<br>" + get_diameter() + "cm" + "<br>" + get_tyre().toString() + "<br>" + get_brakes().toString() + "<br>" + "£" + getCost() + "</html>";
	}
	
	/**
	 * Wheels need to decrease the stock number by two when used in a bike
	 */
	@Override
	public void reduceStockNum() {
		if(getStockNum() > 1) {
			super.reduceStockNum();
			super.reduceStockNum();
		}
	}

	/**
	 * Checks if current wheel is equal to another provided wheel
	 * @param obj Compared object
	 * @return Equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wheel other = (Wheel) obj;
		return _brakes == other._brakes
				&& Double.doubleToLongBits(_diameter) == Double.doubleToLongBits(other._diameter)
				&& _tyre == other._tyre;
	}
	
	
}
