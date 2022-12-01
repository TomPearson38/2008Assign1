package View.CreatorsAndEditors;

import java.awt.Window;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarCreator extends AbstractHandlebarCreator{

	public HandlebarCreator(Window owner) {
		super(owner);
	}
	
	public static Handlebar addHandlebar(Window owner) {
		HandlebarCreator myWindow = new HandlebarCreator(owner);
		return myWindow.showCreatorDialog();
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
