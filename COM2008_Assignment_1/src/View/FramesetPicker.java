package View;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.FrameOperations;
import Domain.BicycleComponent;
import Domain.Frameset;
import Domain.ICost;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.CommonDescriptors;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;


public class FramesetPicker extends AbstractPicker<Frameset> {

	public FramesetPicker(JFrame parent, Boolean isStaffMode) {
		super(parent, isStaffMode);
		
	}
	public static Frameset chooseFrameset(JFrame parent) {
		return FramesetPicker.chooseFrameset(parent, false);
	}
    public static Frameset chooseFrameset(JFrame parent, Boolean isStaffMode) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent, isStaffMode);
    	
    	PickerWindow.setTitle("Frameset Picker");
    	
    	return PickerWindow.showDialog();  	
    }

	@Override
	protected Collection<Frameset> getAvailableObjects() {
		return FrameOperations.getAllFrames();
	}

	@Override
	protected Collection<PropertyDescriptor<? super Frameset>> getPropertyDescriptors() {
		PropertyDescriptor<Frameset> BrandNameDescriptor = new PropertyDescriptor<Frameset>("Brand Name", frame -> frame.getBrandName());
		PropertyDescriptor<Frameset> SerialNumberDescriptor = new PropertyDescriptor<Frameset>("Serial Number", frame -> Integer.toString(frame.getSerialNumber()));
		PropertyDescriptor<ICost> CostDescriptor = CommonDescriptors.getCostDescriptor();
		PropertyDescriptor<Frameset> ShocksDescriptor = new PropertyDescriptor<Frameset>("Shocks", frame -> Boolean.toString(frame.get_shocks()));
		PropertyDescriptor<Frameset> SizeDescriptor = new PropertyDescriptor<Frameset>("Size", frame -> Double.toString(frame.get_size()));
		
		
		Collection<PropertyDescriptor<? super Frameset>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, ShocksDescriptor, SizeDescriptor);
		
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
		
		Filter<ICost> costFilter = CommonFilters.getCostFilter();
		
		Collection<String> brands = FrameOperations.getBrandsInFramesTable();
		Filter<BicycleComponent> brandFilter = CommonFilters.getBrandFilter(brands);
				
		return Arrays.asList(shocksFilter, sizeFilter, costFilter, brandFilter);
	}
	
	protected Boolean updateComponent(Frameset frameSetData) {
		return FrameOperations.updateFrameset(frameSetData);
	}
	
	protected Boolean deleteComponent(Frameset frameSetToDelete) throws SQLIntegrityConstraintViolationException  {
		return FrameOperations.deleteFrameset(frameSetToDelete);
	}
}
