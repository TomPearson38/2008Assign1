package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Database.WheelOperations;
import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;

public class WheelEditor extends AbstractWheelCreator {
	int wheelID;

	public WheelEditor(Window owner, Wheel wheelToEdit) {
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
		try {
			WheelOperations.updateWheel(newWheel);
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "Error editing component - Frameset/Serial number combination already in use", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return newWheel;
	}
	
	public static Wheel openEditor(Window owner, Wheel object) {
		WheelEditor newEditorWindow = new WheelEditor(owner, object);
		return newEditorWindow.showCreatorDialog();
	}
}