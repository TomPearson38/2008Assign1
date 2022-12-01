package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Gearset;
import Domain.Handlebar;
import Domain.HandlebarStyles;

/**
 * Class contains all SQL operations of the Gearset Class
 * @author Alex Dobson
 *
 */
public class GearsetOperations {
	
	public final static String id = "gearset_id";
	public final static String name = "gearset_name";
	
	public final static String column_string = 
			"Gearsets.id AS " + id + 
			", Gearsets.name AS " + name;
	
	
	/**
	 * Assuming that all the ResultSet columns match these then it 
	 * will parse them into an gearset object
	 * @param rs ResultsSet
	 * @return Gearset object found, null if not found
	 * @throws SQLException
	 */
	public static Gearset parseGearsFromResultset(ResultSet rs) throws SQLException {
		int gearset_id = rs.getInt(id);
	    String gearset_name = rs.getString(name);
	   
	    return new Gearset(gearset_id, gearset_name);
	}
	
	/**
	 * Returns all the records in the Gearsets table as Gearset objects
	 * @return all gearsets
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
	
	/**
	 * Gets a specific gear set from the table based upon provided id
	 * @param _id ID to lookup
	 * @return Gearset found, if not found null is returned
	 */
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
	
	/**
	 * Creates gearset off provided information
	 * @param name Name of new gearset
	 * @return created gearset
	 */
	public static Gearset createGearset(String name) throws SQLException {
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
			throw ex;
		}
		return null;
	}
	
	public static class GearsetUpdateRequest {
		private int id;
		private String name;
		public GearsetUpdateRequest(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		
	}

	/**
	 * Updates provided gearset object in the database
	 * @param gearsetToUpdate Gearset to be updated
	 * @return update successful?
	 */
	public static Gearset updateGearset(GearsetUpdateRequest request) throws SQLException {
	    String sqlTemplate = 
	            "UPDATE Gearsets " +
	            "SET name = ? " +
	            "WHERE id = ?;";
	                    
	                    
	                    try(Connection mySQLConnection = ConnectionManager.getConnection()) {
	                        PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
	                        
	                        statement.setString(1, request.getName());
	                        statement.setInt(2, request.getId());
	                        
	                        int rowsAffected = statement.executeUpdate();
	                        statement.close();
	                        
	                        if (rowsAffected > 0) {
	                        	return new Gearset(request.getId(), request.getName());
	                        } else {
	                        	return null;
	                        }
	                    } catch (SQLException ex) {
	                        throw ex;
	                    }
	}
	
	/**
	 * Deletes provided gearset from the database
	 * @param gearsetToDelete Gear to delete 
	 * @return Successfull?
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public static boolean deleteGearset(Gearset gearsetToDelete) throws SQLIntegrityConstraintViolationException {
	       String sql = 
	               "DELETE " +
	               "FROM Gearsets " +
	               "WHERE id = ?;";
	                       try(Connection mySQLConnection = ConnectionManager.getConnection()) {
	                           PreparedStatement statement = mySQLConnection.prepareStatement(sql);
	                           
	                           statement.setInt(1, gearsetToDelete.get_id());
	                           
	                           int rowsAffected = statement.executeUpdate();
	                           statement.close();
	                           return rowsAffected > 0;
	                       }
	                       catch(SQLIntegrityConstraintViolationException e) {
	                           throw e;
	                       }
	                       catch (SQLException e) {
	                           // TODO Auto-generated catch block
	                           e.printStackTrace();
	                           
	                           return false;
	                       }
	}
}
