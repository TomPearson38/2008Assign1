package View;

import java.awt.Frame;
import java.util.Collection;

import Domain.Handlebar;
import View.AbstractCreator.AbstractCreator;
import View.AbstractCreator.GridRow;

public class HandlebarCreator extends AbstractCreator{

	public HandlebarCreator(Frame owner) {
		super(owner);
	}
	
	public static Handlebar addHandlebar(Frame owner) {
		AbstractCreator myWindow = new HandlebarCreator(owner);
		return myWindow.showCreatorDialog();
	}

	@Override
	protected Collection<GridRow> getGridValues() {
		// TODO Auto-generated method stub
		GridRow serialNumberRow = new GridRow("Serial Number: ", new JInputField(new JIntegerField));
		return null;
	}

}
