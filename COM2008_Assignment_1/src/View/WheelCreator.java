package View;

import java.awt.Frame;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;

public class WheelCreator extends AbstractWheelCreator {

	public WheelCreator(Frame owner) {
		// TODO Auto-generated constructor stub#
		super(owner);
	}

	protected Wheel sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double diameter = diameterRow.getFieldValue();
		TyreType tyreType = tyreTypeRow.getFieldValue();
		BrakeType brakeType = brakeTypeRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Wheel newWheel = WheelOperations.createWheel(brandName, serialNumber, cost, diameter, tyreType, brakeType, availableStock);
		return newWheel;
	}
}
