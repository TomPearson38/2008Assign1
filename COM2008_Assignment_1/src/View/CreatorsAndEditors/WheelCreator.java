package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;

public class WheelCreator extends AbstractWheelCreator {

	public WheelCreator(Window owner) {
		super(owner);
	}
	
	public static Wheel addWheel(Window owner) {
		WheelCreator myWindow = new WheelCreator(owner);
		return myWindow.showCreatorDialog();
	}

	protected Wheel sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double diameter = diameterRow.getFieldValue();
		TyreType tyreType = tyreTypeRow.getFieldValue();
		BrakeType brakeType = brakeTypeRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Wheel newWheel = null;
		try {
			newWheel = WheelOperations.createWheel(brandName, serialNumber, cost, diameter, tyreType, brakeType, availableStock);
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "Error adding component - Frameset/Serial number combination already in use", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return newWheel;
	}
}
