package Domain;

public abstract class BicycleComponent implements IBicycleComponent {
	private String _brandName;
	private int _serialNumber;
	private double _cost;
	
	public BicycleComponent(String _brandName, int _serialNumber, double _cost) {
		super();
		this._brandName = _brandName;
		this._serialNumber = _serialNumber;
		this._cost = _cost;
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
	
}
