package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Address;
import Domain.Staff;

public class AddressOperations {
	
	public final static String id = "handlebar_id";
	public final static String house_num_name = "house_num_name";
	public final static String street_name = "street_name";
	public final static String post_code = "post_code";
	public final static String city = "city";
	
	public final static String column_string = 
			"Addresses.id AS " + id + 
			", Addresses.house_num_name AS " + house_num_name + 
			", Addresses.street_name AS " + street_name + 
			", Addresses.post_code AS " + post_code +
			", Addresses.city AS " + city;
	
	public static Address parseAddressFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(AddressOperations.id);
		String houseNumName = rs.getString(AddressOperations.house_num_name);
		String streetName = rs.getString(AddressOperations.street_name);
		String city = rs.getString(AddressOperations.city);
		String postCode = rs.getString(AddressOperations.post_code);
	   
	    return new Address(id, houseNumName, streetName, city, postCode);
	}
	public static Collection<Address> getAllAddresses() {
		
		String sql = 				
"SELECT " + column_string +
"FROM Addresses;"
;
		
		
		Collection<Address> Addresses;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Addresses = new ArrayList<Address>();
			
			while (rs.next()) {
			   
			    Address retrived_address = parseAddressFromResultSet(rs);
			   
			    Addresses.add(retrived_address);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Addresses;
		
	}
	
	public static Address findAddress(String houseNumName, String streetName, String city ,String postCode) {		
		String sql = """				
SELECT id, house_num_name, street_name, city,post_code
FROM Addresses
WHERE house_num_name=? AND street_name=? AND city=? AND post_code= ?;
""";
		
		Address foundAddress = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setString(1, houseNumName);
			statement.setString(2, streetName);
			statement.setString(3, city);
			statement.setString(4, postCode);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
				int addressID = rs.getInt("id");
				String foundNum = rs.getString("house_num_name");
				String foundStreet = rs.getString("street_name");
				String foundCity = rs.getString("city");
				String foundPostCode = rs.getString("post_code");

				foundAddress = new Address(addressID, foundNum, foundStreet, foundCity,foundPostCode);
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return foundAddress;
	}

	public static Address getAddress(int id) {
		String sql = """				
SELECT *
FROM Addresses
WHERE id=?
""";
		
		Address foundAddress = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);

			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
				int addressID = rs.getInt("id");
				String foundNum = rs.getString("house_num_name");
				String foundStreet = rs.getString("street_name");
				String foundCity = rs.getString("city");
				String foundPostCode = rs.getString("post_code");

				foundAddress = new Address(addressID, foundNum, foundStreet, foundCity,foundPostCode);
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return foundAddress;
	}
	
	
}
