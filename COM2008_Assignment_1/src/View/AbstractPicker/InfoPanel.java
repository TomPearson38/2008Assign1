package View.AbstractPicker;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/*
 * Displays information about an object T in a vertical layout with rows
 * @param <T> the type of the object
 */
class InfoPanel<T> extends JPanel {
	private T _currentObject;
	
	private BoxLayout infoPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	private Collection<LabelAndValue<? super T>> LabelsAndValues = new ArrayList<LabelAndValue<? super T>>();
	
	/*
	 * @param descriptors the descriptors for the object, each descriptor correspons to a row
	 */
	public InfoPanel(Collection<PropertyDescriptor<? super T>> descriptors) {
		super();
		this.setLayout(infoPanelLayout);
		
		this.setPreferredSize(new Dimension(200, getPreferredSize().height));
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		for (PropertyDescriptor<? super T> d : descriptors) {
			LabelAndValue<? super T> newDescriptorLabelAndValue = new LabelAndValue<T>(d);
			LabelsAndValues.add(newDescriptorLabelAndValue);
			this.add(newDescriptorLabelAndValue);
		}
	}
	
    
	
	
	public T get_currentObject() {
		return _currentObject;
	}
	
	public void set_currentObject(T value) {
		_currentObject = value;
		
		for (LabelAndValue<? super T> l : LabelsAndValues) {
			l.objectChanged(value);
		}
	}
	
	interface ObjectChangedListener<T> {
		void objectChanged(T newObject);
	}
	
	
}