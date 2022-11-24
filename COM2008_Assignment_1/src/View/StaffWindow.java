package View;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class StaffWindow extends JDialog {
	
	private JButton viewComponentsButton = new JButton("View Bicycle Components");

	public StaffWindow(JFrame owner) {
		super(owner);
		// TODO Auto-generated constructor stub
		viewComponentsButton.addActionListener(e -> FramesetPicker.chooseFrameset(owner, true));
		this.getContentPane().add(viewComponentsButton);
	}
	
	public void showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
    }
	
}
