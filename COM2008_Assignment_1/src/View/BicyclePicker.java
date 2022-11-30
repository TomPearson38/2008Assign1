package View;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import javax.swing.JFrame;

import Database.BicycleOperations;
import Database.HandlebarOperations;
import Database.WheelOperations;
import Domain.Bicycle;
import Domain.BicycleComponent;
import Domain.BrakeType;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.TyreType;
import Domain.Wheel;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;

public class BicyclePicker extends AbstractPicker<Bicycle>{

	public BicyclePicker(JFrame parent, boolean managementMode) {
		super(parent, managementMode);
	}
	
	public static Bicycle chooseWheels(JFrame parent) {
		return BicyclePicker.chooseWheels(parent, false);
	}
	
    public static Bicycle chooseWheels(JFrame parent, boolean managementMode) {
    	BicyclePicker PickerWindow = new BicyclePicker(parent, managementMode);
    	
    	PickerWindow.setTitle("Bicycle Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Bicycle> getAvailableObjects() {
		return BicycleOperations.getAllBikes();
	}

	@Override
	protected Collection<PropertyDescriptor<Bicycle>> getPropertyDescriptors() {
		PropertyDescriptor<Bicycle> NameDescriptor = new PropertyDescriptor<Bicycle>("Name", Bicycle::getFrameName);
		PropertyDescriptor<Bicycle> BrandNameDescriptor = new PropertyDescriptor<Bicycle>("Brand", Bicycle::bicycleBrand);
		PropertyDescriptor<Bicycle> CostDescriptor = new PropertyDescriptor<Bicycle>("Brand", bike -> Double.toString(bike.getCost()));
		PropertyDescriptor<Bicycle> FrameBrandDescriptor = new PropertyDescriptor<Bicycle>("Frame", bike -> bike.get_frame().BrandName());
		PropertyDescriptor<Bicycle> ShocksDescriptor = new PropertyDescriptor<Bicycle>("Shocks", bike -> Boolean.toString(bike.get_frame().get_shocks()));
		PropertyDescriptor<Bicycle> HandlebarStyle = new PropertyDescriptor<Bicycle>("Handlebar Style", bike -> bike.get_handlebar().get_style().toString());
		PropertyDescriptor<Bicycle> TyreDescriptor = new PropertyDescriptor<Bicycle>("Tyres", bike -> bike.get_Wheels().get_tyre().toString());
		PropertyDescriptor<Bicycle> BrakeDescriptor = new PropertyDescriptor<Bicycle>("Tyres", bike -> bike.get_Wheels().get_brakes().toString());
	
		Collection<PropertyDescriptor<Bicycle>> descriptors = Arrays.asList(NameDescriptor, BrandNameDescriptor, CostDescriptor, FrameBrandDescriptor, ShocksDescriptor, HandlebarStyle, TyreDescriptor, BrakeDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Bicycle>> getFilters() {
		FilterValue<Bicycle> hasRimBrakes = new FilterValue<Bicycle>("Rim Breaks", bike -> bike.get_Wheels().get_brakes() == BrakeType.RIM);
		FilterValue<Bicycle> hasDiskBrakes = new FilterValue<Bicycle>("Disk Breaks", bike -> bike.get_Wheels().get_brakes() == BrakeType.RIM);
		Filter<Bicycle> brakesFilter = new Filter<Bicycle>("Brakes",  Arrays.asList(hasRimBrakes, hasDiskBrakes));
		
		FilterValue<Bicycle> hasRoadTyres = new FilterValue<Bicycle>("Road Tyres", bike -> bike.get_Wheels().get_tyre() == TyreType.ROAD);
		FilterValue<Bicycle> hasMountainTyres = new FilterValue<Bicycle>("Mountain Tyres", bike -> bike.get_Wheels().get_tyre() == TyreType.MOUNTAIN);
		FilterValue<Bicycle> hasHybridTyres = new FilterValue<Bicycle>("Hybrid Tyres", bike -> bike.get_Wheels().get_tyre() == TyreType.HYBRID);
		Filter<Bicycle> tyresFilter = new Filter<Bicycle>("Tyres",  Arrays.asList(hasRoadTyres, hasMountainTyres, hasHybridTyres));
		
//		Filter<BicycleComponent> costFilter = BicycleComponentFilters.getCostFilter();
		
		return Arrays.asList(brakesFilter, tyresFilter);
	}

	protected Boolean updateComponent(Wheel wheelData) {
		return WheelOperations.updateWheel(wheelData);
	}
	
	protected Boolean deleteComponent(Wheel wheelToDelete) throws SQLIntegrityConstraintViolationException {
		
		return WheelOperations.deleteWheel(wheelToDelete);
	}

	@Override
	protected Boolean updateComponent(Bicycle Object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Boolean deleteComponent(Bicycle Object) throws SQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		return null;
	}
}
