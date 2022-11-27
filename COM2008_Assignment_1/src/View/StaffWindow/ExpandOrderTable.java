package View.StaffWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import Database.OrderOperations;
import Domain.Bicycle;
import Domain.BicycleComponent;
import Domain.Order;
import Domain.OrderStatus;
import View.Table.*;

public class ExpandOrderTable extends AbstractTable<PartsModelRow> {	
	@Override
	protected  List<Column<PartsModelRow, ?>> getColumns() {
		
		final Column<PartsModelRow, String> partNameColumn = new Column<PartsModelRow, String>("Part", PartsModelRow::get_name, String.class);
		
		final Column<PartsModelRow, String> brandNameColumn = new Column<PartsModelRow, String>("Brand", PartsModelRow::get_brand, String.class);

		final Column<PartsModelRow, Integer> partIdColumn = new Column<PartsModelRow, Integer>("Part ID", PartsModelRow::get_partId, Integer.class);

		final Column<PartsModelRow, Integer> partSerialNumber = new Column<PartsModelRow, Integer>("Serial Number", PartsModelRow::get_partSerialNumber, Integer.class);

		final Column<PartsModelRow, Double> partCost = new Column<PartsModelRow, Double>("Cost", PartsModelRow::get_cost, Double.class);
		
		return Arrays.asList(partNameColumn, brandNameColumn, partIdColumn, partSerialNumber, partCost);
		
	}
	
	@Override
	protected GenericAbstractTableModel<PartsModelRow> getTableModel() {
		
		final Collection<BicycleComponent> allComponents = new ArrayList<BicycleComponent>();
		allComponents.add(ExpandedBikeView.getBike().get_frame());
		allComponents.add(ExpandedBikeView.getBike().get_handlebar());
		allComponents.add(ExpandedBikeView.getBike().get_Wheels());

		
		
		return new ExpandTableModel(allComponents, getColumns());
	}
	
	
	public class ExpandTableModel extends GenericAbstractTableModel<PartsModelRow> {
		public ExpandTableModel(Collection<BicycleComponent> parts, List<Column<PartsModelRow, ?>> columns) {
			super(parts.stream().map(PartsModelRow::new).collect(Collectors.toList()), columns);
			
		}
	}	
}
