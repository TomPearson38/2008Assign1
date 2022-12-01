package View.BicycleDesigner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Domain.Bicycle;

public class BicycleDesignerDialog extends JDialog {
	
	private Bicycle result;
	
	BicycleDesignerPanel designer;
	
	
	
	public BicycleDesignerDialog(Window owner) {
		super(owner, ModalityType.APPLICATION_MODAL);
		designer = new BicycleDesignerPanel(owner);
		addControls();
	}
	
	public BicycleDesignerDialog(Window owner, Bicycle designToEdit) {
		super(owner);
		designer = new BicycleDesignerPanel(owner);
		designer.setDesign(designToEdit);
		addControls();
	}
	
	public Bicycle showDesignerDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setTitle("Bicycle Designer");
        setVisible(true);
        
        return result;
    }
	
	public static Bicycle designNewBicycle(Window owner) {
    	BicycleDesignerDialog DesignerWindow = new BicycleDesignerDialog(owner);
    	
    	return DesignerWindow.showDesignerDialog();  	
    }
	public static Bicycle editBicycle(Window owner, Bicycle designToEdit) {
    	BicycleDesignerDialog DesignerWindow = new BicycleDesignerDialog(owner, designToEdit);

    	return DesignerWindow.showDesignerDialog();  	
    }
	
	private void addControls() {
		final Container pane = this.getContentPane();
		this.setLayout(new BorderLayout());
		
		final JButton saveButton = new SaveBicycleDesignButton("Save design", designer);
		final JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(saveButton, BorderLayout.EAST);
		pane.add(bottomPanel, BorderLayout.SOUTH);
		
		pane.add(designer, BorderLayout.CENTER);
	}
	
	
}
