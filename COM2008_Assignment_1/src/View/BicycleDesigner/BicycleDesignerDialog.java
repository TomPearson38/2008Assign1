package View.BicycleDesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domain.Bicycle;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Wheel;
import View.Pickers.FramesetPicker;
import View.Pickers.HandlebarPicker;
import View.Pickers.WheelPicker;

public class BicycleDesignerDialog extends JDialog {
	
	private Bicycle result;
	
	private JFrame _parent;
	
	public BicycleDesignerDialog(JFrame parent) {
		super(parent);
		_parent = parent;
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
		this.setPreferredSize(new Dimension(500,400));
		
		final BicycleDesignerPanel designer = new BicycleDesignerPanel(_parent);
		
		pane.add(designer);
	}
	
	
}
