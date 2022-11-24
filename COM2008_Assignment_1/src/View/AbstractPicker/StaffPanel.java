package View.AbstractPicker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import View.FramesetPicker;


class StaffPanel<T> extends JPanel {
	private T _currentObject;
	
	private GridLayout staffPanelLayout = new GridLayout(1,2);
	
	JButton editButton;
	JButton deleteButton;
	
	public StaffPanel(Function<T, Boolean> updateComponent, Function<T, Boolean> deleteComponent) {
		super();
		this.setLayout(staffPanelLayout);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		editButton = new JButton("Edit");
		editButton.addActionListener(e -> updateComponent.apply(_currentObject)); //TODO call functions that have been passed in
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(e -> deleteComponent.apply(_currentObject));
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		this.add(editButton);
		this.add(deleteButton);
		
		
	}

	public void set_currentObject(T _currentObject) {
		this._currentObject = _currentObject;
		
		//Activate Staff buttons once an object is selected
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}	
}