package View.AbstractPicker;

import java.awt.Component;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.JPanel;

class LabelAndValue<T> extends JPanel implements InfoPanel.ObjectChangedListener<T> {
	JLabel label;
	JLabel content;
	
	Function<? super T, String> propertySelector;
	
	public LabelAndValue(PropertyDescriptor<? super T> backingInfo) {
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