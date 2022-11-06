package View;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.FrameOperations;
import Domain.BicycleComponent;
import Domain.Frameset;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;


public class FramesetPicker extends AbstractPicker<Frameset> {

	public FramesetPicker(JFrame parent) {
		super(parent);
		
	}
	
    public static Frameset chooseFrameset(JFrame parent) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent);
    	
    	PickerWindow.setTitle("Frameset Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Frameset> getAvailableObjects() {
		return FrameOperations.getAllFrames();
	}

	@Override
	protected Collection<PropertyDescriptor<Frameset>> getPropertyDescriptors() {
		PropertyDescriptor<Frameset> BrandNameDescriptor = new PropertyDescriptor<Frameset>("Brand Name", frame -> frame.BrandName());
		PropertyDescriptor<Frameset> SerialNumberDescriptor = new PropertyDescriptor<Frameset>("Serial Number", frame -> Integer.toString(frame.SerialNumber()));
		PropertyDescriptor<Frameset> CostDescriptor = new PropertyDescriptor<Frameset>("Cost", frame -> Double.toString(frame.Cost()));
		PropertyDescriptor<Frameset> ShocksDescriptor = new PropertyDescriptor<Frameset>("Shocks", frame -> Boolean.toString(frame.get_shocks()));
		PropertyDescriptor<Frameset> SizeDescriptor = new PropertyDescriptor<Frameset>("Size", frame -> Double.toString(frame.get_size()));
		
		
		Collection<PropertyDescriptor<Frameset>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, ShocksDescriptor, SizeDescriptor);
		
		return descriptors;
	}
	
	@Override
	protected Collection<Filter<? super Frameset>> getFilters() {
		FilterValue<Frameset> hasShocks = new FilterValue<Frameset>("Shocks", frame -> frame.get_shocks() == true);
		FilterValue<Frameset> noShocks = new FilterValue<Frameset>("No shocks", frame -> frame.get_shocks() == false);
		Filter<Frameset> shocksFilter = new Filter<Frameset>("Shocks",  Arrays.asList(hasShocks, noShocks));
		
		FilterValue<Frameset> smallerThan100 = new FilterValue<Frameset>("< 100", frame -> frame.get_size() < 100);
		FilterValue<Frameset> from100to500 = new FilterValue<Frameset>("100 - 500", frame -> frame.get_size() < 100 && frame.get_size() < 500);
		FilterValue<Frameset> greaterThan500 = new FilterValue<Frameset>("> 500", frame -> frame.get_size() > 500);
		Filter<Frameset> sizeFilter = new Filter<Frameset>("Size",  Arrays.asList(smallerThan100, from100to500, greaterThan500));
		
		Filter<BicycleComponent> costFilter = BicycleComponentFilters.getCostFilter();
		
		
		return Arrays.asList(shocksFilter, sizeFilter, costFilter);
	}

}
