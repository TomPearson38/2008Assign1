package Domain;

public abstract class BicycleComponent extends DataRecord implements IBicycleComponent {
	private String _brandName;
	private int _serialNumber;
	private double _cost;
	private int _stockNum;
	
	public BicycleComponent(int _id, String _brandName, int _serialNumber, double _cost, int _stockNum) {
		super(_id);
		this._brandName = _brandName;
		this._serialNumber = _serialNumber;
		this._cost = _cost;
		this._stockNum = _stockNum;
	}
	
	@Override
	public String BrandName() {
		return _brandName;
	}

	@Override
	public int SerialNumber() {
		return _serialNumber;
	}
	
	@Override
	public double Cost() {
		return _cost;
	}
	
	@Override
	public int StockNum() {
		return _stockNum;
	}
	
}
