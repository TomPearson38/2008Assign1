package View.BicycleDesigner;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import Domain.Bicycle;

public class BicycleDesignerDialog extends JDialog {
	
	private Bicycle result;
	
	BicycleDesignerPanel designer;
	
	private JFrame _parent;
	
//	public BicycleDesignerDialog(JDialog owner) {
//		super(owner);
//		designer = new BicycleDesignerPanel(owner);
//		addControls();
//	}
	
	public BicycleDesignerDialog(JFrame owner) {
		super(owner);
		designer = new BicycleDesignerPanel(owner);
		addControls();
	}
	
	public Bicycle showDesignerDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
	
	public static Bicycle designBicycle(JFrame parent) {
    	BicycleDesignerDialog DesignerWindow = new BicycleDesignerDialog(parent);
    	
    	DesignerWindow.setTitle("Bicycle Designer");
    	
    	return DesignerWindow.showDesignerDialog();  	
    }
	
	private void addControls() {
		final Container pane = this.getContentPane();
		
		pane.add(designer);
	}
	
	
}
