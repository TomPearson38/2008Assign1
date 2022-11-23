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
	/*
	 * Returns all the records in the Bicycle table as Bicycle objects
	 */
	public static Collection<Bicycle> getAllBikes() {
	
		String sql = """				
SELECT * FROM Bikes;
""";
		
		
		Collection<Bicycle> Bicycles;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Bicycles = new ArrayList<Bicycle>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				Frameset frame = FrameOperations.getFrameset(rs.getInt("frameset_id"));
				Handlebar hb = HandlebarOperations.getHandlebar(rs.getInt("handlebar_id"));
				Wheel wheels = WheelOperations.getWheel(rs.getInt("wheels_id"));
				String frameName = rs.getString("frame_name");
			   
				Bicycle  currentBike = new Bicycle(id, frame, hb, wheels, frameName);
							   			   
			    Bicycles.add(currentBike);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Bicycles;
		
	}
	
	public static Bicycle getBike(int id) {
		String sql= "SELECT * FROM Bicycles WHERE id='" + id + "';";
		
		Bicycle foundBike = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
						
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
}
