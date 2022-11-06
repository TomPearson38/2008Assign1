package View;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;

public class WheelPicker extends AbstractPicker<Wheel>{

	public WheelPicker(JFrame parent) {
		super(parent);
		
	}
	
    public static Wheel chooseWheels(JFrame parent) {
    	WheelPicker PickerWindow = new WheelPicker(parent);
    	
    	PickerWindow.setTitle("Wheel Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Wheel> getAvailableObjects() {
		return WheelOperations.getAllWheels();
	}

	@Override
	protected Collection<PropertyDescriptor<Wheel>> getPropertyDescriptors() {
		PropertyDescriptor<Wheel> BrandNameDescriptor = new PropertyDescriptor<Wheel>("Brand Name", wheel -> wheel.BrandName());
		PropertyDescriptor<Wheel> SerialNumberDescriptor = new PropertyDescriptor<Wheel>("Serial Number", wheel -> Integer.toString(wheel.SerialNumber()));
		PropertyDescriptor<Wheel> CostDescriptor = new PropertyDescriptor<Wheel>("Cost", wheel -> Double.toString(wheel.Cost()));
		PropertyDescriptor<Wheel> DiameterDescriptor = new PropertyDescriptor<Wheel>("Diameter", wheel -> Double.toString(wheel.get_diameter()));
		PropertyDescriptor<Wheel> TyreTypeDescriptor = new PropertyDescriptor<Wheel>("Tyre Type", wheel -> (wheel.get_tyre().toString()));
		PropertyDescriptor<Wheel> BrakeTypeDescriptor = new PropertyDescriptor<Wheel>("Brake Type", wheel -> (wheel.get_brakes().toString()));
		
		Collection<PropertyDescriptor<Wheel>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, DiameterDescriptor, TyreTypeDescriptor, BrakeTypeDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Wheel>> getFilters() {
		FilterValue<Wheel> hasRimBrakes = new FilterValue<Wheel>("Rim Breaks", wheel -> wheel.get_brakes() == BrakeType.RIM);
		FilterValue<Wheel> hasDiskBrakes = new FilterValue<Wheel>("Disk Breaks", wheel -> wheel.get_brakes() == BrakeType.DISK);
		Filter<Wheel> brakesFilter = new Filter<Wheel>("Brakes",  Arrays.asList(hasRimBrakes, hasDiskBrakes));
		
		FilterValue<Wheel> hasRoadTyres = new FilterValue<Wheel>("Road Tyres", wheel -> wheel.get_tyre() == TyreType.ROAD);
		FilterValue<Wheel> hasMountainTyres = new FilterValue<Wheel>("Mountain Tyres", wheel -> wheel.get_tyre() == TyreType.MOUNTAIN);
		FilterValue<Wheel> hasHybridTyres = new FilterValue<Wheel>("Hybrid Tyres", wheel -> wheel.get_tyre() == TyreType.HYBRID);
		Filter<Wheel> tyresFilter = new Filter<Wheel>("Tyres",  Arrays.asList(hasRoadTyres, hasMountainTyres, hasHybridTyres));
		
		return Arrays.asList(brakesFilter, tyresFilter);
	}

}
