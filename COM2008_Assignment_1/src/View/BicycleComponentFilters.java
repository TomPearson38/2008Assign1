package View;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import Domain.BicycleComponent;
import Domain.ICost;
import View.AbstractPicker.Filter;
import View.AbstractPicker.FilterValue;

public class BicycleComponentFilters {
	public static Filter<ICost> getCostFilter() {
		FilterValue<ICost> lessThan500Pounds = new FilterValue<ICost>("< £500", component -> component.getCost() < 500);
		FilterValue<ICost> from500To1000Pounds = new FilterValue<ICost>("£500 - £1000", component -> component.getCost() > 500 && component.getCost() < 1000);
		FilterValue<ICost> greaterThan1000Pounds = new FilterValue<ICost>("> £1000", component -> component.getCost() > 500);

		Filter<ICost> costFilter = new Filter<ICost>("Cost",  Arrays.asList(lessThan500Pounds, from500To1000Pounds, greaterThan1000Pounds));
		return costFilter;
	}
	
	public static Filter<BicycleComponent> getBrandFilter(Collection<String> brands) {
		Collection<FilterValue<BicycleComponent>> brandFilters = brands.stream().map(brand -> new FilterValue<BicycleComponent>(brand, component -> component.getBrandName().equals(brand))).collect(Collectors.toList());
		
		return new Filter<BicycleComponent>("Brand", brandFilters);
	}
}
