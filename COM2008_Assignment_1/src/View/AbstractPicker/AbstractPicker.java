package View.AbstractPicker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Domain.IToUIString;

public abstract class AbstractPicker<T extends IToUIString> extends JDialog {
    JComboBox<Boolean> shocksComboBox;
    JButton okButton = new JButton("OK");
    
    
    private T _currentObject;
    private T result = null;
    
    InfoPanel<T> rightPanel;
    
    PickerPanel<T> pickerPanel;
    
    FilterPanel<T> filterPanel;
    
    
    public T showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
    
    protected abstract Collection<T> getAvailableObjects();
    
    protected abstract Collection<PropertyDescriptor<T>> getPropertyDescriptors();
    
    protected abstract Collection<Filter<T>> getFilters();
    
    private void setSelectedObject(T value) {
    	_currentObject = value;
    	rightPanel.set_currentObject(value);
    }
    
    public AbstractPicker(JFrame parent) {
        super(parent, true);
        this.setPreferredSize(new Dimension(850,650));
        setResizable(true);
        
        rightPanel = new InfoPanel<T>(getPropertyDescriptors());
        
        this.addComponentsToPane();
    }
    
    private PickerPanel<T> setUpPickerPanel() {
    	pickerPanel = new PickerPanel<T>(getAvailableObjects());
    	
    	pickerPanel.addEventListener(e -> setSelectedObject(e));
    	
    	return pickerPanel;
    }
    
    private FilterPanel<T> setUpFilterPanel() {    	
    	filterPanel = new FilterPanel<T>(getFilters());
    	
    	filterPanel.addEventListener(newFilters -> pickerPanel.applyFilters(newFilters));

        return filterPanel;
    }
     
    
    private void addComponentsToPane() {
           	
    	final Container pane = new JPanel();
    	pane.setLayout(new BorderLayout());
    	
    	final JPanel filterPanel = setUpFilterPanel();
    	pane.add(filterPanel, BorderLayout.NORTH);
    	
    	final JPanel centralPanel = new JPanel();
    	centralPanel.setLayout(new BorderLayout());
    	
        final PickerPanel<T> objectsPanel = setUpPickerPanel();
        
    	final JScrollPane scrollPane = new JScrollPane(objectsPanel);
    	
    	centralPanel.add(rightPanel, BorderLayout.EAST);
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        
        
        pane.add(centralPanel, BorderLayout.CENTER);

        
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        

        bottomPanel.add(okButton, BorderLayout.EAST);
        
        final JDialog dialog = this;

        okButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		result = _currentObject;
        		dialog.setVisible(false);
        	}
        });
        
        pane.add(bottomPanel, BorderLayout.SOUTH);
        
        final Container dialogContainer = this.getContentPane();
        dialogContainer.add(pane, BorderLayout.CENTER);
    }
    
    
}
