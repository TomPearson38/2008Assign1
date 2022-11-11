package View;

import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Database.HandlebarOperations;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.GridRow;
import View.AbstractCreator.IGridRow;
import View.AbstractCreator.CreatorInputField;

public class HandlebarCreator extends AbstractCreator<Handlebar> {
	GridRow<Integer, JIntegerField> serialNumberRow;
	GridRow<String, JTextField> brandNameRow;
	GridRow<Double, JDoubleField> costRow;
	GridRow<HandlebarStyles, JComboBox<HandlebarStyles>> stylesRow;
	
	public HandlebarCreator(Frame owner) {
		super(owner);
	}
	
	public static Handlebar addHandlebar(Frame owner) {
		HandlebarCreator myWindow = new HandlebarCreator(owner);
		return myWindow.showCreatorDialog();
	}


	@Override
	protected Collection<IGridRow> getGridValues() {

		serialNumberRow = new GridRow<Integer, JIntegerField>(
				"Serial Number", 
				new CreatorInputField<Integer, JIntegerField>(
						new JIntegerField(), 
						Field -> Field.getInt()
						),
				row -> row.getFieldValue() != null
				);
		
		brandNameRow = new GridRow<String, JTextField>(
				"Brand Name", 
				new CreatorInputField<String, JTextField>(
						new JTextField(), 
						Field -> Field.getText()
						),
				row -> row.getFieldValue() != null && !row.getFieldValue().isEmpty()
				);
		
		costRow = new GridRow<Double, JDoubleField>(
				"Cost", 
				new CreatorInputField<Double, JDoubleField>(
						new JDoubleField(), 
						Field -> Field.getDouble()
						),
				row -> row.getFieldValue() != null
				);
		
		stylesRow = new GridRow<HandlebarStyles, JComboBox<HandlebarStyles>>(
				"Styles", 
				new CreatorInputField<HandlebarStyles, JComboBox<HandlebarStyles>>(
						new JComboBox<HandlebarStyles>(HandlebarStyles.values()),
						Field -> (HandlebarStyles)Field.getSelectedItem()
						)
				);
		
		
		return Arrays.asList(serialNumberRow, brandNameRow, costRow, stylesRow);
	}
	
	protected Handlebar sendValueToDatabase() {
		String brandName = brandNameRow.getFieldValue();
		int serialNumber = serialNumberRow.getFieldValue();
		Double cost = costRow.getFieldValue();
		HandlebarStyles style = stylesRow.getFieldValue();
		
		Handlebar newHandlebar = HandlebarOperations.createHandlebar(brandName, serialNumber, cost, style);
		return newHandlebar;
	}
	

}
