package View.StaffWindow;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.AbstractFramesetCreator;
import View.FramesetPicker;
import View.AbstractHandlebarCreator;
import View.GearPicker;
import View.GearsetCreator;
import View.HandlebarCreator;
import View.HandlebarPicker;
import View.AbstractWheelCreator;
import View.WheelPicker;

public class ManageStockPanel extends JPanel {	
	private JFrame owner;
	
	public ManageStockPanel(JFrame owner) {
		super();
		this.owner = owner;
		
		addComponents();
	}
	
	private void addComponents() {
		this.setLayout(new FlowLayout());
		
		final JButton manageFramesButton = new JButton("Manage Frames");
		manageFramesButton.addActionListener(e -> FramesetPicker.chooseFrameset(owner, true));
		
		final JButton addFrameButton = new JButton("Add Frame");
		addFrameButton.addActionListener(e -> AbstractFramesetCreator.addHFrameset(owner));
		
		final JPanel framesetBox = new ManagementBox("Framesets", manageFramesButton, addFrameButton);
		this.add(framesetBox);
		
		
		
		final JButton manageWheelsButton = new JButton("Manage Wheels");
		manageWheelsButton.addActionListener(e -> WheelPicker.chooseWheels(owner, true));
		
		final JButton addWheelsButton = new JButton("Add Wheel");
		addWheelsButton.addActionListener(e -> AbstractWheelCreator.addWheel(owner));
		
		final JPanel wheelsBox = new ManagementBox("Wheels", manageWheelsButton, addWheelsButton);
		this.add(wheelsBox);
		
		
		
		final JButton manageHandlebarButton = new JButton("Manage Handlebars");
		manageHandlebarButton.addActionListener(e -> HandlebarPicker.chooseHandlebar(owner, true));
		
		final JButton addHandlebarButton = new JButton("Add Handlebar");
		addHandlebarButton.addActionListener(e -> AbstractHandlebarCreator.addHandlebar(owner));
		
		final JPanel handlebarBox = new ManagementBox("Handlebars", manageHandlebarButton, addHandlebarButton);
		this.add(handlebarBox);
		
		
		final JButton manageGearsButton = new JButton("Manage Gears");
		manageGearsButton.addActionListener(e -> GearPicker.chooseGearset(owner, true));
        
        final JButton addGearButton = new JButton("Add Gear");
        addGearButton.addActionListener(e -> GearsetCreator.addGearset(owner));
        
        final JPanel gearsBox = new ManagementBox("Gears", manageGearsButton, addGearButton);
        this.add(gearsBox);
		
	}
	
	private class ManagementBox extends JPanel {
		private JButton manageStockButton;
		private JButton addNewButton;
		
		public ManagementBox(String title, JButton manageStockButton, JButton addNewButton) {
			super();
			
			this.setBorder(BorderFactory.createTitledBorder(title));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			this.manageStockButton = manageStockButton;
			this.addNewButton = addNewButton;
			
			addComponents();
		}
		
		private void addComponents() {
			this.add(manageStockButton);
			this.add(addNewButton);
		}
	}

}
