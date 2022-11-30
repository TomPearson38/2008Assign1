package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Bicycle;
import Domain.Frameset;
import Domain.Gearset;
import Domain.Handlebar;
import Domain.Wheel;

public class BicycleOperations {
	public final static String id = "bike_id";
	public final static String name = "given_name";
	public final static String frameset_id = "frameset_id";
	public final static String handlebar_id = "handlebar_id";
	public final static String wheels_id = "wheels_id";
	
	public final static String column_string = 
			"Bicycles.id AS " + id + 
			", Bicycles.given_name AS " + name + 
			", Bicycles.frameset_id AS " + frameset_id + 
			", Bicycles.handlebar_id AS " + handlebar_id + 
			", Bicycles.wheels_id AS " + wheels_id;
	
	/*
	 * Returns all the records in the Bicycle table as Bicycle objects
	 */
	public static Collection<Bicycle> getAllBikes() {
	
		String sql =			
"SELECT " + BicycleOperations.column_string + ", " + FrameOperations.column_string + ", " +  GearsetOperations.column_string + ", " + HandlebarOperations.column_string + ", " + WheelOperations.column_string + " " + 
" FROM Bicycles " +
"LEFT JOIN Frames " +
"ON Bicycles.frameset_id = Frames.id " +
"LEFT JOIN Gearsets " +
"ON Frames.gears_id = Gearsets.id " + 
"LEFT JOIN Handlebars " +
"ON Bicycles.handlebar_id = Handlebars.id " +
"LEFT JOIN Wheels " +
"ON Bicycles.wheels_id = Wheels.id;";
		
		Collection<Bicycle> Bicycles;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Bicycles = new ArrayList<Bicycle>();
			
			while (rs.next()) {
				
				Bicycle  currentBike = parseBicycleFromResultset(rs);
							   			   
			    Bicycles.add(currentBike);			           
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Bicycles;
		
	}
	
	public static Bicycle parseBicycleFromResultset(ResultSet rs) throws SQLException, EnumMappingException {
		int id = rs.getInt(BicycleOperations.id);
		Frameset frame = FrameOperations.parseFramesetFromResultSet(rs);
		Handlebar hb = HandlebarOperations.parseHandlebarFromResultset(rs);
		Wheel wheels = WheelOperations.parseWheelFromResultset(rs);
		
		String givenName = rs.getString(name);
	   
		return new Bicycle(id, frame, hb, wheels, givenName);
	}
	
	public static Bicycle getBike(int id) {
		String sql= 
"SELECT " + column_string + " FROM Bicycles" +  
"WHERE id=?;";
		
		Bicycle foundBike = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
				int _id = rs.getInt("id");
				Frameset frame = FrameOperations.getFrameset(rs.getInt("frameset_id"));
				Handlebar hb = HandlebarOperations.getHandlebar(rs.getInt("handlebar_id"));
				Wheel wheels = WheelOperations.getWheel(rs.getInt("wheels_id"));
				String givenName = rs.getString("given_name");
			   
				foundBike = new Bicycle(_id, frame, hb, wheels, givenName);
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return foundBike;
	}
	
	public static class CreateBicycleRequest {
		private Frameset frame;
		private Handlebar handlebars;
		private Wheel wheels;
		private String name;
		
		public CreateBicycleRequest(Frameset frame, Handlebar handlebar, Wheel wheels, String name) {
			super();
			this.frame = frame;
			this.handlebars = handlebar;
			this.wheels = wheels;
			this.name = name;
		}
		public Frameset getFrame() {
			return frame;
		}
		public Handlebar getHandlebars() {
			return handlebars;
		}
		public Wheel getWheels() {
			return wheels;
		}
		public String getName() {
			return name;
		}
	}
	
	public static Bicycle addBicycle(CreateBicycleRequest request) {
		String sqlTemplate = "INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, given_name) VALUES(?,?,?,?);";
						
						try(Connection mySQLConnection = ConnectionManager.getConnection()) {
							
							PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
							
							statement.setInt(1, request.getFrame().get_id());
							statement.setInt(2, request.getHandlebars().get_id());
							statement.setInt(3, request.getWheels().get_id());
							statement.setString(4, request.getName());
							
							int rowAffected = statement.executeUpdate();
							if (rowAffected == 1) {
								ResultSet rs = statement.getGeneratedKeys();
								if (rs.next()) {
									
									int bicycleId = rs.getInt(1);
									
									return new Bicycle(bicycleId, request.getFrame(), request.getHandlebars(), request.getWheels(), name);
								}
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						return null;
	}
	
	public static boolean updateBicycle(Bicycle bicycleToUpdate) {
		
		String sqlTemplate = "UPDATE Bicycles SET frameset_id = ?, handlebar_id = ?, wheels_id = ?, given_name = ? WHERE id = ?;";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, bicycleToUpdate.get_frame().get_id());
			statement.setInt(2, bicycleToUpdate.get_handlebar().get_id());
			statement.setInt(3, bicycleToUpdate.get_Wheels().get_id());
			statement.setString(4, bicycleToUpdate.getCustomerGivenName());
			statement.setInt(5, bicycleToUpdate.get_id());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean deleteBicycle(Bicycle bicycleToDelete) throws SQLIntegrityConstraintViolationException  {
		String sql = "DELETE FROM Bicycles WHERE id = ?;";
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, bicycleToDelete.get_id());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			throw e;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
}
