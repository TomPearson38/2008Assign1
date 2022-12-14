package View.AbstractPicker;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

class FilterPanel<T> extends JPanel {
	private Collection<Filter<? super T>> _filters;
	
	private Collection<JComboBox<FilterValue<? super T>>> dropdowns = new ArrayList<JComboBox<FilterValue<? super T>>>();
	
	private Collection<FilterPanel.IFilterValuesChangeListener<T>> _changeListeners = new ArrayList<FilterPanel.IFilterValuesChangeListener<T>>();
	
	public void addEventListener(FilterPanel.IFilterValuesChangeListener<T> newListener) {
		_changeListeners.add(newListener);
	}
	private void broadcastChange(Collection<Predicate<T>> newFilters) {
		_changeListeners.forEach(x -> x.FilterValuesChanged(newFilters));
	}
	
	public void setFilters(Collection<Filter<? super T>> filters) {
		_filters = filters;
		setUpFilterPanel();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Predicate<T>> getActiveFilters() {
		return dropdowns.stream().<FilterValue<T>>map(d -> (FilterValue<T>)d.getSelectedItem()).<Predicate<T>>map(FilterValue::getPredicate).collect(Collectors.toList());
	}
	
	public FilterPanel() {
		super();
	}
	
    private void setUpFilterPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        final Collection<Filter<? super T>> filters = _filters;
        
    	for (Filter<? super T> f : filters) {
    		JComboBox<FilterValue<? super T>> newFilterDropdown = new JComboBox<FilterValue<? super T>>();
    		
    		FilterValue<T> allValuesFilter = new FilterValue<T>("All", t -> true);
    		newFilterDropdown.addItem(allValuesFilter);
    		
    		for (FilterValue<? super T> fv : f.filterValues) {
    			newFilterDropdown.addItem(fv);
    		}
    		
    		newFilterDropdown.addItemListener(new ItemListener() {
            	public void itemStateChanged(ItemEvent evt) {
            	    if (evt.getStateChange() == ItemEvent.SELECTED) {
            	      // Item was just selected
            	    	broadcastChange(getActiveFilters());
            	    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
            	      // Item is no longer selected
            	    }
            		
            		
            	}
            });
    		
    		this.add(new JLabel(f.filterName));
    		this.add(newFilterDropdown);
    		dropdowns.add(newFilterDropdown);
    		
    	}
    	
        
    }
    
    public interface IFilterValuesChangeListener<T> {
    	public void FilterValuesChanged(Collection<Predicate<T>> newFilters);
    }
}