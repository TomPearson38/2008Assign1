package View.CreatorsAndEditors;

import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Database.FrameOperations;
import Database.GearsetOperations;
import Domain.Frameset;
import Domain.Gearset;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.BooleanGridRow;
import View.AbstractCreator.CollectionGridRow;
import View.AbstractCreator.GridRow;
import View.AbstractCreator.IGridRow;
import View.AbstractCreator.IntegerCreatorInputField;
import View.AbstractCreator.IntegerGridRow;
import View.AbstractCreator.StringCreatorInputField;
import View.AbstractCreator.StringGridRow;
import View.UserControls.JDoubleField;
import View.UserControls.JIntegerField;
import View.AbstractCreator.CreatorInputField;
import View.AbstractCreator.DoubleCreatorInputField;
import View.AbstractCreator.DoubleGridRow;
import View.AbstractCreator.EnumCreatorInputField;
import View.AbstractCreator.EnumGridRow;

public abstract class AbstractFramesetCreator extends AbstractCreator<Frameset> {
	IGridRow<Integer, JIntegerField> serialNumberRow;
	IGridRow<String, JTextField> brandNameRow;
	IGridRow<Double, JDoubleField> costRow;
	IGridRow<Double, JDoubleField> sizeRow;
	IGridRow<Boolean, JCheckBox> shocksRow;
	IGridRow<Gearset, JComboBox<Gearset>> gearsRow;
	IGridRow<Integer, JIntegerField> stockRow;
	
	public AbstractFramesetCreator(Frame owner) {
		super(owner);
	}
	
	public static Frameset addHFrameset(Frame owner) {
		FramesetCreator myWindow = new FramesetCreator(owner);
		return myWindow.showCreatorDialog();
	}


	@Override
	protected Collection<IGridRow<?, ?>> getGridValues() {

		serialNumberRow = new IntegerGridRow("Serial Number");
		
		brandNameRow = new StringGridRow("Brand Name");
		
		costRow = new DoubleGridRow("Cost");
		
		sizeRow = new DoubleGridRow("Size");
		
		shocksRow = new BooleanGridRow("Shocks");
		
		stockRow = new IntegerGridRow("Available Stock");
		
		Collection<Gearset> gears = GearsetOperations.getAllGears();
		gearsRow = new CollectionGridRow<Gearset>("Gears", gears.toArray(new Gearset[gears.size()]));
		
		//gearsRow = new EnumGridRow<Gearset>("Gears", HandlebarStyles.values());
		
		
		return Arrays.asList(serialNumberRow, brandNameRow, costRow, sizeRow, shocksRow, gearsRow, stockRow);
	}
	
	protected abstract Frameset sendValueToDatabase();
	

}
