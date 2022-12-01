package View.BicycleDesigner;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Database.BicycleOperations;
import Domain.Bicycle;
import Resources.ResourceSingleton;

public class SaveBicycleDesignButton extends JButton {

	private BicycleDesignerPanel designerPanel;

	public SaveBicycleDesignButton(String text, BicycleDesignerPanel designerPanel) {
		super(text);
		this.designerPanel = designerPanel;
		
		designerPanel.addIsNewDesignListener(isNewDesign -> {
			if (isNewDesign) {
				setText("Save New Design");
			} else if (!isNewDesign) {
				setText("Update Design");
			}
		});
		setIcon(new ImageIcon(ResourceSingleton.getSaveIcon()));
		setEnabled(false);
		Supplier<Boolean> shouldSaveButtonBeEnabled = () -> {
			return designerPanel.isDesignValid() && !designerPanel.isDesignSaved();
		};
		designerPanel.addDesignValidityListener(e -> setEnabled(shouldSaveButtonBeEnabled.get()));
		designerPanel.addDesignSavedListener(e -> setEnabled(shouldSaveButtonBeEnabled.get()));
		
		this.addActionListener(this::saveButtonClicked);
	}
	
	/**
	 * Saves bike to database if valid
	 * @param e
	 */
	private void saveButtonClicked(ActionEvent e) {
		if (designerPanel.isNewDesign()) {
			//add to DB
			try {
				Bicycle newDesignSavedToDatabase = BicycleOperations.addBicycle(designerPanel.generateBicycleCreateRequest());
				designerPanel.setDesign(newDesignSavedToDatabase);
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Couldn't save to database", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			//update existing record in DB
			final Bicycle bicycleToUpdate = designerPanel.getUpdatedVersionOfOriginalDomainObject();
			boolean updateSucceeded = BicycleOperations.updateBicycle(bicycleToUpdate);
			if (updateSucceeded) {
				designerPanel.setDesign(bicycleToUpdate);
			} else {
				JOptionPane.showMessageDialog(this, "Couldn't update design in database", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
