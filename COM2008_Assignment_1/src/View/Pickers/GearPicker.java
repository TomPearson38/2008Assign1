package View.Pickers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.GearsetOperations;
import Database.HandlebarOperations;
import Domain.Gearset;
import Domain.Handlebar;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.PropertyDescriptor;

/**
 * Used to edit the gears
 * @author Alex Dobson
 *
 */
public class GearPicker extends AbstractPicker<Gearset>{

	/**
	 * Allows the user staff to edit gears
	 * @param parent Parent form
	 * @param managementMode If the edit buttons are present
	 */
	public GearPicker(JFrame parent, boolean managementMode) {
		super(parent, managementMode);
		
	}
	
	public static Gearset chooseGearset(JFrame parent) {
		return GearPicker.chooseGearset(parent, false);
	}
	
    public static Gearset chooseGearset(JFrame parent, boolean managementMode) {
    	GearPicker PickerWindow = new GearPicker(parent, managementMode);
    	
    	PickerWindow.setTitle("Handlebar Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Gearset> getAvailableObjects() {
		Collection<Gearset> allGears = null;
	    allGears = GearsetOperations.getAllGears();
		return allGears;
	}

	@Override
	protected Collection<PropertyDescriptor<? super Gearset>> getPropertyDescriptors() {
		PropertyDescriptor<Gearset> BrandNameDescriptor = new PropertyDescriptor<Gearset>("Name", gear -> gear.get_name());
		
		Collection<PropertyDescriptor<? super Gearset>> descriptors = Arrays.asList(BrandNameDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Gearset>> getFilters() {

		return new ArrayList<Filter<? super Gearset>>();
	}
	
	protected Boolean updateComponent(Handlebar handlebarData) {
		return HandlebarOperations.updateHandlebar(handlebarData);
	}
	
	protected Boolean deleteComponent(Handlebar handlebarToDelete) throws SQLIntegrityConstraintViolationException  {
		return HandlebarOperations.deleteHandlebar(handlebarToDelete);
	}

    @Override
    protected Boolean updateComponent(Gearset Object) {
        return GearsetOperations.updateGearset(Object);
    }

    @Override
    protected Boolean deleteComponent(Gearset Object) throws SQLIntegrityConstraintViolationException {
        return GearsetOperations.deleteGearset(Object);
    }
	
}
