package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Bicycle;
import Domain.Customer;
import Domain.Order;
import Domain.OrderStatus;

/**
 * Contains all the SQL operations of the Order class
 * @author tomap
 *
 */
public class OrderOperations {
	public final static String order_number = "order_number";
	public final static String customer_id = "customer_id";
	public final static String cost = "original_cost";
	public final static String order_status = "order_status";
	public final static String bike_id = "bike_id";
	public final static String serial_number = "order_serial_number";
	public final static String order_date = "order_date";
	
	public final static String column_string = 
			"Orders.order_number AS " + order_number + 
			", Orders.customer_id AS " + customer_id + 
			", Orders.cost AS " + cost +
			", Orders.order_status AS " + order_status +
			", Orders.bike_id AS " + bike_id + 
			", Orders.serial_number AS " + serial_number +
			", Orders.order_date AS " + order_date;
				
	/**
	 * Converts a results set into a Order object provided all the fields are correct
	 * @param rs Results set
	 * @return New Order Object
	 * @throws SQLException
	 * @throws EnumMappingException
	 */
	public static Order parseOrderFromResultSet(ResultSet rs) throws SQLException, EnumMappingException {
		int orderNum = rs.getInt(OrderOperations.order_number);
		
	    double cost = rs.getDouble(OrderOperations.cost);
	    OrderStatus os = OrderStatus.valueOf((rs.getString(OrderOperations.order_status)).toUpperCase());
	    int serial_number = rs.getInt(OrderOperations.serial_number);
	    String date = rs.getString(OrderOperations.order_date);
	    

	    Customer retrieved_customer = CustomerOperations.parseCustomerFromResultset(rs);
	    
	    Bicycle retrieved_bike = BicycleOperations.parseBicycleFromResultset(rs);

	    return new Order(orderNum, retrieved_customer, cost, os, retrieved_bike, serial_number, date);
	}
	
	/**
	 * Returns all orders in the database
	 * @return All orders
	 */
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
	
	/**
	 * Updates the provided list of orders in the database
	 * @param ordersToUpdate List of orders to update
	 */
	public static void updateOrders(Collection<Order> ordersToUpdate) {
		try {
			Connection connection = ConnectionManager.getConnection();
			
			connection.setAutoCommit(false);
			
			for (Order order : ordersToUpdate) {
				updateOrder(order, false);
			}
			
			connection.commit();
			
			connection.setAutoCommit(true);
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Used to convert order status ENUM to string
	 * @param status Order status to convert
	 * @return Converted string
	 */
	private static String mapOrderStatusToDBEnum(OrderStatus status) {
		switch (status) {
		case PENDING:
			return "pending";
		case CONFIRMED:
			return "confirmed";
		case FULFILLED:
			return "fulfilled";
		default:
			return null;
		}
	}
	
	/**
	 * Gets provided order from the database based upon the id number
	 * @param idNum ID to lookup
	 * @return Order object found, null if none found
	 */
	public static Order getOrder(int idNum) {
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
"ON Bicycles.wheels_id = Wheels.id WHERE Orders.order_number=?;";


		Order currentOrder = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			statement.setInt(1, idNum);


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

	
	/**
	 * Reduced order to update call
	 * Updates the provided order object in the database
	 * @param orderToUpdate 
	 * @return successful?
	 */
	public static boolean updateOrder(Order orderToUpdate) {
		return updateOrder(orderToUpdate, true);
	}
	
	/**
	 * Updates the provided order object in the database
	 * @param orderToUpdate
	 * @param autoClose
	 * @return successful?
	 */
	public static boolean updateOrder(Order orderToUpdate, boolean autoClose) {
		
		String sqlTemplate = "UPDATE Orders SET order_number = ?, customer_id = ?, cost = ?, order_status = ?, bike_id = ?, serial_number = ?, order_date = ? WHERE order_number = ?;";
		Connection mySQLConnection = ConnectionManager.getConnection();
		try {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, orderToUpdate.get_order_number());
			statement.setInt(2, orderToUpdate.get_customer().get_id());
			statement.setDouble(3, orderToUpdate.get_cost());
			statement.setString(4, mapOrderStatusToDBEnum(orderToUpdate.get_order_status()));
			statement.setInt(5, orderToUpdate.get_bike().get_id());
			statement.setInt(6, orderToUpdate.get_serial_number());
			statement.setString(7, orderToUpdate.get_date());
			statement.setInt(8, orderToUpdate.get_order_number());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		} finally {
			if (autoClose) {
				try {
					mySQLConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Using the provided customer id, all the customers orders are loaded from the database
	 * @param get_id Id to lookup
	 * @return Orders found, null if empty
	 */
	public static Collection<Order> getOrdersForCustomer(int get_id) {
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
"ON Bicycles.wheels_id = Wheels.id WHERE Customers.id = ?;";

		
		Collection<Order> Orders;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			statement.setInt(1, get_id);
			
			ResultSet rs = statement.executeQuery();
			
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
	
	/**
	 * Creates a new order based upon provided information. Saves it to DB
	 * @param _customer
	 * @param assignedBike Bike to be ordered
	 * @param cost Cost of bike
	 * @param date Date of order
	 * @param serialNumber Serial number of bike
	 * @return Created order
	 */
	public static Order createNewOrder(Customer _customer, Bicycle assignedBike, double cost, Date date, int serialNumber) {
		String sqlTemplate = "INSERT INTO Orders(customer_id, cost, order_status, bike_id, serial_number, order_date) VALUES(?,?,?,?,?,?);";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, _customer.get_id());
			statement.setDouble(2, cost);
			statement.setString(3, "pending");
			statement.setInt(4, assignedBike.get_id());
			statement.setInt(5, serialNumber);
			statement.setDate(6, date);
			
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				
				int orderID = rs.getInt(1);
				
				return new Order(orderID, _customer, cost, OrderStatus.PENDING, assignedBike, serialNumber, date.toString());
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;	
	}

	public static boolean deleteOrder(Order currentOrder) {
		String sqlTemplate = "DELETE FROM Orders WHERE order_number=?; ";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, currentOrder.get_order_number());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			
			return rowsAffected > 0;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
}
