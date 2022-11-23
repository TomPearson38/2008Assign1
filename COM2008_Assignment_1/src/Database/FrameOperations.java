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
import Domain.Handlebar;
import Domain.HandlebarStyles;

public class FrameOperations {

	/*
	 * Returns all the records in the Framesets table as Frameset objects
	 */
	public static Collection<Frameset> getAllFrames() {
	
		String sql = """				
SELECT Frames.id, Frames.serial_number, Frames.brand_name, Frames.cost, Frames.size, Frames.shocks, Gearsets.id as gearset_id, Gearsets.name AS gearset_name, Frames.stock_num
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
			    
			    int stock_num = rs.getInt("stock_num");
			   
			    Frameset retrieved_frameset = new Frameset(id, brand_name, serial_number, cost, size, retrieved_gearset, shocks, stock_num);
			   
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
	
	public static Frameset getFrameset(int idNum) {
		String sql = """				
SELECT *
FROM Frames
WHERE id='"""+idNum+"';";
		
		
		Frameset currentFrame = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
						
			while (rs.next()) {
				int id = rs.getInt("id");
				int serialNum = rs.getInt("serial_number");
				String brandName = rs.getString("brand_name");
				double cost = rs.getDouble("cost");
				double size = rs.getDouble("size");
				boolean shocks = rs.getBoolean("shocks");
				Gearset gear = GearsetOperations.getGear(rs.getInt("gears_id"));
				int stockNum = rs.getInt("stock_num");
			   
				currentFrame = new Frameset(id, brandName, serialNum, cost, size, gear, shocks, stockNum);
			   			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return currentFrame;	
	}
	
	
	/*
	 * Updates a Frameset in the database returning whether the update was successful or not
	 */
	public static boolean updateFrameset(Frameset FramesetToUpdate) {
		
		String sqlTemplate = """
UPDATE Frames
SET serial_number = ?, brand_name = ?, cost = ?, shocks = ?, size = ?, gears_id = ?, stock_num = ?
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
			statement.setInt(8, FramesetToUpdate.StockNum());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static Frameset insertFrameset(int serialNumber, String brandName, double cost, double size, boolean shocks, Gearset gears, int stockNum) {
		String sqlTemplate = """
INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id)
VALUES(?,?,?,?,?,?,?);
""";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, serialNumber);
			statement.setString(2, brandName);
			statement.setDouble(3, cost);
			statement.setDouble(4, size);
			statement.setBoolean(5, shocks);
			statement.setInt(6, gears.get_id());
			statement.setInt(7, stockNum);
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int frameId = rs.getInt(1);
					
					return new Frameset(frameId, brandName, serialNumber, cost, size, gears, shocks, stockNum);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	public static Collection<String> getBrandsInFramesTable() {
		return ComponentOperations.getAllBrands("Frames");
	}
}
