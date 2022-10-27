package View.AbstractPicker;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	
	interface ObjectChangedListener<T> {
		void objectChanged(T newObject);
	}
	
	
}