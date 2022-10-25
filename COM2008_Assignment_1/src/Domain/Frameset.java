package Domain;

public class Frameset extends BicycleComponent implements IDataRecord, IToUIString{
	private double _size;
	private boolean _shocks;
	private Gearset _gearset;
	
	public Frameset(int _id, String _brandName, int _serialNumber, double _cost, double _size, Gearset _gearset) {
		this(_id, _brandName, _serialNumber, _cost, _size, _gearset, false);
	}
	
	public Frameset(int _id, String _brandName, int _serialNumber, double _cost, double _size, Gearset _gearset, boolean _shocks) {
		super(_id, _brandName, _serialNumber, _cost);
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
		return "<html>" + BrandName() + "<br>" + get_size() + "cm" + "</html>";
	}
}
