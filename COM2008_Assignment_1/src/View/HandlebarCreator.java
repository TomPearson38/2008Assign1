package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Domain.Bicycle;
import Domain.Handlebar;

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
		
		JPanel serialNumberRow = new JPanel(new FlowLayout());	
		serialNumberRow.add(serialNumberLabel);
		serialNumberRow.add(serialNumberField);
		
		//Brand Name GUI Input Fields:
		JLabel brandNameLabel = new JLabel("Brand Name: ");
		
		JTextField brandNameField = new JTextField();
		brandNameField.setPreferredSize(new Dimension(200, 20));
		
		JPanel brandNameRow = new JPanel(new FlowLayout());	
		brandNameRow.add(brandNameLabel);
		brandNameRow.add(brandNameField);
		
		//Cost GUI Input Fields:
		JLabel costLabel = new JLabel("Brand Name: ");
		
		JTextField costField = new JTextField();
		costField.setPreferredSize(new Dimension(200, 20));
		
		JPanel costRow = new JPanel(new FlowLayout());	
		costRow.add(costLabel);
		costRow.add(costField);
		
		//Style GUI Input Fields:
		JLabel stylesLabel = new JLabel("Styles: ");
		
		String [] styles = new String [] {"high", "straight", "dropped"};
		
		JList styleList = new JList(styles);
		styleList.setVisibleRowCount(3);
		JScrollPane styleField = new JScrollPane(styleList);
		styleField.setPreferredSize(new Dimension(200, 20));
		
		JPanel styleRow = new JPanel(new FlowLayout());
		styleRow.add(stylesLabel);
		styleRow.add(styleField);
		
		//Set GUI Layout:
		final Container pane = this.getContentPane();
		FlowLayout topToBot = new FlowLayout();
		topToBot.setAlignment(FlowLayout.LEADING);
		
		//Add Created GUI Fields to the pane:
		pane.setLayout(topToBot);
		pane.add(serialNumberRow);
		pane.add(brandNameRow);
		pane.add(costRow);
		pane.add(styleRow);
		
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
