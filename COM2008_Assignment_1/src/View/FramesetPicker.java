package View;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import Database.FrameOperations;

import java.util.function.Function;

import Domain.Frameset;

public class FramesetPicker extends AbstractPicker<Frameset> {

	public FramesetPicker(JFrame parent) {
		super(parent);
		
	}
	
    public static Frameset chooseFrameset(JFrame parent) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent);
    	
    	PickerWindow.setTitle("Frameset Picker");
    	
    	return PickerWindow.showFramesetDialog();  	
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
		PropertyDescriptor<Frameset> SizeDescriptor = new PropertyDescriptor<Frameset>("Size", frame -> Boolean.toString(frame.get_shocks()));
		
		
		Collection<PropertyDescriptor<Frameset>> descriptors = Arrays.asList(BrandNameDescriptor, SerialNumberDescriptor, CostDescriptor, ShocksDescriptor, SizeDescriptor);
		
		return descriptors;
	}

}
