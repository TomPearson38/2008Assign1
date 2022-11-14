package View;

import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Database.HandlebarOperations;
import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;
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

public class WheelCreator extends AbstractCreator<Wheel> {
	IGridRow<Integer, JIntegerField> serialNumberRow;
	IGridRow<String, JTextField> brandNameRow;
	IGridRow<Double, JDoubleField> costRow;
	IGridRow<Double, JDoubleField> diameterRow;
	IGridRow<TyreType, JComboBox<TyreType>> tyreTypeRow;
	IGridRow<BrakeType, JComboBox<BrakeType>> brakeTypeRow;
	
	public WheelCreator(Frame owner) {
		super(owner);
	}
	
	public static Wheel addWheel(Frame owner) {
		WheelCreator myWindow = new WheelCreator(owner);
		return myWindow.showCreatorDialog();
	}


	@Override
	protected Collection<IGridRow> getGridValues() {

		serialNumberRow = new IntegerGridRow("Serial Number");
		
		brandNameRow = new StringGridRow("Brand Name");
		
		costRow = new DoubleGridRow("Cost");
		
		diameterRow = new DoubleGridRow("Diameter");
		
		tyreTypeRow = new EnumGridRow<TyreType>("Tyre Type", TyreType.values());
		
		brakeTypeRow = new EnumGridRow<BrakeType>("Brake Type", BrakeType.values());
		
		
		
		return Arrays.asList(serialNumberRow, brandNameRow, costRow, diameterRow, tyreTypeRow, brakeTypeRow);
	}
	
	protected Wheel sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double diameter = diameterRow.getFieldValue();
		TyreType tyreType = tyreTypeRow.getFieldValue();
		BrakeType brakeType = brakeTypeRow.getFieldValue();
		
		Wheel newWheel = WheelOperations.createWheel(brandName, serialNumber, cost, diameter, tyreType, brakeType);
		return newWheel;
	}
	

}
