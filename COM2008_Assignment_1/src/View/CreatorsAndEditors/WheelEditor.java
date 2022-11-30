package View.CreatorsAndEditors;

import java.awt.Frame;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;

public class WheelEditor extends AbstractWheelCreator {
	int wheelID;

	public WheelEditor(Frame owner, Wheel wheelToEdit) {
		// TODO Auto-generated constructor stub#
		super(owner);
		wheelID = wheelToEdit.get_id();
		brandNameRow.setFieldValue(wheelToEdit.getBrandName());
		serialNumberRow.setFieldValue(wheelToEdit.getSerialNumber());
		costRow.setFieldValue(wheelToEdit.getCost());
		diameterRow.setFieldValue(wheelToEdit.get_diameter());
		tyreTypeRow.setFieldValue(wheelToEdit.get_tyre());
		brakeTypeRow.setFieldValue(wheelToEdit.get_brakes());
		stockRow.setFieldValue(wheelToEdit.getStockNum());
	}

	protected Wheel sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double diameter = diameterRow.getFieldValue();
		TyreType tyreType = tyreTypeRow.getFieldValue();
		BrakeType brakeType = brakeTypeRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Wheel newWheel = new Wheel(this.wheelID, serialNumber, brandName, cost, diameter, tyreType, brakeType, availableStock);
		return newWheel;
	}
}