package View.AbstractCreator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class AbstractCreator<T> extends JDialog {
	private JPanel gridPanel;
	private JPanel bottomPanel;
	
	final Collection<IGridRow<?, ?>> gridValuesToAdd = getGridValues();
	
	public AbstractCreator(Dialog owner) {
		super(owner, true);
		addComponents();
	}
	
	public AbstractCreator(Frame owner) {
		super(owner, true);
		addComponents();
	}
	
	protected abstract Collection<IGridRow<?, ?>> getGridValues();
	
	protected abstract T sendValueToDatabase() throws SQLException;
	
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
		
		Collection<IGridRow<?, ?>> invalidRows = gridValuesToAdd.stream().filter(row -> !row.isRowValid()).collect(Collectors.toList());
		if (invalidRows.size() > 0) {
			String [] invalidRowLabels = invalidRows.stream().map(IGridRow::getRowLabel).toArray(String[]::new);
			JOptionPane.showMessageDialog(this, "The following rows are invalid: " + String.join(", ", invalidRowLabels), "Error: Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		T objectFromDatabase;
		try {
			objectFromDatabase = sendValueToDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error deleting/updating component!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (objectFromDatabase == null) {
			JOptionPane.showMessageDialog(this, "Error: Invalid Input", "Input Error", JOptionPane.ERROR_MESSAGE);
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
