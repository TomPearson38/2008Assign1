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
import View.AbstractCreator.IntegerCreatorInputField;
import View.AbstractCreator.StringCreatorInputField;
import View.AbstractCreator.CreatorInputField;
import View.AbstractCreator.DoubleCreatorInputField;

public class HandlebarCreator extends AbstractCreator<Handlebar> {
	IGridRow<Integer, JIntegerField> serialNumberRow;
	IGridRow<String, JTextField> brandNameRow;
	IGridRow<Double, JDoubleField> costRow;
	IGridRow<HandlebarStyles, JComboBox<HandlebarStyles>> stylesRow;
	
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
				new IntegerCreatorInputField(),
				row -> row.getFieldValue() != null
				);
		
		brandNameRow = new GridRow<String, JTextField>(
				"Brand Name", 
				new StringCreatorInputField(),
				row -> row.getFieldValue() != null && !row.getFieldValue().isEmpty()
				);
		
		costRow = new GridRow<Double, JDoubleField>(
				"Cost", 
				new DoubleCreatorInputField(),
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
