package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Customer;
import Domain.Address;

/**
 * Class contains all SQL operations of the Customer Class
 * @author tomap
 *
 */
public class CustomerOperations {
	
	public final static String id = "customer_id";
	public final static String forename = "forename";
	public final static String surname = "surname";
	public final static String address_id = "address_id";
	
	public final static String column_string = 
			"Customers.id AS " + id + 
			", Customers.forename AS " + forename + 
			", Customers.surname AS " + surname + 
			", Customers.address_id AS " + address_id;
	
	/**
	 * Returns all the records in the Customers table as Customer objects
	 * @return
	 */
	public static Collection<Customer> getAllCustomers() {
	
		String sql = "SELECT Customers.id, Customers.forename, Customers.surname, Customers.address_id, Addresses.house_num_name as houseNumName, Addresses.street_name as streetName, Addresses.post_code as postCode FROM Customers LEFT JOIN Addresses ON Customers.address_id = Addresses.id;";
		
		
		Collection<Customer> Customers;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Customers = new ArrayList<Customer>();
			
			while (rs.next()) {
				int id = rs.getInt(CustomerOperations.id);
				String forename = rs.getString(CustomerOperations.forename);
			    String surname = rs.getString(CustomerOperations.surname);
			    int addressID = rs.getInt(CustomerOperations.address_id);
			    
			    String houseNumName = rs.getString("house_num_name");
			    String streetName = rs.getString("street_name");
			    String city = rs.getString("city");
			    String postCode = rs.getString("post_code");
			   
			    Address retrieved_address = new Address(addressID, houseNumName, streetName, city, postCode);
			   
			    Customer retrieved_customer = new Customer(id, forename, surname, retrieved_address);
			   
			    Customers.add(retrieved_customer);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Customers;
		
	}
	
	/**
	 * Assuming that all the ResultSet columns match the address object,
	 * then it will parse them into an Address object
	 * @param rs
	 * @return Address object
	 */
	public static Address parseAddressFromResultset(ResultSet rs) {
		try {
			int addressID = rs.getInt("address_id");
			String houseNumName = rs.getString("house_num_name");
		    String streetName = rs.getString("street_name");
		    String city = rs.getString("city");
		    String postCode = rs.getString("post_code");
		   
		    return new Address(addressID, houseNumName, streetName, city, postCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * assuming that all the ResultSet columns match these then it will parse them into an Customer object
	 * @param rs
	 * @return Customer object
	 */
	public static Customer parseCustomerFromResultset(ResultSet rs) {
		try {
			int id = rs.getInt("customer_id");
			String forename = rs.getString("forename");
		    String surname = rs.getString("surname");
		    
		    Address customersAddress = parseAddressFromResultset(rs);
		   
		    return  new Customer(id, forename, surname, customersAddress);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Finds a customer from the database using provided information
	 * @param forename
	 * @param surname
	 * @param houseNumName
	 * @param streetName
	 * @param city
	 * @param postCode
	 * @return Found customer, null if not found
	 */
	public static Customer findCustomer(String forename, String surname, String houseNumName, String streetName ,String city, String postCode){
		
		Address foundAddress = AddressOperations.findAddress(houseNumName, streetName, city, postCode);
		
		if(foundAddress == null)
			return null;
		
		String sqlCustomer = "SELECT id, forename, surname, address_id FROM Customers WHERE forename = ? AND surname= ? AND address_id= ?;"; 
				
		Customer selectedCustomer = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlCustomer);
			
			statement.setString(1, forename);
			statement.setString(2, surname);
			statement.setInt(3, (foundAddress.get_id()));
			
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String getForename = rs.getString("forename");
				String getSurname = rs.getString("surname");
				
				selectedCustomer = new Customer(id, getForename, getSurname, foundAddress);	
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return selectedCustomer;
	}
	
	/**
	 * Creates a customer from the provided information and saves it to the database
	 * @param forename
	 * @param surname
	 * @param address Address object associated with the customer
	 * @return Customer created
	 */
	public static Customer createCustomer(String forename, String surname, Address address) {
		String sqlTemplate = "INSERT INTO Customers(forename, surname, address_id) VALUES(?,?,?);";
						
						try(Connection mySQLConnection = ConnectionManager.getConnection()) {
							
							PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
							
							statement.setString(1, forename);
							statement.setString(2, surname);
							statement.setInt(3, address.get_id());
							
							int rowAffected = statement.executeUpdate();
							if (rowAffected == 1) {
								ResultSet rs = statement.getGeneratedKeys();
								if (rs.next()) {
									
									int customerID = rs.getInt(1);
									
									return new Customer(customerID, forename, surname, address);
								}
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						return null;
	}
	
	/**
	 * Gets a customer based upon the ID provided. Normally used in order to find
	 * Associated customer with order
	 * @param id
	 * @return
	 */
	public static Customer getCustomer(int id) {		
		String sqlCustomer = "SELECT * FROM Customers WHERE id=?;";
				
		Customer selectedCustomer = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlCustomer);
			
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			
			while (rs.next()) {
				int _id = rs.getInt("id");
				String getForename = rs.getString("forename");
				String getSurname = rs.getString("surname");
				Address foundAddress = AddressOperations.getAddress(rs.getInt("address_id"));
				
				selectedCustomer = new Customer(_id, getForename, getSurname, foundAddress);	
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return selectedCustomer;
	}
	
	/**
	 * Used when the customer places an order.
	 * It either finds a customer or creates one.
	 * @param _forename
	 * @param _surname
	 * @param _houseNumName
	 * @param _streetName
	 * @param _city
	 * @param _postCode
	 * @return A new or existing customer
	 */
	public static Customer customerCreatingOrder(String _forename, String _surname, String _houseNumName, String _streetName, String _city, String _postCode) {
		//Looks up customer based upon details
		Customer desiredCustomer = CustomerOperations.findCustomer(_forename, _surname, _houseNumName, _streetName, _city, _postCode);
		
		//Customer not found
		if(desiredCustomer == null) {
			//Checks to see if address is in database
			Address foundAddress = AddressOperations.findAddress(_houseNumName, _streetName, _city, _postCode);
			
			//Address not found
			if(foundAddress == null)
				//Address created
				foundAddress = AddressOperations.createAddress(_houseNumName, _streetName, _city, _postCode);
			
			//Customer created
			desiredCustomer = createCustomer(_forename, _surname, foundAddress);
		}

		return desiredCustomer;
	}
	
	
}
