package View.StaffWindow;

import Database.BicycleOperations;
import Domain.Bicycle;
import Domain.BicycleComponent;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Order;
import Domain.OrderStatus;
import Domain.Wheel;

public class PartsModelRow {
	private BicycleComponent bikePart;
	
	public PartsModelRow(BicycleComponent _bikePart) {
		bikePart = _bikePart;
	}

	public String get_name() {
		if(bikePart instanceof Frameset) {
			return "Frame";
		}
		else if(bikePart instanceof Wheel) {
			return "Wheels";
		}
		else if(bikePart instanceof Handlebar) {
			return "Handlebars";
		}
		else {
			return "ERROR";
		}
	}

	public int get_partId() {
		return bikePart.get_id();
	}

	public String get_brand() {
		return bikePart.BrandName();
	}

	public int get_partSerialNumber() {
		return bikePart.SerialNumber();
	}

	public double get_cost() {
		return bikePart.Cost();
	}	
}
