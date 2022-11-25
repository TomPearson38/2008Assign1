package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Address;
import Domain.Bicycle;
import Domain.Customer;
import Domain.Frameset;
import Domain.Gearset;
import Domain.Handlebar;
import Domain.Order;
import Domain.OrderStatus;
import Domain.TyreType;
import Domain.Wheel;

public class OrderOperations {
	public static Collection<Order> getAllOrders() {
		
		String sql = """				
SELECT Orders.order_number, Orders.customer_given_name, Orders.cost, Orders.order_status, Orders.serial_number, Orders.order_date, Customers.id AS customer_id, Customers.forename, Customers.surname, Customers.address_id, Addresses.house_num_name AS houseNumName, Addresses.street_name AS streetName, Addresses.post_code, Bicycles.id AS bicycle_id, Bicycles.frameset_id FROM Orders;
LEFT JOIN Customers
ON Orders.customer_id = Customers.id
LEFT JOIN Bicycles
ON Orders.bike_id = Bicycles.id
""";
		
		
		Collection<Order> Orders;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Orders = new ArrayList<Order>();
			
			while (rs.next()) {
				int orderNum = rs.getInt("order_number");
				int customer_id = rs.getInt("customer_id");
				
			    String customerGivenName = rs.getString("customer_given_name");
			    double cost = rs.getDouble("cost");
			    OrderStatus os = OrderStatus.valueOf((rs.getString("order_status")).toUpperCase());
			    Bicycle bike = BicycleOperations.getBike(rs.getInt("bike_id"));
			    int serial_number = rs.getInt("serial_number");
			    String date = rs.getString("order_date");
			    

			    Customer retrieved_customer = CustomerOperations.parseCustomerFromResultset(rs);
			    
			    int _id = rs.getInt("id");
				Frameset frame = FrameOperations.getFrameset(rs.getInt("frameset_id"));
				Handlebar hb = HandlebarOperations.getHandlebar(rs.getInt("handlebar_id"));
				Wheel wheels = WheelOperations.getWheel(rs.getInt("wheels_id"));
				String frameName = rs.getString("frame_name");
			   
//				foundBike = new Bicycle(_id, frame, hb, wheels, frameName);

			    Order retrieved_order = new Order(orderNum, retrieved_customer, customerGivenName, cost, os, bike, serial_number, date);
			   
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
