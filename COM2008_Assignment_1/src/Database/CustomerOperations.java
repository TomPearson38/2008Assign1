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
	
	/*
	 * Returns all the records in the Customers table as Customer objects
	 */
	public static Collection<Customer> getAllCustomers() {
	
		String sql = """				
SELECT Customers.id, Customers.forename, Customers.surname, Customers.address_id, Addresses.house_num_name as houseNumName, Addresses.street_name as streetName, Addresses.post_code as postCode
FROM Customers
LEFT JOIN Addresses
ON Customers.address_id = Addresses.id;
""";
		
		
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
	
	/*
	 * assuming that all the ResultSet columns match these then it will parse them into an Address object
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
	
	/*
	 * assuming that all the ResultSet columns match these then it will parse them into an Customer object
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
	
	public static Customer findCustomer(String forename, String surname, String houseNumName, String streetName ,String city, String postCode){
		
		Address foundAddress = AddressOperations.findAddress(houseNumName, streetName, city, postCode);
		
		if(foundAddress == null)
			return null;
		
		String sqlCustomer = """				
SELECT id, forename, surname, address_id
FROM Customers
WHERE forename = ? AND surname= ? AND address_id= ?;"""; 
				
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
	
	public static Customer getCustomer(int id) {		
		String sqlCustomer = """				
SELECT *
FROM Customers
WHERE id=?;
""";
				
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
}
