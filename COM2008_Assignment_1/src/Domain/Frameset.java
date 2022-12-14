package Domain;

/**
 * Contains the frame part of bicycle component
 * @author Alex Dobson
 *
 */
public class Frameset extends BicycleComponent implements IDataRecord, IToUIString{
	private double _size;
	private boolean _shocks;
	private Gearset _gearset;
	
	/**
	 * Initialised frameset that doesn't contain if shocks are present
	 * @param _id
	 * @param _brandName
	 * @param _serialNumber
	 * @param _cost
	 * @param _size
	 * @param _gearset Gearset ENUM of current gearset
	 * @param _stockNum
	 */
	public Frameset(int _id, String _brandName, int _serialNumber, double _cost, double _size, Gearset _gearset, int _stockNum) {
		this(_id, _brandName, _serialNumber, _cost, _size, _gearset, false, _stockNum);
	}
	
	/**
	 * Initialised frameset that does contain if shocks are present
	 * @param _id
	 * @param _brandName
	 * @param _serialNumber
	 * @param _cost
	 * @param _size
	 * @param _gearset
	 * @param _shocks
	 * @param _stockNum
	 */
	public Frameset(int _id, String _brandName, int _serialNumber, double _cost, double _size, Gearset _gearset, boolean _shocks, int _stockNum) {
		super(_id, _brandName, _serialNumber, _cost, _stockNum);
		this._size = _size;
		this._shocks = _shocks;
		this._gearset = _gearset;
	}
	
	/*
	 * returns the size of the frame in cm
	 */
	public double get_size() {
		return _size;
	}
	
	/*
	 * whether the frame has shock absorbers
	 */
	public boolean get_shocks() {
		return _shocks;
	}
	public void set_shocks(boolean value) {
		_shocks = value;
	}
	
	public Gearset get_gearset() {
		return _gearset;
	}

	@Override
	public String toUIString() {
		// TODO Auto-generated method stub
		return "<html>" + getBrandName() + "<br>" + get_size() + "cm" + "<br>" + "?" + getCost() + "</html>";
	}

	/**
	 * Checks to see if two Frameset objects are equal
	 * @return if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frameset other = (Frameset) obj;
		if (_gearset == null) {
			if (other._gearset != null)
				return false;
		} else if (!_gearset.equals(other._gearset))
			return false;
		if (_shocks != other._shocks)
			return false;
		if (Double.doubleToLongBits(_size) != Double.doubleToLongBits(other._size))
			return false;
		return true;
	}

}
