package View.AbstractCreator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Collection;

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
import View.JIntegerField;

public abstract class AbstractCreator<T> extends JDialog {
	
	private JIntegerField serialNumberField;
	private JTextField brandNameField;
	private JIntegerField costField;
	private JComboBox<HandlebarStyles> stylesList;
	
	private JPanel gridPanel;
	private JPanel bottomPanel;

	public AbstractCreator(Frame owner) {
		super(owner);
		addComponents();
	}
	
	protected abstract Collection<GridRow> getGridValues();
	
	protected abstract T sendValueToDatabase();
	
	private void addComponents() {
		
		//Set GUI Layout:
		gridPanel = new JPanel();
		bottomPanel = new JPanel();
		
		GridLayout layout = new GridLayout(0,2);
		gridPanel.setLayout(layout);
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton confirmAttributes = new JButton("Confirm");
		confirmAttributes.addActionListener(e -> createNewObject());
		
		bottomPanel.add(confirmAttributes);
		
		final Container pane = this.getContentPane();	
		pane.setLayout(new BorderLayout());
		
		pane.add(gridPanel, BorderLayout.CENTER);
		pane.add(bottomPanel, BorderLayout.SOUTH);
		
		final Collection<GridRow> gridValuesToAdd = getGridValues();
		
		gridValuesToAdd.forEach(gridRow -> {
			gridPanel.add(new JLabel(gridRow.getLabelText()));
			gridPanel.add(gridRow.getInputField().getComponent());
		});
	}
	
	T result = null;
	public void createNewObject() {
		T objectFromDatabase = sendValueToDatabase();
		
		result = objectFromDatabase;
		this.setVisible(false);
	}
	
	public T showCreatorDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
	
}
