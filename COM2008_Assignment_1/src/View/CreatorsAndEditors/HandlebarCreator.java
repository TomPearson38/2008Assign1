package View.CreatorsAndEditors;

import java.awt.Dialog;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarCreator extends AbstractHandlebarCreator{

	public HandlebarCreator(Dialog owner) {
		// TODO Auto-generated constructor stub
		super(owner);
	}
	
	public static Handlebar addHandlebar(Dialog owner) {
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
