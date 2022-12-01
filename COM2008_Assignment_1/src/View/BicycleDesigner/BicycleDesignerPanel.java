package View.BicycleDesigner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
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
import Database.BicycleOperations;
import Database.BicycleOperations.CreateBicycleRequest;
import Domain.Bicycle;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import Domain.TyreType;
import Domain.Wheel;
import View.Pickers.FramesetPicker;
import View.Pickers.HandlebarPicker;
import View.Pickers.WheelPicker;
import View.UserControls.JLimitedTextField;

public class BicycleDesignerPanel extends JPanel {
	private JLabel nameFieldLabel = new JLabel("Name: ");
	final static int nameFieldLimit = 40;
	private JLimitedTextField nameField = new JLimitedTextField(nameFieldLimit);

	private JPanel componentsPanel = new JPanel();
	private JButton chooseFrameButton = new JButton("Frame");
	private JButton chooseWheelsButton = new JButton("Wheels");
	private JButton chooseHandlebarsButton = new JButton("Handlebars");
	
	private JPanel topPanel = new JPanel();
	
	private BicycleVisualisationPanel centralPanel = new BicycleVisualisationPanel();
	
	private Bicycle savedDesign;
	private void setSavedDesign(Bicycle value) {
		savedDesign = value;
		
		broadcastisNewDesignChange();
	}
	private ActiveBicycleDesign currentDesign = new ActiveBicycleDesign(null, null, null, "");
	private void setCurrentDesign(ActiveBicycleDesign value) {
		currentDesign = value;
		
		setCurrentFrameset(currentDesign.getFrame());
		setCurrentHandlebars(currentDesign.getHandlebars());
		setCurrentWheels(currentDesign.getWheels());
		nameField.setText(currentDesign.getName());
	}
	
	
	public BicycleOperations.CreateBicycleRequest generateBicycleCreateRequest() {
		return currentDesign.generateBicycleCreateRequest();
	}
	
	public Bicycle getSavedBicycle() throws DesignNotSavedException {
		if (!isDesignSaved()) {
			throw new DesignNotSavedException("Design domain object cannot be accessed until the design has been saved to the database");
		} else {
			return savedDesign;
		}
	}
	public class DesignNotSavedException extends Exception { 
		public DesignNotSavedException(String message) { super(message); } 
	}
	
	public void setDesign(Bicycle domainObjectToUse) {
		setSavedDesign(domainObjectToUse);
		setCurrentDesign(new ActiveBicycleDesign(domainObjectToUse));
		
		
		broadcastDesignSavedChange();
	}

	public boolean isNewDesign() {
		return savedDesign == null;
	}
	public Collection<Consumer<Boolean>> isDesignNewListeners = new ArrayList<Consumer<Boolean>>();
	public void addIsNewDesignListener(Consumer<Boolean> listener) { isDesignNewListeners.add(listener); }
	public void removeIsNewDesignListener(Consumer<Boolean> listener) { isDesignNewListeners.remove(listener); }
	private void broadcastisNewDesignChange() {
		final boolean isNewDesign = isNewDesign();
		isDesignNewListeners.forEach(x -> x.accept(isNewDesign));
	}
	
	public Bicycle getUpdatedVersionOfOriginalDomainObject() {
		if (!isNewDesign()) {
			return currentDesign.transformDomainObject(savedDesign);
		} else {
			return null;
		}
	}
	
	/*
	 * Has the current design been saved to the database
	 */
	public boolean isDesignSaved() {
		//if the current design is the same as the original design then it hasn't been changed since the save
		return currentDesign.matches(savedDesign);
	}
	public Collection<Consumer<Boolean>> designSavedListeners = new ArrayList<Consumer<Boolean>>();
	public void addDesignSavedListener(Consumer<Boolean> listener) { designSavedListeners.add(listener); }
	public void removeDesignSavedListener(Consumer<Boolean> listener) { designSavedListeners.remove(listener); }
	private void broadcastDesignSavedChange() {
		final boolean designSaved = isDesignSaved();
		designSavedListeners.forEach(x -> x.accept(designSaved));
	}
	
	
	
	public boolean isDesignValid() {
		return currentDesign.isDesignValid();
	}
	public Collection<Consumer<Boolean>> designValidityListeners = new ArrayList<Consumer<Boolean>>();
	public void addDesignValidityListener(Consumer<Boolean> listener) { designValidityListeners.add(listener); }
	public void removeDesignValidityListener(Consumer<Boolean> listener) { designValidityListeners.remove(listener); }
	private void broadcastDesignValidityChange() {
		final boolean designValidity = isDesignValid();
		designValidityListeners.forEach(x -> x.accept(designValidity));
	}

	private Window _owner;
	
	public BicycleDesignerPanel(Window owner) {
		super();
		this._owner = owner;
		
		
		addControls();
	}

	private void setCurrentFrameset(Frameset value) {
		currentDesign.setFrame(value);
		
		if (value != null) {
			chooseFrameButton.setText(value.toUIString());
			centralPanel.setFramesetSprite(DefaultSprites.getDefaultFramesetSprite());
		} else {
			chooseFrameButton.setText("Frame");
			centralPanel.setFramesetSprite(null);
		}
		broadcastDesignValidityChange();
		broadcastDesignSavedChange();
	}
		
	
	
	private void setCurrentWheels(Wheel value) {
		currentDesign.setWheels(value);
		
		if (value != null) {
			chooseWheelsButton.setText(value.toUIString());
			if (value.get_tyre() == TyreType.ROAD) {
				centralPanel.setWheelSprite(DefaultSprites.getRoadWheelSprite());
			}
			else if (value.get_tyre() == TyreType.HYBRID) {
				centralPanel.setWheelSprite(DefaultSprites.getHyrbidWheelSprite());
			}
			else {
				centralPanel.setWheelSprite(DefaultSprites.getOffroadWheelSprite());
			}
		} else {
			chooseWheelsButton.setText("Wheels");
			centralPanel.setWheelSprite(null);
		}
		broadcastDesignValidityChange();
		broadcastDesignSavedChange();
	}
	
	
	
	private void setCurrentHandlebars(Handlebar value) {
		currentDesign.setHandlebars(value);
		
		if (value != null) {
			chooseHandlebarsButton.setText(value.toUIString());
			if (value.get_style() == HandlebarStyles.HIGH) {
				centralPanel.setHandlebarSprite(DefaultSprites.getHighHandlebarsSprite());
			}
			else if (value.get_style() == HandlebarStyles.DROPPED) {
				centralPanel.setHandlebarSprite(DefaultSprites.getDroppedHandlebarsSprite());
			}
			else {
				centralPanel.setHandlebarSprite(DefaultSprites.getDefaultHandlebarsSprite());
			}
		} else {
			chooseHandlebarsButton.setText("Handlebars");
			centralPanel.setHandlebarSprite(null);
		}
		broadcastDesignValidityChange();
		broadcastDesignSavedChange();
	}
	
	public void setBicycleName(String value) {
		nameField.setText(value);
	}

	private void addControls() {
		this.setPreferredSize(new Dimension(500,400));
		this.setLayout(new BorderLayout());
		
		setSavedDesign(null);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(nameFieldLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  updateViewModel(e);
			  }
			  public void removeUpdate(DocumentEvent e) {
				  updateViewModel(e);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  updateViewModel(e);
			  }
			  private void updateViewModel(DocumentEvent e) {
				  String newText = nameField.getText();
				  currentDesign.setName(newText);
				  broadcastDesignValidityChange();
				  broadcastDesignSavedChange();
			  }
			});
		
		
		chooseFrameButton.addActionListener(e -> setCurrentFrameset(FramesetPicker.chooseFrameset(_owner)));
		chooseWheelsButton.addActionListener(e -> setCurrentWheels(WheelPicker.chooseWheels(_owner)));
		chooseHandlebarsButton.addActionListener(e -> setCurrentHandlebars(HandlebarPicker.chooseHandlebar(_owner)));
		
		
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
	

	
	public class ActiveBicycleDesign {
		private Frameset frame;
		private Handlebar handlebars;
		private Wheel wheels;
		private String name;
		
		public ActiveBicycleDesign(Bicycle domainObject) {
			this(domainObject.get_frame(), domainObject.get_handlebar(), domainObject.get_Wheels(), domainObject.getCustomerGivenName());
		}
		
		public ActiveBicycleDesign(Frameset frame, Handlebar handlebars, Wheel wheels, String name) {
			super();
			this.frame = frame;
			this.handlebars = handlebars;
			this.wheels = wheels;
			this.name = name;
		}


		public Frameset getFrame() {
			return frame;
		}

		public Handlebar getHandlebars() {
			return handlebars;
		}

		public Wheel getWheels() {
			return wheels;
		}

		public String getName() {
			return name;
		}

		public void setFrame(Frameset frame) {
			this.frame = frame;
		}

		public void setHandlebars(Handlebar handlebars) {
			this.handlebars = handlebars;
		}

		public void setWheels(Wheel wheels) {
			this.wheels = wheels;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		public boolean isDesignValid() {
			return frame != null && handlebars != null && wheels != null && name.length() != 0;
		}
		
		public CreateBicycleRequest generateBicycleCreateRequest() {
			return new CreateBicycleRequest(frame, handlebars, wheels, name);
		}
		
		public Bicycle transformDomainObject(Bicycle domainObject) {
			Bicycle newDomainObject = new Bicycle(domainObject.get_id(), frame, handlebars, wheels, name);
			return newDomainObject;
		}
		
		
		public boolean matches(Bicycle domainObject) {
			if (domainObject == null) {
				return false;
			}
			if (frame == null || handlebars == null || wheels == null) {	//the domain object can never have null values for these so if we check them here we can call equals() on them next
				return false;
			}
			return  domainObject.get_frame().equals(frame) &&
					domainObject.get_handlebar().equals(handlebars) &&
					domainObject.get_Wheels().equals(wheels) &&
					domainObject.getCustomerGivenName().equals(name);
		}
		
	}
}
