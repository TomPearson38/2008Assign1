package View.CreatorsAndEditors;

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
import View.AbstractCreator.IntegerGridRow;
import View.AbstractCreator.StringCreatorInputField;
import View.AbstractCreator.StringGridRow;
import View.UserControls.JDoubleField;
import View.UserControls.JIntegerField;
import View.AbstractCreator.CreatorInputField;
import View.AbstractCreator.DoubleCreatorInputField;
import View.AbstractCreator.DoubleGridRow;
import View.AbstractCreator.EnumCreatorInputField;
import View.AbstractCreator.EnumGridRow;

public abstract class AbstractHandlebarCreator extends AbstractCreator<Handlebar> {
	IGridRow<Integer, JIntegerField> serialNumberRow;
	IGridRow<String, JTextField> brandNameRow;
	IGridRow<Double, JDoubleField> costRow;
	IGridRow<HandlebarStyles, JComboBox<HandlebarStyles>> stylesRow;
	IGridRow<Integer, JIntegerField> stockRow;
	
	public AbstractHandlebarCreator(Frame owner) {
		super(owner);
	}
	
	public static Handlebar addHandlebar(Frame owner) {
		HandlebarCreator myWindow = new HandlebarCreator(owner);
		return myWindow.showCreatorDialog();
	}


	@Override
	protected Collection<IGridRow<?, ?>> getGridValues() {

		serialNumberRow = new IntegerGridRow("Serial Number");
		
		brandNameRow = new StringGridRow("Brand Name");
		
		costRow = new DoubleGridRow("Cost");
		
		stylesRow = new EnumGridRow<HandlebarStyles>("Styles", HandlebarStyles.values());
		
		stockRow = new IntegerGridRow("Available Stock");
		
		
		return Arrays.asList(serialNumberRow, brandNameRow, costRow, stylesRow, stockRow);
	}
	
	protected abstract Handlebar sendValueToDatabase();
	

}
