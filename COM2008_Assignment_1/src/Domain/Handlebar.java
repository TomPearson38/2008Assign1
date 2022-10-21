package Domain;

public class Handlebar extends BicycleComponent {
	private HandlebarStyles _style;
	
	public Handlebar(int _id, String _brandName, int _serialNumber, double _cost) {
		super(_id, _brandName, _serialNumber, _cost);
	}

	public HandlebarStyles get_style() {
		return _style;
	}

	public void set_style(HandlebarStyles _style) {
		this._style = _style;
	}
	
}
