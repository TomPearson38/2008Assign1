package View.CreatorsAndEditors;

import java.awt.Window;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JTextField;

import Domain.Gearset;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.IGridRow;
import View.AbstractCreator.StringGridRow;

/**
 * Used to add a new gearset
 * @author tomap
 *
 */
public abstract class AbstractGearsetCreator extends AbstractCreator<Gearset> {
	IGridRow<String, JTextField> nameRow;
	

	public AbstractGearsetCreator(Window owner) {
		super(owner);
	}


	@Override
	protected Collection<IGridRow<?, ?>> getGridValues() {

		nameRow = new StringGridRow("Name");
		
		return Arrays.asList(nameRow);
	}
	
	
	

}
