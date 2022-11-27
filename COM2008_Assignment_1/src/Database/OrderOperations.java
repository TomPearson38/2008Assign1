package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public final static String order_number = "order_number";
	public final static String customer_id = "customer_id";
	public final static String given_name = "given_name";
	public final static String cost = "original_cost";
	public final static String order_status = "order_status";
	public final static String bike_id = "bike_id";
	public final static String serial_number = "order_serial_number";
	public final static String order_date = "order_date";
	
	public final static String column_string = 
			"Orders.order_number AS " + order_number + 
			", Orders.customer_id AS " + customer_id + 
			", Orders.customer_given_name AS " + given_name + 
			", Orders.cost AS " + cost +
			", Orders.order_status AS " + order_status +
			", Orders.bike_id AS " + bike_id + 
			", Orders.serial_number AS " + serial_number +
			", Orders.order_date AS " + order_date;
													
	public static Order parseOrderFromResultSet(ResultSet rs) throws SQLException, EnumMappingException {
		int orderNum = rs.getInt(OrderOperations.order_number);
		
	    String customerGivenName = rs.getString(OrderOperations.given_name);
	    double cost = rs.getDouble(OrderOperations.cost);
	    OrderStatus os = OrderStatus.valueOf((rs.getString(OrderOperations.order_status)).toUpperCase());
	    int serial_number = rs.getInt(OrderOperations.serial_number);
	    String date = rs.getString(OrderOperations.order_date);
	    

	    Customer retrieved_customer = CustomerOperations.parseCustomerFromResultset(rs);
	    
	    Bicycle retrieved_bike = BicycleOperations.parseBicycleFromResultset(rs);

	    return new Order(orderNum, retrieved_customer, customerGivenName, cost, os, retrieved_bike, serial_number, date);
	}
	public static Collection<Order> getAllOrders() {
		
		String sql = 				
"SELECT " + OrderOperations.column_string + ", " + CustomerOperations.column_string + ", " + AddressOperations.column_string + ", " + BicycleOperations.column_string + ", " + FrameOperations.column_string + ", " + GearsetOperations.column_string + ", " +  HandlebarOperations.column_string + ", " + WheelOperations.column_string + " " + 
"FROM Orders " +
"LEFT JOIN Customers " +
"ON Orders.customer_id = Customers.id " +
"LEFT JOIN Addresses " +
"ON Customers.address_id = Addresses.id " +
"LEFT JOIN Bicycles " +
"ON Orders.bike_id = Bicycles.id " + 
"LEFT JOIN Frames " +
"ON Bicycles.frameset_id = Frames.id " +
"LEFT JOIN Gearsets " +
"ON Frames.gears_id = Gearsets.id " + 
"LEFT JOIN Handlebars " +
"ON Bicycles.handlebar_id = Handlebars.id " +
"LEFT JOIN Wheels " +
"ON Bicycles.wheels_id = Wheels.id;";
		System.out.println(sql);
		
		Collection<Order> Orders;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Orders = new ArrayList<Order>();
			
			while (rs.next()) {
				
				Order retrieved_order = parseOrderFromResultSet(rs);
			   
			    Orders.add(retrieved_order);			   
			                    
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Orders;
		
	}
	
	public static Order getOrder(int idNum) {
		String sql = 				
"SELECT " + OrderOperations.column_string + ", " + CustomerOperations.column_string + ", " + AddressOperations.column_string + ", " + BicycleOperations.column_string + ", " + FrameOperations.column_string + ", " + GearsetOperations.column_string + ", " +  HandlebarOperations.column_string + ", " + WheelOperations.column_string + " " + 
"FROM Orders " +
"LEFT JOIN Customers " +
"ON Orders.customer_id = Customers.id  AND Orders.order_number=? " +
"LEFT JOIN Addresses " +
"ON Customers.address_id = Addresses.id " +
"LEFT JOIN Bicycles " +
"ON Orders.bike_id = Bicycles.id " + 
"LEFT JOIN Frames " +
"ON Bicycles.frameset_id = Frames.id " +
"LEFT JOIN Gearsets " +
"ON Frames.gears_id = Gearsets.id " + 
"LEFT JOIN Handlebars " +
"ON Bicycles.handlebar_id = Handlebars.id " +
"LEFT JOIN Wheels " +
"ON Bicycles.wheels_id = Wheels.id;";


		Order currentOrder = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			statement.setInt(1, idNum);
			System.out.println(statement.toString());

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				currentOrder = parseOrderFromResultSet(rs);
			}

			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return currentOrder;	
	}
}
