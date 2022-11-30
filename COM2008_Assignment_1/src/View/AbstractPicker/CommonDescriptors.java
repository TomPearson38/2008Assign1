package View.AbstractPicker;

import Domain.Bicycle;
import Domain.BicycleComponent;
import Domain.IBicycleComponent;
import Domain.ICost;

public class CommonDescriptors {
	public static PropertyDescriptor<ICost> getCostDescriptor() {
		return new PropertyDescriptor<ICost>("Cost", component -> Double.toString(component.getCost()));
	}
	
	public static PropertyDescriptor<IBicycleComponent> getBrandNameDescriptor() {
		return new PropertyDescriptor<IBicycleComponent>("BrandName", IBicycleComponent::getBrandName);
	}
	
	public static PropertyDescriptor<IBicycleComponent> getSerialNumberDescriptor() {
		return new PropertyDescriptor<IBicycleComponent>("SerialNumber", component -> Integer.toString(component.getSerialNumber()));
	}
	
	public static PropertyDescriptor<IBicycleComponent> getStockDescriptor() {
		return new PropertyDescriptor<IBicycleComponent>("Stock", component -> Integer.toString(component.getStockNum()));
	}
}
