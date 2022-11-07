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
import View.AbstractCreator.JInputField;

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
	protected Collection<GridRow> getGridValues() {

		serialNumberRow = new GridRow<Integer, JIntegerField>(
				"Serial Number: ", 
				new JInputField<Integer, JIntegerField>(
						new JIntegerField(), 
						Field -> Field.getInt()
						)
				);
		
		brandNameRow = new GridRow<String, JTextField>(
				"Brand Name: ", 
				new JInputField<String, JTextField>(
						new JTextField(), 
						Field -> Field.getText()
						)
				);
		
		costRow = new GridRow<Double, JDoubleField>(
				"Cost: ", 
				new JInputField<Double, JDoubleField>(
						new JDoubleField(), 
						Field -> Field.getDouble()
						)
				);
		
		stylesRow = new GridRow<HandlebarStyles, JComboBox<HandlebarStyles>>(
				"Styles: ", 
				new JInputField<HandlebarStyles, JComboBox<HandlebarStyles>>(
						new JComboBox<HandlebarStyles>(HandlebarStyles.values()),
						Field -> (HandlebarStyles)Field.getSelectedItem()
						)
				);
		
		
		return Arrays.asList(serialNumberRow, brandNameRow, costRow, stylesRow);
	}
	
	protected Handlebar sendValueToDatabase() {
		String brandName = brandNameRow.getInputField().getValueFromComponent();
		int serialNumber = serialNumberRow.getInputField().getValueFromComponent();
		Double cost = costRow.getInputField().getValueFromComponent();
		HandlebarStyles style = stylesRow.getInputField().getValueFromComponent();
		
		Handlebar newHandlebar = HandlebarOperations.createHandlebar(brandName, serialNumber, cost, style);
		return newHandlebar;
	}
	

}
