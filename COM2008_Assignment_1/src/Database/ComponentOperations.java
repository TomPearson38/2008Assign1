package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A collection of all the bike component operations and interactions with sql
 * Used to generate the filer options from the picker panels
 * @author Alex Dobson
 *
 */
public class ComponentOperations {
	/**
	 * Gets all the brands from the provided database table
	 * @param tableName
	 * @return
	 */
	public static Collection<String> getAllBrands(String tableName) {
		return ComponentOperations.getAllBrands(tableName, "brand_name");
	}
	
	/**
	 * Gets all the brands from the provided database table and given column identifier
	 * @param tableName Table to get columns from
	 * @param brandNameColumnIdentifier Column name
	 * @return
	 */
	public static Collection<String> getAllBrands(String tableName, String brandNameColumnIdentifier) {
		String sql = "SELECT " + brandNameColumnIdentifier + " FROM " + tableName + " GROUP BY " + brandNameColumnIdentifier + ";";
		
		Collection<String> Brands;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Brands = new ArrayList<String>();
			
			while (rs.next()) {
			    String brand_name = rs.getString("brand_name");
			   
			    Brands.add(brand_name);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Brands;
	}
}
