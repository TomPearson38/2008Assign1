package View.CreatorsAndEditors;

import java.awt.Frame;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarCreator extends AbstractHandlebarCreator{

	public HandlebarCreator(Frame owner) {
		// TODO Auto-generated constructor stub
		super(owner);
	}
	
	protected Handlebar sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		HandlebarStyles style = stylesRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Handlebar newHandlebar = HandlebarOperations.createHandlebar(brandName, serialNumber, cost, style, availableStock);
		return newHandlebar;
	}

}
