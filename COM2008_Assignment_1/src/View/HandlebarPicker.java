package View;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.EnumMappingException;
import Database.FrameOperations;
import Database.HandlebarOperations;
import Domain.BicycleComponent;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;

public class HandlebarPicker extends AbstractPicker<Handlebar>{

	public HandlebarPicker(JFrame parent, boolean managementMode) {
		super(parent, managementMode);
		
	}
	
	public static Handlebar chooseHandlebar(JFrame parent) {
		return HandlebarPicker.chooseHandlebar(parent, false);
	}
	
    public static Handlebar chooseHandlebar(JFrame parent, boolean managementMode) {
    	HandlebarPicker PickerWindow = new HandlebarPicker(parent, managementMode);
    	
    	PickerWindow.setTitle("Handlebar Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Handlebar> getAvailableObjects() {
		Collection<Handlebar> allHandlebars = null;
		try {
			 allHandlebars = HandlebarOperations.getAllHandlebars();
		} catch (EnumMappingException ex) {
			
		} finally {
			return allHandlebars;
		}
	}

	@Override
	protected Collection<PropertyDescriptor<Handlebar>> getPropertyDescriptors() {
		PropertyDescriptor<Handlebar> BrandNameDescriptor = new PropertyDescriptor<Handlebar>("Brand Name", handlebar -> handlebar.getBrandName());
		PropertyDescriptor<Handlebar> SerialNumberDescriptor = new PropertyDescriptor<Handlebar>("Serial Number", handlebar -> Integer.toString(handlebar.getSerialNumber()));
		PropertyDescriptor<Handlebar> CostDescriptor = new PropertyDescriptor<Handlebar>("Cost", handlebar -> Double.toString(handlebar.getCost()));
		PropertyDescriptor<Handlebar> StylesDescriptor = new PropertyDescriptor<Handlebar>("Style", handlebar -> (handlebar.get_style().toString()));
		Collection<PropertyDescriptor<Handlebar>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, StylesDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Handlebar>> getFilters() {
		FilterValue<Handlebar> hasHighStyle = new FilterValue<Handlebar>("High", handlebar -> handlebar.get_style() == HandlebarStyles.HIGH);
		FilterValue<Handlebar> hasDroppedStyle = new FilterValue<Handlebar>("Dropped", handlebar -> handlebar.get_style() == HandlebarStyles.DROPPED);
		FilterValue<Handlebar> hasStraightStyle = new FilterValue<Handlebar>("Straight", handlebar -> handlebar.get_style() == HandlebarStyles.STRAIGHT);
		Filter<Handlebar> stylesFilter = new Filter<Handlebar>("Styles",  Arrays.asList(hasHighStyle, hasDroppedStyle, hasStraightStyle));
		
		Filter<BicycleComponent> costFilter = BicycleComponentFilters.getCostFilter();
		
		return Arrays.asList(stylesFilter, costFilter);
	}
	
	protected Boolean updateComponent(Handlebar handlebarData) {
		return HandlebarOperations.updateHandlebar(handlebarData);
	}
	
	protected Boolean deleteComponent(Handlebar handlebarToDelete) throws SQLIntegrityConstraintViolationException  {
		return HandlebarOperations.deleteHandlebar(handlebarToDelete);
	}
	
}
