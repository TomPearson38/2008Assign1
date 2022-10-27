package View.AbstractPicker;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


class InfoPanel<T> extends JPanel {
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
	
	class LabelAndValue<T> extends JPanel implements InfoPanel.ObjectChangedListener<T> {
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