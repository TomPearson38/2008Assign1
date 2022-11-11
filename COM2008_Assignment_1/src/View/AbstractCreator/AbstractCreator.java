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
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	
	private Frame parent;
	final Collection<IGridRow> gridValuesToAdd = getGridValues();

	public AbstractCreator(Frame owner) {
		super(owner);
		addComponents();
		parent = owner;
	}
	
	protected abstract Collection<IGridRow> getGridValues();
	
	protected abstract T sendValueToDatabase();
	
	private void addComponents() {
		
		//Set GUI Layout:
		gridPanel = new JPanel();
		bottomPanel = new JPanel();
		
		GridLayout layout = new GridLayout(0,2);
		gridPanel.setLayout(layout);
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton confirmAttributes = new JButton("Confirm");
		confirmAttributes.addActionListener(e -> confirmButtonClicked());
		
		bottomPanel.add(confirmAttributes);
		
		final Container pane = this.getContentPane();	
		pane.setLayout(new BorderLayout());
		
		pane.add(gridPanel, BorderLayout.CENTER);
		pane.add(bottomPanel, BorderLayout.SOUTH);
		
		gridValuesToAdd.forEach(gridRow -> {
			gridPanel.add(new JLabel(gridRow.getRowLabel() + ": "));
			gridPanel.add(gridRow.getGridRowComponent());
		});
	}
	
	T result = null;
	public void confirmButtonClicked() {
		
		Collection<IGridRow> invalidRows = gridValuesToAdd.stream().filter(row -> !row.isRowValid()).collect(Collectors.toList());
		if (invalidRows.size() > 0) {
			String [] invalidRowLabels = invalidRows.stream().map(IGridRow::getRowLabel).toArray(String[]::new);
			JOptionPane.showMessageDialog(parent, "The following rows are invalid: " + String.join(", ", invalidRowLabels), "Error: Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		T objectFromDatabase = sendValueToDatabase();
		if (objectFromDatabase == null) {
			JOptionPane.showMessageDialog(parent, "Error: Invalid Input", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
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
