package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Database.FrameOperations;
import Domain.Frameset;
import Domain.Gearset;

public class FramesetEditor extends AbstractFramesetCreator {
	
	int framesetID;

	public FramesetEditor(Window owner, Frameset framesetToEdit) {
		super(owner);
		framesetID = framesetToEdit.get_id();
		
		brandNameRow.setFieldValue(framesetToEdit.getBrandName());
		serialNumberRow.setFieldValue(framesetToEdit.getSerialNumber());
		costRow.setFieldValue(framesetToEdit.getCost());
		sizeRow.setFieldValue(framesetToEdit.get_size());
		shocksRow.setFieldValue(framesetToEdit.get_shocks());
		gearsRow.setFieldValue(framesetToEdit.get_gearset());
		stockRow.setFieldValue(framesetToEdit.getStockNum());
		
	}

	protected Frameset sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		Double size = sizeRow.getFieldValue();
		Boolean shocks = shocksRow.getFieldValue();
		Gearset gears = gearsRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Frameset framesetToUpdate = new Frameset(this.framesetID, brandName, serialNumber, cost, size, gears, shocks, availableStock);
		try {
			FrameOperations.updateFrameset(framesetToUpdate);
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "Error editing component - Frameset/Serial number combination already in use", "Error!", JOptionPane.ERROR_MESSAGE);
			
		}
		return framesetToUpdate;
	}
	
	public static Frameset openEditor(Window owner, Frameset object) {
		FramesetEditor newEditorWindow = new FramesetEditor(owner, object);
		return newEditorWindow.showCreatorDialog();
	}
}
