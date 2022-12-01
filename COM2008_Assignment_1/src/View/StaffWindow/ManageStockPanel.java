package View.StaffWindow;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.CreatorsAndEditors.AbstractFramesetCreator;
import View.CreatorsAndEditors.AbstractHandlebarCreator;
import View.CreatorsAndEditors.AbstractWheelCreator;
import View.CreatorsAndEditors.FramesetCreator;
import View.CreatorsAndEditors.GearsetCreator;
import View.CreatorsAndEditors.AbstractGearsetCreator;
import View.CreatorsAndEditors.HandlebarCreator;
import View.CreatorsAndEditors.WheelCreator;
import View.Pickers.BicyclePicker;
import View.Pickers.FramesetPicker;
import View.Pickers.GearPicker;
import View.Pickers.HandlebarPicker;
import View.Pickers.WheelPicker;

/*
 * Panel for managing stock, contains several controls for managing various bicycle component and others from a staff perspective
 */
public class ManageStockPanel extends JPanel {	
	private JDialog owner;
	
	public ManageStockPanel(JDialog owner) {
		super();
		this.owner = owner;
		
		addComponents();
	}
	
	private void addComponents() {
		this.setLayout(new FlowLayout());
		
		final JButton manageFramesButton = new JButton("Manage Frames");
		manageFramesButton.addActionListener(e -> FramesetPicker.chooseFrameset(owner, true));
		
		final JButton addFrameButton = new JButton("Add Frame");
		addFrameButton.addActionListener(e -> FramesetCreator.addFrameset(owner));
		
		final JPanel framesetBox = new ManagementBox("Framesets", manageFramesButton, addFrameButton);
		this.add(framesetBox);
		
		
		
		final JButton manageWheelsButton = new JButton("Manage Wheels");
		manageWheelsButton.addActionListener(e -> WheelPicker.chooseWheels(owner, true));
		
		final JButton addWheelsButton = new JButton("Add Wheel");
		addWheelsButton.addActionListener(e -> WheelCreator.addWheel(owner));
		
		final JPanel wheelsBox = new ManagementBox("Wheels", manageWheelsButton, addWheelsButton);
		this.add(wheelsBox);
		
		
		
		final JButton manageHandlebarButton = new JButton("Manage Handlebars");
		manageHandlebarButton.addActionListener(e -> HandlebarPicker.chooseHandlebar(owner, true));
		
		final JButton addHandlebarButton = new JButton("Add Handlebar");
		addHandlebarButton.addActionListener(e -> HandlebarCreator.addHandlebar(owner));
		
		final JPanel handlebarBox = new ManagementBox("Handlebars", manageHandlebarButton, addHandlebarButton);
		this.add(handlebarBox);
		
		
		final JButton manageGearsButton = new JButton("Manage Gears");
		manageGearsButton.addActionListener(e -> GearPicker.chooseGearset(owner, true));
        
        final JButton addGearButton = new JButton("Add Gear");
        addGearButton.addActionListener(e -> GearsetCreator.addGearset(owner));
        
        final JPanel gearsBox = new ManagementBox("Gears", manageGearsButton, addGearButton);
        this.add(gearsBox);
        
        
        final JButton manageDesignsButton = new JButton("Manage Designs");
        manageDesignsButton.addActionListener(e -> BicyclePicker.chooseBicycle(owner, true));
		
        final JPanel designsBox = new ManagementOnlyBox("Designs", manageDesignsButton);
        
        
        this.add(designsBox);
		
	}
	
	private class ManagementBox extends ManagementOnlyBox {
		private JButton addNewButton;
		
		public ManagementBox(String title, JButton manageStockButton, JButton addNewButton) {
			super(title, manageStockButton);
			
			this.setBorder(BorderFactory.createTitledBorder(title));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			this.addNewButton = addNewButton;
			
			addComponents();
		}
		
		private void addComponents() {
			this.add(addNewButton);
		}
	}
	
	private class ManagementOnlyBox extends JPanel {
		private JButton manageStockButton;
		
		public ManagementOnlyBox(String title, JButton manageStockButton) {
			super();
			
			this.setBorder(BorderFactory.createTitledBorder(title));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			this.manageStockButton = manageStockButton;
			
			addComponents();
		}
		
		private void addComponents() {
			this.add(manageStockButton);
		}
	}

}
