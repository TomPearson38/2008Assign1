package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.border.EtchedBorder;

import Database.FrameOperations;
import Database.GearsetOperations;
import Domain.Frameset;
import Domain.Gearset;

public class FramesetPicker extends JDialog {
    JComboBox<Gearset> gearsComboBox;
    JComboBox<Boolean> shocksComboBox;
    JButton applyButton = new JButton("Apply");
    FlowLayout framesLayout = new FlowLayout(FlowLayout.LEFT);
    FlowLayout controlsLayout = new FlowLayout(FlowLayout.RIGHT);
    
    
    private Frameset _currentFrameset;
    private Frameset result = null;
    

    
    InfoPanel rightPanel = new InfoPanel();
    
    public static Frameset chooseFrameset(JFrame parent) {
    	FramesetPicker PickerWindow = new FramesetPicker(parent);
    	
    	PickerWindow.setTitle("Frameset Picker");
    	
    	return PickerWindow.showFramesetDialog();  	
    }
    
    public Frameset showFramesetDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
    
    public Frameset getSelectedFrameset() {
    	return _currentFrameset;
    }
    private void setSelectedFrameset(Frameset value) {
    	_currentFrameset = value;
    	rightPanel.set_frameset(value);
    }
    
    public FramesetPicker(JFrame parent) {
        super(parent, true);
        this.setPreferredSize(new Dimension(850,650));
        setResizable(true);
        
        this.addComponentsToPane();
    }
    
    private void initComboboxes() {
    	Collection<Gearset> AllGearsets = GearsetOperations.getAllGears();
    	Gearset[] gearsets = AllGearsets.toArray(new Gearset[AllGearsets.size()]);
        gearsComboBox = new JComboBox<Gearset>(gearsets);
        
        Boolean shocksValues[] = {true, false};
        shocksComboBox = new JComboBox<Boolean>(shocksValues);
    }
    
    private JPanel setUpFramesPanel() {
    	final JPanel framesPanel = new JPanel();

//        framesPanel.setPreferredSize(new Dimension(560, 600));
        framesPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        
        Collection<Frameset> frames = FrameOperations.getAllFrames();
        for (Frameset frame : frames) {
          JButton newButton = new JButton("<html>" + frame.BrandName() + "<br>" + frame.get_size() + "cm" + "</html>");
          newButton.setPreferredSize(new Dimension(180,180));
          
          newButton.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  setSelectedFrameset(frame);
              }
          });
          
          framesPanel.add(newButton);
        }
        
        return framesPanel;
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
    	centralPanel.setBorder(BorderFactory.createLineBorder(Color.red));
    	
        final JPanel framesPanel = setUpFramesPanel();
//        framesPanel.setLayout(new GridLayout(0,3));
        framesPanel.setLayout(new GridLayout(0,3));
    	final JScrollPane scrollPane = new JScrollPane(framesPanel);
    	
    	centralPanel.add(rightPanel, BorderLayout.EAST);
    	rightPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        
        
        pane.add(centralPanel, BorderLayout.CENTER);

        
        
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        
        final JPanel controlsPanel = setUpControlsPanel();
        bottomPanel.add(controlsPanel, BorderLayout.CENTER);
        bottomPanel.add(applyButton, BorderLayout.EAST);
        
        final JDialog dialog = this;

        applyButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		result = _currentFrameset;
        		dialog.setVisible(false);
        	}
        });
        
        pane.add(bottomPanel, BorderLayout.SOUTH);
        
        final Container dialogContainer = this.getContentPane();
        dialogContainer.add(pane, BorderLayout.CENTER);
    }
    
    class InfoPanel extends JPanel {
    	private Frameset _currentFrame;
    	
    	private BoxLayout infoPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    	
    	private LabelAndValue brandNameLabel = new LabelAndValue("Brand name");
    	private LabelAndValue serialNumberLabel = new LabelAndValue("Serial number");
    	private LabelAndValue costLabel = new LabelAndValue("Cost");
    	private LabelAndValue shocksLabel = new LabelAndValue("Shocks");
    	private LabelAndValue sizeLabel = new LabelAndValue("Size");
    	
    	public InfoPanel() {
			super();
			this.setLayout(infoPanelLayout);
			
			this.setPreferredSize(new Dimension(200, getPreferredSize().height));
			
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			this.add(brandNameLabel);
			this.add(serialNumberLabel);
			this.add(costLabel);
			this.add(shocksLabel);
			this.add(sizeLabel);
		}

		
    	
    	public Frameset get_frameset() {
    		return _currentFrame;
    	}
    	
    	public void set_frameset(Frameset value) {
    		_currentFrame = value;
    		
    		brandNameLabel.setText(value.BrandName());
    		serialNumberLabel.setText(Double.toString(value.SerialNumber()));
    		costLabel.setText(Double.toString(value.Cost()));
    		shocksLabel.setText(Boolean.toString(value.get_shocks()));
    		sizeLabel.setText(Double.toString(value.get_size()));
    	}
    	
    	class LabelAndValue extends JPanel {
    		JLabel label;
    		JLabel content;
    		
    		public LabelAndValue(String labelText) {
    			this(labelText, "");
    		}
    		
    		public LabelAndValue(String labelText, String valueText) {
    			
    			label = new JLabel(labelText + ": ");
    			content = new JLabel(valueText);
    			
    			this.add(label);
    			this.add(content);
    			
    			this.setAlignmentY(Component.LEFT_ALIGNMENT);
    		}
    		
    		public void setText(String value) {
    			content.setText(value);
    			
    			
    		}
    	}
    	
    	
    }


}
