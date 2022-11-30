package Domain;

/**
 * An abstract class that contains the elements that are common between all
 * individual bicycle components.
 * @author Alex Dobson
 *
 */
public abstract class BicycleComponent extends DataRecord implements IBicycleComponent {
	private String _brandName;
	private int _serialNumber;
	private double _cost;
	private int _stockNum;
	
	/**
	 * Called when a child class creates a new instance of BicycleComponent in order
	 * to declare common features
	 * @param _id
	 * @param _brandName
	 * @param _serialNumber
	 * @param _cost
	 * @param _stockNum
	 */
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
	
	/**
	 * Used when the bike part is being used in an order.
	 * Allows for accurate stock tracking
	 */
	@Override
	public void reduceStockNum() {
		if(_stockNum > 0)
			_stockNum--;
	}
	
}
