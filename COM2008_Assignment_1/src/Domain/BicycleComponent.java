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
	public String getBrandName() {
		return _brandName;
	}

	@Override
	public int getSerialNumber() {
		return _serialNumber;
	}
	
	@Override
	public double getCost() {
		return _cost;
	}
	
	@Override
	public int getStockNum() {
		return _stockNum;
	}
	
	@Override
	public void reduceStockNum() {
		if(_stockNum > 0)
			_stockNum--;
	}
	
}
