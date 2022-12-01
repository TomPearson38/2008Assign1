package View.Pickers;

import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.IBicycleComponent;
import Domain.ICost;
import Domain.TyreType;
import Domain.Wheel;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.CommonDescriptors;
import View.AbstractPicker.CommonFilters;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;
import View.CreatorsAndEditors.WheelEditor;

public class WheelPicker extends AbstractPicker<Wheel>{


	public WheelPicker(Window owner, Boolean isStaffMode) {
		super(owner, isStaffMode);
	}


    
	public static Wheel chooseWheels(Window parent) { return WheelPicker.chooseWheels(parent, false); }
    public static Wheel chooseWheels(Window parent, boolean managementMode) {
    	WheelPicker PickerWindow = new WheelPicker(parent, managementMode);
    	return configureAndShowPicker(PickerWindow);
    }
    private static Wheel configureAndShowPicker(WheelPicker pickerWindow) {
    	pickerWindow.setTitle("Wheel Picker");
    	
    	return pickerWindow.showDialog(); 
    }

	@Override
	protected Collection<Wheel> getAvailableObjects() {
		return WheelOperations.getAllWheels();
	}

	@Override
	protected Collection<PropertyDescriptor<? super Wheel>> getPropertyDescriptors() {
		PropertyDescriptor<IBicycleComponent> BrandNameDescriptor = CommonDescriptors.getBrandNameDescriptor();
		PropertyDescriptor<IBicycleComponent> SerialNumberDescriptor = CommonDescriptors.getSerialNumberDescriptor();
		PropertyDescriptor<ICost> CostDescriptor = CommonDescriptors.getCostDescriptor();
		PropertyDescriptor<IBicycleComponent> StockDescriptor = CommonDescriptors.getStockDescriptor();
		PropertyDescriptor<Wheel> DiameterDescriptor = new PropertyDescriptor<Wheel>("Diameter", wheel -> Double.toString(wheel.get_diameter()));
		PropertyDescriptor<Wheel> TyreTypeDescriptor = new PropertyDescriptor<Wheel>("Tyre Type", wheel -> (wheel.get_tyre().toString()));
		PropertyDescriptor<Wheel> BrakeTypeDescriptor = new PropertyDescriptor<Wheel>("Brake Type", wheel -> (wheel.get_brakes().toString()));
		
		
		Collection<PropertyDescriptor<? super Wheel>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, StockDescriptor, DiameterDescriptor, TyreTypeDescriptor, BrakeTypeDescriptor);
		
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
		
		Filter<ICost> costFilter = CommonFilters.getCostFilter();
		
		return Arrays.asList(brakesFilter, tyresFilter, costFilter);
	}
	
	protected Boolean deleteComponent(Wheel wheelToDelete) throws SQLIntegrityConstraintViolationException {
		
		return WheelOperations.deleteWheel(wheelToDelete);
	}

	@Override
	protected Wheel editObject(Wheel currentObject) {
		return WheelEditor.openEditor(this, currentObject);
	}
}
