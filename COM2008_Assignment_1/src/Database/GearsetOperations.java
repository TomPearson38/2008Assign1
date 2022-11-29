package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Gearset;
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class GearsetOperations {
	
	public final static String id = "gearset_id";
	public final static String name = "gearset_name";
	
	public final static String column_string = 
			"Gearsets.id AS " + id + 
			", Gearsets.name AS " + name;
	
	
	public static Gearset parseGearsFromResultset(ResultSet rs) throws SQLException {
		int gearset_id = rs.getInt(id);
	    String gearset_name = rs.getString(name);
	   
	    return new Gearset(gearset_id, gearset_name);
	}
	
	/*
	 * Returns all the records in the Gearsets table as Gearset objects
	 */
	public static Collection<Gearset> getAllGears() {
	
		String sql = 				
"SELECT " + column_string + " " +
"FROM Gearsets;";
		
		
		Collection<Gearset> Gearsets;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Gearsets = new ArrayList<Gearset>();
			
			while (rs.next()) {
			   
			    Gearset retrieved_gearset = parseGearsFromResultset(rs);
			   
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
		String sql = 			
"SELECT " + column_string + 
"FROM Gearsets" + 
"WHERE id=?;";
		
		
		Gearset selectedGear = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, _id);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
			   
				selectedGear = parseGearsFromResultset(rs);			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return selectedGear;
	}
	
	public static Gearset createGearset(String name) {
		String sqlTemplate = "INSERT INTO Gearsets(name) VALUES(?);";
						
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, name);
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int gearsetId = rs.getInt(1);
					
					return new Gearset(gearsetId, name);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
