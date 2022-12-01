package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Database.FrameOperations;
import Domain.Frameset;
import Domain.Gearset;

public class FramesetCreator extends AbstractFramesetCreator{

	public FramesetCreator(Window owner) {
		super(owner);
	}
	
	public static Frameset addFrameset(Window owner) {
		FramesetCreator myWindow = new FramesetCreator(owner);
		return myWindow.showCreatorDialog();
	}

	protected Frameset sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double size = sizeRow.getFieldValue();
		Boolean shocks = shocksRow.getFieldValue();
		Gearset gears = gearsRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Frameset newFrameset = null;
		try {
			newFrameset = FrameOperations.insertFrameset(serialNumber, brandName, cost, size, shocks, gears, availableStock);
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "Error adding component - Frameset/Serial number combination already in use", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return newFrameset;
	}
}
