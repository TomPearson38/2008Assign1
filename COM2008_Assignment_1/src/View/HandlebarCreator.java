package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Domain.Bicycle;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarCreator extends JDialog {

	public HandlebarCreator(Frame owner) {
		super(owner);
		addComponents();
	}
	
	private void addComponents() {
		//Serial Number GUI Input Fields:
		JLabel serialNumberLabel = new JLabel("Serial Number: ");
		
		JTextField serialNumberField = new JTextField();
		serialNumberField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Brand Name GUI Input Fields:
		JLabel brandNameLabel = new JLabel("Brand Name: ");
		
		JTextField brandNameField = new JTextField();
		brandNameField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Cost GUI Input Fields:
		JLabel costLabel = new JLabel("Brand Name: ");
		
		JTextField costField = new JTextField();
		costField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Style GUI Input Fields:
		JLabel stylesLabel = new JLabel("Styles: ");
		
		String [] styles = new String [] {"high", "straight", "dropped"};
		
		JComboBox<HandlebarStyles> styleList = new JComboBox<HandlebarStyles>(HandlebarStyles);
		

		
		//Set GUI Layout:
		final Container pane = this.getContentPane();		
		GridLayout layout = new GridLayout(0,2);
		pane.setLayout(layout);
		
		
		//Add Created GUI Fields to the pane:
		pane.add(serialNumberLabel);
		pane.add(serialNumberField);
		pane.add(brandNameLabel);
		pane.add(brandNameField);
		pane.add(costLabel);
		pane.add(costField);
		pane.add(stylesLabel);
		pane.add(stylesField);

		
		//TODO:
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
