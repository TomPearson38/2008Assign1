package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public final static String name = "frame_name";
	public final static String frameset_id = "frameset_id";
	public final static String handlebar_id = "handlebar_id";
	public final static String wheels_id = "wheels_id";
	
	public final static String column_string = 
			"Bicycles.id AS " + id + 
			", Bicycles.frame_name AS " + name + 
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
		
		System.out.println(sql);
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
		
		String frameName = rs.getString(name);
	   
		return new Bicycle(id, frame, hb, wheels, frameName);
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
				String frameName = rs.getString("frame_name");
			   
				foundBike = new Bicycle(_id, frame, hb, wheels, frameName);
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return foundBike;
	}
	
	public static Bicycle addBicycle(Frameset frame, Handlebar handlebar, Wheel wheels, String name) {
		String sqlTemplate = "INSERT INTO Bicycles(frameset_id, handlebar_id, wheels_id, frame_name) VALUES(?,?,?,?);";
						
						try(Connection mySQLConnection = ConnectionManager.getConnection()) {
							
							PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
							
							statement.setInt(1, frame.get_id());
							statement.setInt(2, handlebar.get_id());
							statement.setInt(3, wheels.get_id());
							statement.setString(4, name);
							
							int rowAffected = statement.executeUpdate();
							if (rowAffected == 1) {
								ResultSet rs = statement.getGeneratedKeys();
								if (rs.next()) {
									
									int bicycleId = rs.getInt(1);
									
									return new Bicycle(bicycleId, frame, handlebar, wheels, name);
								}
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						return null;
	}
}
