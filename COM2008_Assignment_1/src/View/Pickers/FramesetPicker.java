package View.Pickers;

import java.awt.Dialog;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Database.FrameOperations;
import Domain.BicycleComponent;
import Domain.Frameset;
import Domain.IBicycleComponent;
import Domain.ICost;
import View.AbstractPicker.AbstractPicker;
import View.AbstractPicker.CommonDescriptors;
import View.AbstractPicker.CommonFilters;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;
import View.AbstractPicker.PropertyDescriptor;
import View.CreatorsAndEditors.FramesetEditor;

/**
 * Used for the customer to be able to pick a frame from stock
 * @author Alex Dobson
 *
 */
public class FramesetPicker extends AbstractPicker<Frameset> {

	/**
	 * Initialises the frameset picker
	 * @param parent Parent form
	 * @param isStaffMode if the editor fields are available
	 */
	public FramesetPicker(JFrame parent, Boolean isStaffMode) {
		super(parent, isStaffMode);
	}
	public FramesetPicker(Dialog owner, Boolean isStaffMode) {
		super(owner, isStaffMode);
	}
	
	public static Frameset chooseFrameset(JDialog parent, Boolean isStaffMode) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent, isStaffMode);

    	return configureAndShowPicker(PickerWindow);  	
    }
	
	/**
	 * Used to initialise the choseFrame when the staff mode is not provided
	 * @param parent
	 * @return
	 */
	public static Frameset chooseFrameset(JFrame parent) {
		return FramesetPicker.chooseFrameset(parent, false);
	}
	
	/**
	 * Initialise when staff mode boolean is provided
	 * @param parent
	 * @param isStaffMode 
	 * @return
	 */
    public static Frameset chooseFrameset(JFrame parent, Boolean isStaffMode) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent, isStaffMode);

    	return configureAndShowPicker(PickerWindow);  	
    }
    private static Frameset configureAndShowPicker(FramesetPicker pickerWindow) {
    	pickerWindow.setTitle("Frameset Picker");
    	
    	return pickerWindow.showDialog(); 
    }

	@Override
	protected Collection<Frameset> getAvailableObjects() {
		return FrameOperations.getAllFrames();
	}

	@Override
	protected Collection<PropertyDescriptor<? super Frameset>> getPropertyDescriptors() {
		PropertyDescriptor<IBicycleComponent> BrandNameDescriptor = CommonDescriptors.getBrandNameDescriptor();
		PropertyDescriptor<IBicycleComponent> SerialNumberDescriptor = CommonDescriptors.getSerialNumberDescriptor();
		PropertyDescriptor<ICost> CostDescriptor = CommonDescriptors.getCostDescriptor();
		PropertyDescriptor<IBicycleComponent> StockDescriptor = CommonDescriptors.getStockDescriptor();
		PropertyDescriptor<Frameset> ShocksDescriptor = new PropertyDescriptor<Frameset>("Shocks", frame -> Boolean.toString(frame.get_shocks()));
		PropertyDescriptor<Frameset> SizeDescriptor = new PropertyDescriptor<Frameset>("Size", frame -> Double.toString(frame.get_size()));
		
		
		Collection<PropertyDescriptor<? super Frameset>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, StockDescriptor, ShocksDescriptor, SizeDescriptor);
		
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
	
	
	/**
	 * Returns if the component deletion has been successful
	 */
	protected Boolean deleteComponent(Frameset frameSetToDelete) throws SQLIntegrityConstraintViolationException  {
		return FrameOperations.deleteFrameset(frameSetToDelete);
	}
	@Override
	protected Frameset editObject(Frameset currentObject) {
		return FramesetEditor.openEditor(this, currentObject);
		
	}
}
