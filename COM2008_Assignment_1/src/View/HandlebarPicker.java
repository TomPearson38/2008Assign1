package View;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.HandlebarOperations;
import Domain.BicycleComponent;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;

public class HandlebarPicker extends AbstractPicker<Handlebar>{

	public HandlebarPicker(JFrame parent) {
		super(parent);
		
	}
	
    public static Handlebar chooseHandlebar(JFrame parent) {
    	HandlebarPicker PickerWindow = new HandlebarPicker(parent);
    	
    	PickerWindow.setTitle("Wheel Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Handlebar> getAvailableObjects() {
		return HandlebarOperations.getAllHandlebars();
	}

	@Override
	protected Collection<PropertyDescriptor<Handlebar>> getPropertyDescriptors() {
		PropertyDescriptor<Handlebar> BrandNameDescriptor = new PropertyDescriptor<Handlebar>("Brand Name", handlebar -> handlebar.BrandName());
		PropertyDescriptor<Handlebar> SerialNumberDescriptor = new PropertyDescriptor<Handlebar>("Serial Number", handlebar -> Integer.toString(handlebar.SerialNumber()));
		PropertyDescriptor<Handlebar> CostDescriptor = new PropertyDescriptor<Handlebar>("Cost", handlebar -> Double.toString(handlebar.Cost()));
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

}
