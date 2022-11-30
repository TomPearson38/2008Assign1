package View;

import java.awt.Frame;

import Database.FrameOperations;
import Domain.Frameset;
import Domain.Gearset;

public class FramesetCreator extends AbstractFramesetCreator{

	public FramesetCreator(Frame owner) {
		// TODO Auto-generated constructor stub
		super(owner);
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
