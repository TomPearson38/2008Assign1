package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ComponentOperations {
	public static Collection<String> getAllBrands(String tableName) {
		return ComponentOperations.getAllBrands(tableName, "brand_name");
	}
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
