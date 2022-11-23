package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Bicycle;
import Domain.Customer;
import Domain.Frameset;
import Domain.Gearset;
import Domain.Order;
import Domain.OrderStatus;
import Domain.TyreType;

public class OrderOperations {
	public static Collection<Order> getAllOrders() {
		
		String sql = """				
SELECT * FROM Orders;
""";
		
		
		Collection<Order> Orders;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Orders = new ArrayList<Order>();
			
			while (rs.next()) {
				int orderNum = rs.getInt("order_number");
				Customer cust = CustomerOperations.getCustomer(rs.getInt("customer_id"));
			    String customerGivenName = rs.getString("customer_given_name");
			    double cost = rs.getDouble("cost");
			    OrderStatus os = OrderStatus.valueOf((rs.getString("order_status")).toUpperCase());
			    Bicycle bike = BicycleOperations.getBike(rs.getInt("bike_id"));
			    int serial_number = rs.getInt("serial_number");
			    String date = rs.getString("order_date");

			    Order retrieved_order = new Order(orderNum, cust, customerGivenName, cost, os, bike, serial_number, date);
			   
			    Orders.add(retrieved_order);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Orders;
		
	}
}
