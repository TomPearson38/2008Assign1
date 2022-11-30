package View;

import java.awt.Frame;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarEditor extends AbstractHandlebarCreator{
	
	int handlebarID;

	public HandlebarEditor(Frame owner, Handlebar handlebarToEdit) {
		// TODO Auto-generated constructor stub
		super(owner);
		handlebarID = handlebarToEdit.get_id();
		
		brandNameRow.setFieldValue(handlebarToEdit.BrandName());
		serialNumberRow.setFieldValue(handlebarToEdit.SerialNumber());
		costRow.setFieldValue(handlebarToEdit.Cost());
		stylesRow.setFieldValue(handlebarToEdit.get_style());
		stockRow.setFieldValue(handlebarToEdit.StockNum());
		
	}
	
	protected Handlebar sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		HandlebarStyles style = stylesRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Handlebar newHandlebar = new Handlebar(this.handlebarID, brandName, serialNumber, cost, style, availableStock);
		return newHandlebar;
	}

}
