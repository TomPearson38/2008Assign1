package View.CreatorsAndEditors;

import java.awt.Dialog;
import java.awt.Frame;
import java.sql.SQLException;

import Database.GearsetOperations;
import Database.GearsetOperations.GearsetUpdateRequest;
import Domain.Frameset;
import Domain.Gearset;

public class GearsetEditor extends AbstractGearsetCreator {
	private int id;

	public GearsetEditor(Dialog owner, Gearset gearsetToEdit) {
		super(owner);
		this.id = gearsetToEdit.get_id();
	}
	
	protected Gearset sendValueToDatabase() throws SQLException {
		String name = nameRow.getFieldValue();
		
		GearsetUpdateRequest request = new GearsetUpdateRequest(id, name);
		
		return GearsetOperations.updateGearset(request);
	}
	
	public static Gearset openEditor(Dialog owner, Gearset object) {
		GearsetEditor newEditorWindow = new GearsetEditor(owner, object);
		return newEditorWindow.showCreatorDialog();
	}

}
