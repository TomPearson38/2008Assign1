package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Address;

public class AddressOperations {
	public static Collection<Address> getAllAddresses() {
		
		String sql = """				
SELECT *
FROM Addresses;
""";
		
		
		Collection<Address> Addresses;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Addresses = new ArrayList<Address>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String houseNumName = rs.getString("house_num_name");
				String streetName = rs.getString("street_name");
				String postCode = rs.getString("post_code");
			   
			    Address retrived_address = new Address(id, houseNumName, streetName, postCode);
			   
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
}
