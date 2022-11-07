package Domain;

public class Handlebar extends BicycleComponent implements IToUIString {
	private HandlebarStyles _style;
	
	public Handlebar(int _id, String _brandName, int _serialNumber, double _cost, HandlebarStyles _style) {
		super(_id, _brandName, _serialNumber, _cost);
		this._style = _style;
	}

	public HandlebarStyles get_style() {
		return _style;
	}

	@Override
	public String toUIString() {
		return "<html>" + BrandName() + "<br>" + get_style() + "</html>";
	}	
}
