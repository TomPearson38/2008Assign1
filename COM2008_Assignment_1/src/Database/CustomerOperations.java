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
				int id = rs.getInt("id");
				String forename = rs.getString("forename");
			    String surname = rs.getString("surname");
			    int addressID = rs.getInt("address_id");
			    
			    String houseNumName = rs.getString("house_num_name");
			    String streetName = rs.getString("street_name");
			    String postCode = rs.getString("post_code");
			   
			    Address retrieved_address = new Address(addressID, houseNumName, streetName, postCode);
			   
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

	public static Collection<String> getBrandsInFramesTable() {
		return ComponentOperations.getAllBrands("Frames");
	}
}
