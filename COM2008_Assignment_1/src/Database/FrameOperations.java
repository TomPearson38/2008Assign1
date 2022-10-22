package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Frameset;
import Domain.Gearset;

public class FrameOperations {

	/*
	 * Returns all the records in the Framesets table as Frameset objects
	 */
	public static Collection<Frameset> getAllFrames() {
	
		String sql = """				
SELECT Frames.id, Frames.serial_number, Frames.brand_name, Frames.cost, Frames.size, Frames.shocks, Gearsets.id as gearset_id, Gearsets.name AS gearset_name
FROM Frames
LEFT JOIN Gearsets
ON Frames.gears_id = Gearsets.id;
""";
		
		
		Collection<Frameset> Framesets;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Framesets = new ArrayList<Frameset>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int serial_number = rs.getInt("serial_number");
			    String brand_name = rs.getString("brand_name");
			    double cost = rs.getDouble("cost");
			    double size = rs.getDouble("size");
			    boolean shocks = rs.getBoolean("shocks");
			   
			    int gearset_id = rs.getInt("gearset_id");
			    String gearset_name = rs.getString("gearset_name");
			   
			    Gearset retrieved_gearset = new Gearset(gearset_id, gearset_name);
			   
			    Frameset retrieved_frameset = new Frameset(id, brand_name, serial_number, cost, size, retrieved_gearset, shocks);
			   
			    Framesets.add(retrieved_frameset);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Framesets;
		
	}
	
	/*
	 * Updates a Frameset in the database returning whether the update was successful or not
	 */
	public static boolean updateFrameset(Frameset FramesetToUpdate) {
		
		String sqlTemplate = """
UPDATE Frames
SET serial_number = ?, brand_name = ?, cost = ?, shocks = ?, size = ?, gears_id = ?
WHERE id = ?;
				""";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, FramesetToUpdate.SerialNumber());
			statement.setString(2, FramesetToUpdate.BrandName());
			statement.setDouble(3, FramesetToUpdate.Cost());
			statement.setBoolean(4, FramesetToUpdate.get_shocks());
			statement.setDouble(5, FramesetToUpdate.get_size());
			statement.setInt(6, FramesetToUpdate.get_gearset().get_id());
			statement.setInt(7, FramesetToUpdate.get_id());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static Frameset insertFrameset(int serialNumber, String brandName, double cost, double size, boolean shocks, Gearset gears) {
		String sqlTemplate = """
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id)
VALUES(?,?,?,?,?, ?);
""";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, serialNumber);
			statement.setString(2, brandName);
			statement.setDouble(3, cost);
			statement.setDouble(4, size);
			statement.setBoolean(5, shocks);
			statement.setInt(6, gears.get_id());
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int frameId = rs.getInt(1);
					
					return new Frameset(frameId, brandName, serialNumber, cost, size, gears, shocks);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
