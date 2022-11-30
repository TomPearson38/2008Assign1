package View.BicycleDesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Domain.Bicycle;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.Wheel;
import View.FramesetPicker;
import View.HandlebarPicker;
import View.WheelPicker;

public class BicycleDesignerPanel extends JPanel {
	private JLabel nameFieldLabel = new JLabel("Name: ");
	private JTextField nameField = new JTextField();

	private JPanel componentsPanel = new JPanel();
	private JButton chooseFrameButton = new JButton("Frame");
	private JButton chooseWheelsButton = new JButton("Wheels");
	private JButton chooseHandlebarsButton = new JButton("Handlebars");
	
	private JPanel topPanel = new JPanel();
	
	private BicycleVisualisationPanel centralPanel = new BicycleVisualisationPanel();
	
	private Frameset _currentFrameset;
	private Wheel _currentWheels;
	private Handlebar _currentHandlebars;
	
	
	public Frameset get_currentFrameset() {
		return _currentFrameset;
	}
	public Wheel get_currentWheels() {
		return _currentWheels;
	}
	public Handlebar get_currentHandlebars() {
		return _currentHandlebars;
	}
	public String getName() {
		return nameField.getText();
	}
	
	public boolean isDesignValid() {
		return _currentFrameset != null && _currentWheels != null && _currentHandlebars != null && nameField.getText().length() != 0;
	}
	public Collection<Consumer<Boolean>> designValidityListeners = new ArrayList<Consumer<Boolean>>();
	public void addDesignValidityListener(Consumer<Boolean> listener) { designValidityListeners.add(listener); }
	public void removeDesignValidityListener(Consumer<Boolean> listener) { designValidityListeners.remove(listener); }
	private void broadcastDesignValidityChange() {
		final boolean designValidity = isDesignValid();
		designValidityListeners.forEach(x -> x.accept(designValidity));
	}

	private JFrame _parent;
	
	public BicycleDesignerPanel(JFrame _parent) {
		super();
		this._parent = _parent;
		addControls();
	}
	
	
	
	public void setCurrentFrameset(Frameset value) {
		_currentFrameset = value;
		
		if (_currentFrameset != null) {
			chooseFrameButton.setText(value.toUIString());
			centralPanel.setFramesetSprite(DefaultSprites.getDefaultFramesetSprite());
		} else {
			chooseFrameButton.setText("Frame");
			centralPanel.setFramesetSprite(null);
		}
		broadcastDesignValidityChange();
	}
		
	
	
	public void setCurrentWheels(Wheel value) {
		_currentWheels = value;
		
		if (_currentWheels != null) {
			chooseWheelsButton.setText(value.toUIString());
			centralPanel.setWheelSprite(DefaultSprites.getDefaultWheelSprite());
		} else {
			chooseWheelsButton.setText("Wheels");
			centralPanel.setWheelSprite(null);
		}
		setNameOfBike();
		broadcastDesignValidityChange();
	}
	
	
	
	public void setCurrentHandlebars(Handlebar value) {
		_currentHandlebars = value;
		
		if (_currentHandlebars != null) {
			chooseHandlebarsButton.setText(value.toUIString());
			centralPanel.setHandlebarSprite(DefaultSprites.getDefaultHandlebarSprite());
		} else {
			chooseHandlebarsButton.setText("Handlebars");
			centralPanel.setHandlebarSprite(null);
		}
		setNameOfBike();
		broadcastDesignValidityChange();
	}
	
	private void setNameOfBike() {
		//Generates name for bike
		if(_currentFrameset != null && _currentWheels != null) {
			nameField.setText("" + _currentFrameset.getBrandName() + " " + _currentWheels.get_tyre().toString());
		}
	}

	private void addControls() {
		this.setPreferredSize(new Dimension(500,400));
		this.setLayout(new BorderLayout());
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(nameFieldLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  broadcastDesignValidityChange();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  broadcastDesignValidityChange();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  broadcastDesignValidityChange();
			  }
			});
		
		nameField.setEditable(false);
		
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
				
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centralPanel, BorderLayout.CENTER);
		
	}
}
