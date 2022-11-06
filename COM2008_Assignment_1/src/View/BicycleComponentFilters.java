package View;

import java.util.Arrays;

import Domain.BicycleComponent;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;

public class BicycleComponentFilters {
	public static Filter<BicycleComponent> getCostFilter() {
		FilterValue<BicycleComponent> lessThan500Pounds = new FilterValue<BicycleComponent>("< £500", component -> component.Cost() < 500);
		FilterValue<BicycleComponent> from500To1000Pounds = new FilterValue<BicycleComponent>("£500 - £1000", component -> component.Cost() > 500 && component.Cost() < 1000);
		FilterValue<BicycleComponent> greaterThan1000Pounds = new FilterValue<BicycleComponent>("> £1000", component -> component.Cost() > 500);

		Filter<BicycleComponent> costFilter = new Filter<BicycleComponent>("Costt",  Arrays.asList(lessThan500Pounds, from500To1000Pounds, greaterThan1000Pounds));
		return costFilter;
	}
}
