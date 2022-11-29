package View;

import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Database.GearsetOperations;
import Domain.Gearset;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.GridRow;
import View.AbstractCreator.IGridRow;
import View.AbstractCreator.IntegerCreatorInputField;
import View.AbstractCreator.IntegerGridRow;
import View.AbstractCreator.StringCreatorInputField;
import View.AbstractCreator.StringGridRow;
import View.AbstractCreator.CreatorInputField;
import View.AbstractCreator.DoubleCreatorInputField;
import View.AbstractCreator.DoubleGridRow;
import View.AbstractCreator.EnumCreatorInputField;
import View.AbstractCreator.EnumGridRow;

public class GearsetCreator extends AbstractCreator<Gearset> {
	IGridRow<String, JTextField> nameRow;
	
	public GearsetCreator(Frame owner) {
		super(owner);
	}
	
	public static Gearset addGearset(Frame owner) {
		GearsetCreator myWindow = new GearsetCreator(owner);
		return myWindow.showCreatorDialog();
	}


	@Override
	protected Collection<IGridRow> getGridValues() {

		nameRow = new StringGridRow("Name");
		
		
		return Arrays.asList(nameRow);
	}
	
	protected Gearset sendValueToDatabase() {
		String name = nameRow.getFieldValue();
		
		Gearset newGearset = GearsetOperations.createGearset(name);
		return newGearset;
	}
	

}
