package View.Pickers;

import java.awt.Dialog;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Database.EnumMappingException;
import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import Domain.IBicycleComponent;
import Domain.ICost;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.CommonDescriptors;
import View.AbstractPicker.CommonFilters;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;
import View.CreatorsAndEditors.HandlebarEditor;

public class HandlebarPicker extends AbstractPicker<Handlebar>{


	public HandlebarPicker(JFrame parent, boolean isStaffMode) {
		super(parent, isStaffMode);
	}
	
	public HandlebarPicker(Dialog owner, Boolean isStaffMode) {
		super(owner, isStaffMode);
	}
	
	public static Handlebar chooseHandlebar(JDialog parent) { return HandlebarPicker.chooseHandlebar(parent, false); }
    public static Handlebar chooseHandlebar(JDialog parent, boolean managementMode) {
    	HandlebarPicker pickerWindow = new HandlebarPicker(parent, managementMode);
    	
    	return configureAndShowPicker(pickerWindow);  	
    }
    
	public static Handlebar chooseHandlebar(JFrame parent) { return HandlebarPicker.chooseHandlebar(parent, false); }
    public static Handlebar chooseHandlebar(JFrame parent, boolean managementMode) {
    	HandlebarPicker pickerWindow = new HandlebarPicker(parent, managementMode);
    	
    	return configureAndShowPicker(pickerWindow);  	
    }
    private static Handlebar configureAndShowPicker(HandlebarPicker pickerWindow) {
    	pickerWindow.setTitle("Handlebar Picker");
    	
    	return pickerWindow.showDialog(); 
    }

	@Override
	protected Collection<Handlebar> getAvailableObjects() {
		Collection<Handlebar> allHandlebars = null;
		try {
			 allHandlebars = HandlebarOperations.getAllHandlebars();
		} catch (EnumMappingException ex) {
			ex.printStackTrace();
		}
		return allHandlebars;
	}

	@Override
	protected Collection<PropertyDescriptor<? super Handlebar>> getPropertyDescriptors() {
		PropertyDescriptor<IBicycleComponent> BrandNameDescriptor = CommonDescriptors.getBrandNameDescriptor();
		PropertyDescriptor<IBicycleComponent> SerialNumberDescriptor = CommonDescriptors.getSerialNumberDescriptor();
		PropertyDescriptor<ICost> CostDescriptor = CommonDescriptors.getCostDescriptor();
		PropertyDescriptor<IBicycleComponent> StockDescriptor = CommonDescriptors.getStockDescriptor();
		PropertyDescriptor<Handlebar> StylesDescriptor = new PropertyDescriptor<Handlebar>("Style", handlebar -> (handlebar.get_style().toString()));
		Collection<PropertyDescriptor<? super Handlebar>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, StockDescriptor, StylesDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Handlebar>> getFilters() {
		FilterValue<Handlebar> hasHighStyle = new FilterValue<Handlebar>("High", handlebar -> handlebar.get_style() == HandlebarStyles.HIGH);
		FilterValue<Handlebar> hasDroppedStyle = new FilterValue<Handlebar>("Dropped", handlebar -> handlebar.get_style() == HandlebarStyles.DROPPED);
		FilterValue<Handlebar> hasStraightStyle = new FilterValue<Handlebar>("Straight", handlebar -> handlebar.get_style() == HandlebarStyles.STRAIGHT);
		Filter<Handlebar> stylesFilter = new Filter<Handlebar>("Styles",  Arrays.asList(hasHighStyle, hasDroppedStyle, hasStraightStyle));
		
		Filter<ICost> costFilter = CommonFilters.getCostFilter();
		
		return Arrays.asList(stylesFilter, costFilter);
	}
	
	protected Boolean updateComponent(Handlebar handlebarData) {
		return HandlebarOperations.updateHandlebar(handlebarData);
	}
	
	protected Boolean deleteComponent(Handlebar handlebarToDelete) throws SQLIntegrityConstraintViolationException  {
		return HandlebarOperations.deleteHandlebar(handlebarToDelete);
	}

	@Override
	protected Handlebar editObject(Handlebar currentObject) {
		return HandlebarEditor.openEditor(this, currentObject);
	}
	
}
