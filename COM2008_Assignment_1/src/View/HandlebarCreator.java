package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Database.HandlebarOperations;
import Domain.Bicycle;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class HandlebarCreator extends JDialog {
	
	private JIntegerField serialNumberField;
	private JTextField brandNameField;
	private JIntegerField costField;
	private JComboBox<HandlebarStyles> stylesList;

	public HandlebarCreator(Frame owner) {
		super(owner);
		addComponents();
	}
	
	private void addComponents() {
		//Serial Number GUI Input Fields:
		JLabel serialNumberLabel = new JLabel("Serial Number: ");
		
		
		JIntegerField serialNumberField = new JIntegerField();
		serialNumberField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Brand Name GUI Input Fields:
		JLabel brandNameLabel = new JLabel("Brand Name: ");
		
		JTextField brandNameField = new JTextField();
		brandNameField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Cost GUI Input Fields:
		JLabel costLabel = new JLabel("Cost: ");
		
		JIntegerField costField = new JIntegerField();
		
		costField.setPreferredSize(new Dimension(200, 20));
		
		
		
		//Style GUI Input Fields:
		JLabel stylesLabel = new JLabel("Styles: ");
		
		String [] styles = new String [] {"high", "straight", "dropped"};
		
		JComboBox<HandlebarStyles> stylesList = new JComboBox<HandlebarStyles>(HandlebarStyles.values());
		

		
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
		pane.add(stylesList);
		
		JButton confirmHandlebarAttributes = new JButton("Confirm");
		confirmHandlebarAttributes.addActionListener(e -> createNewHandlebar());

		
		//TODO:
		//ADD OK BUTTON THAT CALLS THE createHandlebar() DATABASE METHOD AFTER VALIDATING INPUT
	}
	
	public void createNewHandlebar() {
		String brandName = brandNameField.getText();
		Integer serialNumber = serialNumberField.getInt();
		Double cost = Double.parseDouble(costField.getText());
		HandlebarStyles style = (HandlebarStyles)stylesList.getSelectedItem();
		Handlebar newHandlebar = HandlebarOperations.createHandlebar(brandName, serialNumber, cost, style);
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
	
	class JIntegerField extends JFormattedTextField{
		
		private NumberFormatter getIntegerFormatter() {
			NumberFormat format = NumberFormat.getIntegerInstance();
			format.setGroupingUsed(false);
			
			NumberFormatter numberFormatter = new NumberFormatter(format);
			numberFormatter.setValueClass(Long.class);
			numberFormatter.setAllowsInvalid(false);
			return numberFormatter;
		}
		
		private DefaultFormatterFactory getFactory() {
			return new DefaultFormatterFactory(getIntegerFormatter());
		}

		public JIntegerField() {
			super();
			this.setFormatterFactory(getFactory());
			
		}
		
		public int getInt() {
			return Integer.parseInt(this.getText());
		}
		
	}
	
	
    class JDoubleField extends JFormattedTextField{
		
		private NumberFormatter getDoubleFormatter() {
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			
			NumberFormatter numberFormatter = new NumberFormatter(format);
			numberFormatter.setValueClass(Long.class);
			numberFormatter.setAllowsInvalid(false);
			return numberFormatter;
		}
		
		private DefaultFormatterFactory getFactory() {
			return new DefaultFormatterFactory(getDoubleFormatter());
		}

		public JDoubleField() {
			super();
			this.setFormatterFactory(getFactory());
			
		}
		
		public int getInt() {
			return Integer.parseInt(this.getText());
		}
		
	}
}
