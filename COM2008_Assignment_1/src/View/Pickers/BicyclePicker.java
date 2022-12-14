package View.Pickers;

import java.awt.Dialog;
import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import javax.swing.JFrame;

import Database.BicycleOperations;
import Domain.Bicycle;
import Domain.BrakeType;
import Domain.ICost;
import Domain.TyreType;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.CommonDescriptors;
import View.AbstractPicker.CommonFilters;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;
import View.BicycleDesigner.BicycleDesignerDialog;

/**
 * Picker panel to chose the individual bike parts
 * @author Alex Dobson
 *
 */
public class BicyclePicker extends AbstractPicker<Bicycle>{

	/**
	 * Customer bike picker view
	 * @param parent Parent frame
	 * @param managementMode If edit and delete fields are visible
	 */
	public BicyclePicker(Window parent, boolean isStaffMode) {
		super(parent, isStaffMode);
	}


	
	/**
	 * Standard wheels picker view for customers
	 * @param parent Parent frame
	 * @return chosen bike
	 */
	public static Bicycle chooseWheels(Window parent) {
		return BicyclePicker.chooseBicycle(parent, false);
	}
	
	/**
	 * Creates picker window
	 * @param parent Parent frame
	 * @param managementMode If edit fields are available
	 * @return chosen bike
	 */
    public static Bicycle chooseBicycle(Window parent, boolean managementMode) {
    	BicyclePicker PickerWindow = new BicyclePicker(parent, managementMode);
    	
    	return configureAndShowPicker(PickerWindow);  	
    }
    private static Bicycle configureAndShowPicker(BicyclePicker pickerWindow) {
    	pickerWindow.setTitle("Bicycle Picker");
    	
    	return pickerWindow.showDialog(); 
    }

	@Override
	protected Collection<Bicycle> getAvailableObjects() {
		return BicycleOperations.getAllBikes();
	}

	@Override
	protected Collection<PropertyDescriptor<? super Bicycle>> getPropertyDescriptors() {
		PropertyDescriptor<Bicycle> NameDescriptor = new PropertyDescriptor<Bicycle>("Name", Bicycle::getCustomerGivenName);
		PropertyDescriptor<Bicycle> BrandNameDescriptor = new PropertyDescriptor<Bicycle>("Brand", Bicycle::getBrandName);
		PropertyDescriptor<ICost> CostDescriptor = CommonDescriptors.getCostDescriptor();
		PropertyDescriptor<Bicycle> FrameBrandDescriptor = new PropertyDescriptor<Bicycle>("Frame", bike -> bike.get_frame().getBrandName());
		PropertyDescriptor<Bicycle> ShocksDescriptor = new PropertyDescriptor<Bicycle>("Shocks", bike -> Boolean.toString(bike.get_frame().get_shocks()));
		PropertyDescriptor<Bicycle> HandlebarStyle = new PropertyDescriptor<Bicycle>("Handlebar Style", bike -> bike.get_handlebar().get_style().toString());
		PropertyDescriptor<Bicycle> TyreDescriptor = new PropertyDescriptor<Bicycle>("Tyres", bike -> bike.get_Wheels().get_tyre().toString());
		PropertyDescriptor<Bicycle> BrakeDescriptor = new PropertyDescriptor<Bicycle>("Tyres", bike -> bike.get_Wheels().get_brakes().toString());
	
		Collection<PropertyDescriptor<? super Bicycle>> descriptors = Arrays.asList(NameDescriptor, BrandNameDescriptor, CostDescriptor, FrameBrandDescriptor, ShocksDescriptor, HandlebarStyle, TyreDescriptor, BrakeDescriptor);
		
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
		
		Filter<ICost> costFilter = CommonFilters.getCostFilter();
		
		return Arrays.asList(brakesFilter, tyresFilter, costFilter);
	}


	@Override
	protected Boolean deleteComponent(Bicycle Object) throws SQLIntegrityConstraintViolationException {
		return BicycleOperations.deleteBicycle(Object);
	}

	@Override
	protected Bicycle editObject(Bicycle currentObject) {
		return BicycleDesignerDialog.editBicycle(this, currentObject);
	}
	

}
