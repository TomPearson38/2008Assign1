package View.AbstractPicker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Domain.IToUIString;
import Resources.ResourceSingleton;

public class PickerPanel<T extends IToUIString> extends JPanel {
	
	private Collection<T> _objects;
	
	private Collection<Predicate<T>> _filters = new ArrayList<Predicate<T>>();
	
	private Collection<IPickerPanelChangeSubscriber<T>> _changeListeners = new ArrayList<IPickerPanelChangeSubscriber<T>>();
	
	public void applyFilters(Collection<Predicate<T>> predicates) {
		removeAll();
		_filters = predicates;
		setUpPickerPanel();
		validate();	//these two are necessary to prevent a hang
		repaint();
	}
	
	private Collection<T> filterObjects(Collection<T> objects, Collection<Predicate<T>> predicates) {
		return objects.stream().filter(o -> predicates.stream().allMatch(p -> p.test(o))).collect(Collectors.toList());
	}
	
	public void addEventListener(IPickerPanelChangeSubscriber<T> listener) {
		_changeListeners.add(listener);
	}

	private void broadcastChangeToEventListeners(T newObject) {
		_changeListeners.forEach(s -> s.PickerPanelChanged(newObject));
	}

	public PickerPanel() {
		super();
//		setUpPickerPanel();
	}
	
	public void setLoadingMode() {
		removeAll();
		ImageIcon loadingIcon = new ImageIcon(ResourceSingleton.getLoadingImage());
		JLabel label = new JLabel(loadingIcon);
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
	}
	
    public void set_objects(Collection<T> _objects) {
		this._objects = _objects;
		removeAll();
		setUpPickerPanel();
		revalidate();
		repaint();
	}

	private void setUpPickerPanel() {
//        framesPanel.setPreferredSize(new Dimension(560, 600));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        this.setLayout(new GridLayout(0,3));
        
        
        Collection<T> objects = filterObjects(_objects, _filters);
        for (T object : objects) {
          JButton newButton = new JButton(object.toUIString());
          newButton.setPreferredSize(new Dimension(180,180));
          
          newButton.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  broadcastChangeToEventListeners(object);
              }
          });
          
          this.add(newButton);
        }
    }
    
    public interface IPickerPanelChangeSubscriber<T> {
    	public void PickerPanelChanged(T newObject);
    }
	
}