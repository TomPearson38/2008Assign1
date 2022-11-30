package Domain;

import java.util.Objects;

public class Handlebar extends BicycleComponent implements IToUIString {

	private HandlebarStyles _style;
	
	public Handlebar(int _id, String _brandName, int _serialNumber, double _cost, HandlebarStyles _style, int _stockNum) {
		super(_id, _brandName, _serialNumber, _cost, _stockNum);
		this._style = _style;
	}

	public HandlebarStyles get_style() {
		return _style;
	}
	
	@Override
	public String toUIString() {
		// TODO Auto-generated method stub
		return "<html>" + getBrandName() + "<br>" + get_style().toString() + "</html>";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Handlebar other = (Handlebar) obj;
		return _style == other._style;
	}
	
	
}
