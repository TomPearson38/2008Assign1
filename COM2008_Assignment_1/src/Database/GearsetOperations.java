package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Gearset;

public class GearsetOperations {
	
	/*
	 * Returns all the records in the Gearsets table as Gearset objects
	 */
	public static Collection<Gearset> getAllGears() {
	
		String sql = """				
SELECT id, name
FROM Gearsets;
""";
		
		
		Collection<Gearset> Gearsets;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Gearsets = new ArrayList<Gearset>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
			   
			    Gearset retrieved_gearset = new Gearset(id, name);
			   
			    Gearsets.add(retrieved_gearset);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Gearsets;
		
	}
	
	public static Gearset getGear(int _id) {
		String sql = """				
SELECT id, name
FROM Gearsets
WHERE id='"""+_id+"';";
		
		
		Gearset selectedGear = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
						
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
			   
				selectedGear = new Gearset(id, name);			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return selectedGear;
	}
}
