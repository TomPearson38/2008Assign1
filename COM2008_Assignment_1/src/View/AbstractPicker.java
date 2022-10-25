package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import Domain.IToUIString;

public abstract class AbstractPicker<T extends IToUIString> extends JDialog {
    JComboBox<Boolean> shocksComboBox;
    JButton okButton = new JButton("OK");
    FlowLayout controlsLayout = new FlowLayout(FlowLayout.RIGHT);
    
    
    private T _currentObject;
    private T result = null;
    
    InfoPanel rightPanel;
    
    
    public T showDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
    
    protected abstract Collection<T> getAvailableObjects();
    
    protected abstract Collection<PropertyDescriptor<T>> getPropertyDescriptors();
    
    private void setSelectedObject(T value) {
    	_currentObject = value;
    	rightPanel.set_currentObject(value);
    }
    
    public AbstractPicker(JFrame parent) {
        super(parent, true);
        this.setPreferredSize(new Dimension(850,650));
        setResizable(true);
        
        rightPanel = new InfoPanel(getPropertyDescriptors());
        
        this.addComponentsToPane();
    }
    
    private void initComboboxes() {        
        Boolean shocksValues[] = {true, false};
        shocksComboBox = new JComboBox<Boolean>(shocksValues);
    }
    
    private JPanel setUpPickerPanel() {
    	final JPanel pickerPanel = new JPanel();

//        framesPanel.setPreferredSize(new Dimension(560, 600));
        pickerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        
        Collection<T> objects = getAvailableObjects();
        for (T object : objects) {
          JButton newButton = new JButton(object.toUIString());
          newButton.setPreferredSize(new Dimension(180,180));
          
          newButton.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  setSelectedObject(object);
              }
          });
          
          pickerPanel.add(newButton);
        }
        
        return pickerPanel;
    }
    
    private JPanel setUpControlsPanel() {
    	JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(controlsLayout);
        controlsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        initComboboxes();
        
        controlsPanel.add(new Label("Shocks: "));
        
        controlsPanel.add(shocksComboBox);
        
        
       

        
        return controlsPanel;
    }
    
    
    private void addComponentsToPane() {
           	
    	final Container pane = new JPanel();
    	pane.setLayout(new BorderLayout());
    	
    	
    	
    	final JPanel centralPanel = new JPanel();
    	centralPanel.setLayout(new BorderLayout());
    	
        final JPanel objectsPanel = setUpPickerPanel();
        objectsPanel.setLayout(new GridLayout(0,3));
    	final JScrollPane scrollPane = new JScrollPane(objectsPanel);
    	
    	centralPanel.add(rightPanel, BorderLayout.EAST);
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        
        
        pane.add(centralPanel, BorderLayout.CENTER);

        
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        
        final JPanel controlsPanel = setUpControlsPanel();
        bottomPanel.add(controlsPanel, BorderLayout.CENTER);
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
    
    public class PropertyDescriptor<T> {
    	public String label;
		public Function<T, String> propertySelector;
		
		public PropertyDescriptor(String label, Function<T, String> propertySelector) {
			super();
			this.label = label;
			this.propertySelector = propertySelector;
		}
    }
    
    class InfoPanel extends JPanel {
    	private T _currentObject;
    	
    	private BoxLayout infoPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    	
    	private Collection<LabelAndValue<T>> LabelsAndValues = new ArrayList<LabelAndValue<T>>();
    	
    	public InfoPanel(Collection<PropertyDescriptor<T>> descriptors) {
			super();
			this.setLayout(infoPanelLayout);
			
			this.setPreferredSize(new Dimension(200, getPreferredSize().height));
			
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			for (PropertyDescriptor<T> d : descriptors) {
				LabelAndValue<T> newDescriptorLabelAndValue = new LabelAndValue<T>(d);
				LabelsAndValues.add(newDescriptorLabelAndValue);
				this.add(newDescriptorLabelAndValue);
			}
		}

		
    	
    	public T get_currentObject() {
    		return _currentObject;
    	}
    	
    	public void set_currentObject(T value) {
    		_currentObject = value;
    		
    		for (LabelAndValue<T> l : LabelsAndValues) {
    			l.objectChanged(value);
    		}
    	}
    	
    	class LabelAndValue<T> extends JPanel implements ObjectChangedListener<T> {
    		JLabel label;
    		JLabel content;
    		
    		Function<T, String> propertySelector;
    		
    		public LabelAndValue(PropertyDescriptor<T> backingInfo) {
    			label = new JLabel(backingInfo.label + ": ");
    			content = new JLabel("");
    			propertySelector = backingInfo.propertySelector;
    			
    			this.add(label);
    			this.add(content);
    			
    			this.setAlignmentY(Component.LEFT_ALIGNMENT);
    		}
    		
    		public void setText(String value) {
    			content.setText(value);
    			
    			
    		}

			@Override
			public void objectChanged(T newObject) {
				content.setText(propertySelector.apply(newObject));
				
			}
    	}
    	
    	interface ObjectChangedListener<T> {
    		void objectChanged(T newObject);
    	}
    	
    	
    }


}
