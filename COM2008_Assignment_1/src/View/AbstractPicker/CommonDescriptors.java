package View.AbstractPicker;

import Domain.Bicycle;
import Domain.ICost;

public class CommonDescriptors {
	public static PropertyDescriptor<ICost> getCostDescriptor() {
		return new PropertyDescriptor<ICost>("Cost", bike -> Double.toString(bike.getCost()));
	}
}
