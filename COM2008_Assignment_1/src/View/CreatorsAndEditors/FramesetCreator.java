package View.CreatorsAndEditors;

import java.awt.Dialog;
import java.awt.Frame;

import Database.FrameOperations;
import Domain.Frameset;
import Domain.Gearset;

public class FramesetCreator extends AbstractFramesetCreator{

	public FramesetCreator(Dialog owner) {
		super(owner);
	}
	
	public static Frameset addFrameset(Dialog owner) {
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
		
		Frameset newFrameset = FrameOperations.insertFrameset(serialNumber, brandName, cost, size, shocks, gears, availableStock);
		return newFrameset;
	}
}
