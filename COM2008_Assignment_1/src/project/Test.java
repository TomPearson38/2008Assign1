package project;

import java.util.*;

import Database.BicycleOperations;
import Database.HandlebarOperations;
import Database.OrderOperations;
import Domain.Bicycle;
import Domain.Order;
import View.ViewStartup;

public class Test {
	public static void main(String[] args){
		
		Collection<Order> orders = OrderOperations.getAllOrders();
//		Collection<Bicycle> bikes = BicycleOperations.getAllBikes();
		ViewStartup.entryPoint();	
	}
}
