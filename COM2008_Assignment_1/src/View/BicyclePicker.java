package View;

import javax.swing.JButton;
import javax.swing.JFrame;

import Domain.Bicycle;

public class BicyclePicker extends JFrame {
	
	private JButton addNewBicycleButton = new JButton("Design new bicycle");

	public BicyclePicker() {
		super("Bicycle Picker");
				
		Bicycle chosenBike = BicycleDesigner.designBicycle(this);
	}
	
	


}
