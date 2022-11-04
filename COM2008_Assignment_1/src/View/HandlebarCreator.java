package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domain.Bicycle;
import Domain.Handlebar;

public class HandlebarCreator extends JDialog {

	public HandlebarCreator(Frame owner) {
		super(owner);
		addComponents();
	}
	
	private void addComponents() {
		JLabel serialNumberLabel = new JLabel("Serial Number: ");
		
		JTextField serialNumberField = new JTextField();
		serialNumberField.setPreferredSize(new Dimension(200, 20));
		
		JPanel serialNumberRow = new JPanel(new FlowLayout());	
		serialNumberRow.add(serialNumberLabel);
		serialNumberRow.add(serialNumberField);
		
		JLabel serialNumberLabel2 = new JLabel("Serial Number: ");
		
		JTextField serialNumberField2 = new JTextField();
		serialNumberField2.setPreferredSize(new Dimension(200, 20));
		
		JPanel serialNumberRow2 = new JPanel(new FlowLayout());	
		serialNumberRow2.add(serialNumberLabel2);
		serialNumberRow2.add(serialNumberField2);
		
		
		final Container pane = this.getContentPane();
		FlowLayout topToBot = new FlowLayout();
		topToBot.setAlignment(FlowLayout.LEADING);
		
		pane.setLayout(topToBot);
		pane.add(serialNumberRow);
		pane.add(serialNumberRow2);
		
		//TODO:
		//ADD REST OF ATTRIBUTES OF HANDLEBAR TO UI
		//ADD OK BUTTON THAT CALLS THE createHandlebar() DATABASE METHOD AFTER VALIDATING INPUT
	}
	
	public static Handlebar addHandlebar(Frame owner) {
		HandlebarCreator myWindow = new HandlebarCreator(owner);
		return myWindow.showCreatorDialog();
	}
	
	public Handlebar showCreatorDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return null;
    }
}
