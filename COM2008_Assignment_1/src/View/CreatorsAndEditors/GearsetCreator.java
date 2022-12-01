package View.CreatorsAndEditors;

import java.awt.Dialog;
import java.sql.SQLException;

import Database.GearsetOperations;
import Domain.Gearset;

public class GearsetCreator extends AbstractGearsetCreator {
	
	public GearsetCreator(Dialog owner) {
		super(owner);

	}

	public static Gearset addGearset(Dialog owner) {
		GearsetCreator newCreatorWindow = new GearsetCreator(owner);
		return newCreatorWindow.showCreatorDialog();
	}
	
	protected Gearset sendValueToDatabase() throws SQLException {
		String name = nameRow.getFieldValue();
		
		Gearset newGearset = GearsetOperations.createGearset(name);
		return newGearset;
	}
	
}
