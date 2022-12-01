package View.CreatorsAndEditors;

import java.awt.Dialog;
import java.awt.Frame;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import View.AbstractPicker.IEditor;

public class HandlebarEditor extends AbstractHandlebarCreator {
	
	int handlebarID;

	public HandlebarEditor(Dialog owner, Handlebar handlebarToEdit) {
		// TODO Auto-generated constructor stub
		super(owner);
		handlebarID = handlebarToEdit.get_id();
		
		brandNameRow.setFieldValue(handlebarToEdit.getBrandName());
		serialNumberRow.setFieldValue(handlebarToEdit.getSerialNumber());
		costRow.setFieldValue(handlebarToEdit.getCost());
		stylesRow.setFieldValue(handlebarToEdit.get_style());
		stockRow.setFieldValue(handlebarToEdit.getStockNum());
		
	}
	
	protected Handlebar sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		HandlebarStyles style = stylesRow.getFieldValue();
		int availableStock = stockRow.getFieldValue();
		
		Handlebar handlebarToUpdate = new Handlebar(this.handlebarID, brandName, serialNumber, cost, style, availableStock);
		HandlebarOperations.updateHandlebar(handlebarToUpdate);
		return handlebarToUpdate;
	}

	public static Handlebar openEditor(Dialog owner, Handlebar handlebarToEdit) {
		HandlebarEditor newEditorWindow = new HandlebarEditor(owner, handlebarToEdit);
		return newEditorWindow.showCreatorDialog();
	}

}
