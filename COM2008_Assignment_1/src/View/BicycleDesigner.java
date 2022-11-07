package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domain.Bicycle;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Wheel;

public class BicycleDesigner extends JDialog {
	
	private JLabel nameFieldLabel = new JLabel("Name: ");
	private JTextField nameField = new JTextField();

	private JPanel componentsPanel = new JPanel();
	private JButton chooseFrameButton = new JButton("Frame");
	private JButton chooseWheelsButton = new JButton("Wheels");
	private JButton chooseHandlebarsButton = new JButton("Handlebars");
	
	private JPanel topPanel = new JPanel();
	
	private JPanel centralPanel = new JPanel();
	
	private JPanel optionsPanel = new JPanel();
	
	private JFrame _parent;
	
	private Frameset _currentFrameset;
	
	public void setCurrentFrameset(Frameset value) {
		_currentFrameset = value;
		
		if (_currentFrameset != null) {
			chooseFrameButton.setText(value.toUIString());
		} else {
			chooseFrameButton.setText("Frame");
		}
	}
		
	private Wheel _currentWheels;
	
	public void setCurrentWheels(Wheel value) {
		_currentWheels = value;
		
		if (_currentWheels != null) {
			chooseWheelsButton.setText(value.toUIString());
		} else {
			chooseWheelsButton.setText("Wheels");
		}
	}
	
	private Handlebar _currentHandlebars;
	
	public void setCurrentHandlebars(Handlebar value) {
		_currentHandlebars = value;
		
		if (_currentHandlebars != null) {
			chooseHandlebarsButton.setText(value.toUIString());
		} else {
			chooseHandlebarsButton.setText("Handlebars");
		}
	}
	
	public BicycleDesigner(JFrame parent) {
		super(parent);
		_parent = parent;
		addControls();
		
	}
	
	private Bicycle result;
	
    public static Bicycle designBicycle(JFrame parent) {
    	BicycleDesigner DesignerWindow = new BicycleDesigner(parent);
    	
    	DesignerWindow.setTitle("Bicycle Designer");
    	
    	return DesignerWindow.showDesignerDialog();  	
    }
    
    public Bicycle showDesignerDialog() {
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        
        return result;
    }
	
	private void addControls() {
		this.setPreferredSize(new Dimension(500,400));
		final Container pane = this.getContentPane();
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(nameFieldLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);

		
		chooseFrameButton.addActionListener(e -> setCurrentFrameset(FramesetPicker.chooseFrameset(_parent)));
		chooseWheelsButton.addActionListener(e -> setCurrentWheels(WheelPicker.chooseWheels(_parent)));
		chooseHandlebarsButton.addActionListener(e -> setCurrentHandlebars(HandlebarPicker.chooseHandlebar(_parent)));

		
		
		componentsPanel.setLayout(new FlowLayout());
		Dimension buttonDimensions = new Dimension(150,100);
		chooseFrameButton.setPreferredSize(buttonDimensions);
		chooseWheelsButton.setPreferredSize(buttonDimensions);
		chooseHandlebarsButton.setPreferredSize(buttonDimensions);
		componentsPanel.add(chooseFrameButton);
		componentsPanel.add(chooseWheelsButton);
		componentsPanel.add(chooseHandlebarsButton);
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(namePanel, BorderLayout.NORTH);
		topPanel.add(componentsPanel, BorderLayout.CENTER);
		
		topPanel.setBackground(Color.red);
		centralPanel.setBackground(Color.blue);
		optionsPanel.setBackground(Color.green);
		
		pane.add(topPanel, BorderLayout.NORTH);
		pane.add(centralPanel, BorderLayout.CENTER);
		pane.add(optionsPanel, BorderLayout.SOUTH);
		
	}
	
	
}
