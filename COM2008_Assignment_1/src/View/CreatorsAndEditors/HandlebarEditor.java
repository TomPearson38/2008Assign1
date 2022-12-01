package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarEditor extends AbstractHandlebarCreator {
	
	int handlebarID;

	public HandlebarEditor(Window owner, Handlebar handlebarToEdit) {
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
		try {
			HandlebarOperations.updateHandlebar(handlebarToUpdate);
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "Error editing component - Frameset/Serial number combination already in use", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return handlebarToUpdate;
	}

	public static Handlebar openEditor(Window owner, Handlebar handlebarToEdit) {
		HandlebarEditor newEditorWindow = new HandlebarEditor(owner, handlebarToEdit);
		return newEditorWindow.showCreatorDialog();
	}

}
