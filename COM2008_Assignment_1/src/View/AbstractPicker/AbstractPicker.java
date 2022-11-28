package View.AbstractPicker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import Domain.IToUIString;

public abstract class AbstractPicker<T extends IToUIString> extends JDialog {
    JComboBox<Boolean> shocksComboBox;
    JButton okButton = new JButton("OK");
    Boolean isStaffMode;
    
    private T _currentObject;
    private T result = null;
    
    InfoPanel<T> infoPanel;
    
    PickerPanel<T> pickerPanel;
    
    FilterPanel<T> filterPanel;
    
    StaffPanel<T> staffPanel;
    
    
	protected abstract Boolean updateComponent(T Object);
	
	protected abstract Boolean deleteComponent(T Object) throws SQLIntegrityConstraintViolationException;
	
    
    
    public T showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
    
    /*
     * Gets the objects from the implementing class to choose from
     */
    protected abstract Collection<T> getAvailableObjects();
    
    /*
     * Get the a list of PropertyDescriptor<T> from the implementing class to display. Each PropertyDescriptor<T> becomes a row in the right hand panel.
     */
    protected abstract Collection<PropertyDescriptor<T>> getPropertyDescriptors();
    
    /*
     * Gets the filters that can be applied to T.
     * each Filter<T> becomes a JComboBox dropdown in the filter panel at the top
     * Filter objects may be of T, or any parent class of type T
     * For example: you can add the costFilter of type Filter<BicycleComponent> to a list of Filter<Wheel> because Wheel extends BicycleComponent
     */
    protected abstract Collection<Filter<? super T>> getFilters();
    
    /*
     * Event handler that is called whenever PickerPanel changes event
     */
    private void setSelectedObject(T value) {
    	_currentObject = value;
    	infoPanel.set_currentObject(value);
    	if (isStaffMode) {
    		staffPanel.set_currentObject(value);
    	}
    }
    
    
    public AbstractPicker(JFrame parent, Boolean isStaffMode) {
        super(parent, true);
        this.setPreferredSize(new Dimension(850,650));
        setResizable(true);
        
        this.isStaffMode = isStaffMode;
        
        this.addComponentsToPane();
    }
    
    private PickerPanel<T> setUpPickerPanel() {
    	final PickerPanel<T> pickerPanel = new PickerPanel<T>();
    	pickerPanel.setLoadingMode();
    	
    	AvailableObjectsLoader<T> loader = new AvailableObjectsLoader<T>(this::getAvailableObjects);
    	loader.addCompletedListener(pickerPanel::set_objects);
    	loader.execute();
    	
    	pickerPanel.addEventListener(e -> setSelectedObject(e));
    	
    	return pickerPanel;
    }
    
    private FilterPanel<T> setUpFilterPanel() {    	
    	filterPanel = new FilterPanel<T>();
    	AvailableObjectsLoader<Filter<? super T>> loader = new AvailableObjectsLoader<Filter<? super T>>(this::getFilters);
    	loader.addCompletedListener(filterPanel::setFilters);
    	
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
    	
        pickerPanel = setUpPickerPanel();
        
    	final JScrollPane scrollPane = new JScrollPane(pickerPanel);
    	
    	infoPanel = new InfoPanel<T>(getPropertyDescriptors());
    	
    	JPanel rightPanel = new JPanel(new BorderLayout());
    	rightPanel.add(infoPanel, BorderLayout.CENTER);
    	
    	staffPanel = new StaffPanel<T>(this::updateComponent, this::deleteComponent, this::refreshPickerPanel);
    	
        if (isStaffMode) {
        	rightPanel.add(staffPanel, BorderLayout.SOUTH);
    	}
    	
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
    
    private void refreshPickerPanel() {
    	pickerPanel.set_objects(getAvailableObjects());
    }
    
    
}
