package View.Pickers;

import java.awt.Dialog;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Database.GearsetOperations;
import Domain.Gearset;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.PropertyDescriptor;
import View.CreatorsAndEditors.GearsetEditor;

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
	public GearPicker(JFrame parent, boolean isStaffMode) {
		super(parent, isStaffMode);
	}
	public GearPicker(Dialog owner, Boolean isStaffMode) {
		super(owner, isStaffMode);
	}

	public static Gearset chooseGearset(JDialog parent) { return GearPicker.chooseGearset(parent, false); }
    public static Gearset chooseGearset(JDialog parent, boolean managementMode) {
    	GearPicker PickerWindow = new GearPicker(parent, managementMode);
    	
    	return configureAndShowPicker(PickerWindow);  	
    }
    
	public static Gearset chooseGearset(JFrame parent) { return GearPicker.chooseGearset(parent, false); }
    public static Gearset chooseGearset(JFrame parent, boolean managementMode) {
    	GearPicker PickerWindow = new GearPicker(parent, managementMode);
    	
    	return configureAndShowPicker(PickerWindow);  	
    }
    private static Gearset configureAndShowPicker(GearPicker pickerWindow) {
    	pickerWindow.setTitle("Gear Picker");
    	
    	return pickerWindow.showDialog(); 
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
	

    @Override
    protected Boolean deleteComponent(Gearset Object) throws SQLIntegrityConstraintViolationException {
        return GearsetOperations.deleteGearset(Object);
    }

	@Override
	protected Gearset editObject(Gearset currentObject) {
		return GearsetEditor.openEditor(this, currentObject);
	}
	
}
