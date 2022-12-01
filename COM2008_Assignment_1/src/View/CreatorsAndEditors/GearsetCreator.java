package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLException;

import Database.GearsetOperations;
import Domain.Gearset;

public class GearsetCreator extends AbstractGearsetCreator {
	
	public GearsetCreator(Window owner) {
		super(owner);

	}

	public static Gearset addGearset(Window owner) {
		GearsetCreator newCreatorWindow = new GearsetCreator(owner);
		return newCreatorWindow.showCreatorDialog();
	}
	
	protected Gearset sendValueToDatabase() throws SQLException {
		String name = nameRow.getFieldValue();
		
		Gearset newGearset = GearsetOperations.createGearset(name);
		return newGearset;
	}
	
}
