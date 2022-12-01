package View.CreatorsAndEditors;

import java.awt.Window;
import java.sql.SQLException;

import Database.GearsetOperations;
import Database.GearsetOperations.GearsetUpdateRequest;
import Domain.Gearset;

public class GearsetEditor extends AbstractGearsetCreator {
	private int id;

	public GearsetEditor(Window owner, Gearset gearsetToEdit) {
		super(owner);
		this.id = gearsetToEdit.get_id();
	}
	
	protected Gearset sendValueToDatabase() throws SQLException {
		String name = nameRow.getFieldValue();
		
		GearsetUpdateRequest request = new GearsetUpdateRequest(id, name);
		
		return GearsetOperations.updateGearset(request);
	}
	
	public static Gearset openEditor(Window owner, Gearset object) {
		GearsetEditor newEditorWindow = new GearsetEditor(owner, object);
		return newEditorWindow.showCreatorDialog();
	}

}
