package View.CreatorsAndEditors;

import java.awt.Window;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Domain.Handlebar;
import Domain.HandlebarStyles;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.DoubleGridRow;
import View.AbstractCreator.EnumGridRow;
import View.AbstractCreator.IGridRow;
import View.AbstractCreator.IntegerGridRow;
import View.AbstractCreator.StringGridRow;
import View.UserControls.JDoubleField;
import View.UserControls.JIntegerField;

public abstract class AbstractHandlebarCreator extends AbstractCreator<Handlebar> {
	IGridRow<Integer, JIntegerField> serialNumberRow;
	IGridRow<String, JTextField> brandNameRow;
	IGridRow<Double, JDoubleField> costRow;
	IGridRow<HandlebarStyles, JComboBox<HandlebarStyles>> stylesRow;
	IGridRow<Integer, JIntegerField> stockRow;
	
	public AbstractHandlebarCreator(Window owner) {
		super(owner);
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
