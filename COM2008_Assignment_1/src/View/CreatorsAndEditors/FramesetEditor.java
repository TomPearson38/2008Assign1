package View.CreatorsAndEditors;

import java.awt.Dialog;
import java.awt.Frame;

import Database.FrameOperations;
import Domain.Frameset;
import Domain.Gearset;
import Domain.Wheel;
import View.AbstractPicker.IEditor;

public class FramesetEditor extends AbstractFramesetCreator {
	
	int framesetID;

	public FramesetEditor(Dialog owner, Frameset framesetToEdit) {
		// TODO Auto-generated constructor stub
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
		FrameOperations.updateFrameset(framesetToUpdate);
		return framesetToUpdate;
	}
	
	public static Frameset openEditor(Dialog owner, Frameset object) {
		FramesetEditor newEditorWindow = new FramesetEditor(owner, object);
		return newEditorWindow.showCreatorDialog();
	}
}
