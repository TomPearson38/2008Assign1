package View;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import Domain.Bicycle;

public class BicyclePicker extends JFrame {
	
	private JButton designNewBicycleButton = new JButton("Design new bicycle");
	private JButton designNewHandlebarButton = new JButton("Add a new handlebar");

	public BicyclePicker() {
		super("Bicycle Picker");
				
		final Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout());
		
		designNewBicycleButton.addActionListener(e -> BicycleDesigner.designBicycle(this));
		pane.add(designNewBicycleButton);
		
		designNewHandlebarButton.addActionListener(e -> HandlebarCreator.addHandlebar(this));
		pane.add(designNewHandlebarButton);
	}
}
