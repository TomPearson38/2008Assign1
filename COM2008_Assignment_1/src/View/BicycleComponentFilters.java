package View;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import Domain.BicycleComponent;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;

public class BicycleComponentFilters {
	public static Filter<BicycleComponent> getCostFilter() {
		FilterValue<BicycleComponent> lessThan500Pounds = new FilterValue<BicycleComponent>("< £500", component -> component.Cost() < 500);
		FilterValue<BicycleComponent> from500To1000Pounds = new FilterValue<BicycleComponent>("£500 - £1000", component -> component.Cost() > 500 && component.Cost() < 1000);
		FilterValue<BicycleComponent> greaterThan1000Pounds = new FilterValue<BicycleComponent>("> £1000", component -> component.Cost() > 500);

		Filter<BicycleComponent> costFilter = new Filter<BicycleComponent>("Cost",  Arrays.asList(lessThan500Pounds, from500To1000Pounds, greaterThan1000Pounds));
		return costFilter;
	}
	
	public static Filter<BicycleComponent> getBrandFilter(Collection<String> brands) {
		Collection<FilterValue<BicycleComponent>> brandFilters = brands.stream().map(brand -> new FilterValue<BicycleComponent>(brand, component -> component.BrandName().equals(brand))).collect(Collectors.toList());
		
		return new Filter<BicycleComponent>("Brand", brandFilters);
	}
}
