package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Frameset;
import Domain.Gearset;

public class FrameOperations {
	
	/*
	 * The aliased names are stored as constants so the aliases are the same in the queries and the ResultSet parsers
	 */
	public final static String id = "frame_id";
	public final static String serial_number = "frame_serial_number";
	public final static String brand_name = "frame_brand_name";
	public final static String cost = "frame_cost";
	public final static String size = "frame_size";
	public final static String shocks = "shocks";
	public final static String stock_number = "frame_stock";
	
	public final static String column_string = 
			"Frames.id AS " + id + 
			", Frames.serial_number AS " + serial_number + 
			", Frames.brand_name AS " + brand_name + 
			", Frames.cost AS " + cost +
			", Frames.size AS " + size +
			", Frames.shocks AS " + shocks +
			", Frames.stock_num AS " + stock_number;
	
	/*
	 * Returns all the records in the Framesets table as Frameset objects
	 */
	public static Collection<Frameset> getAllFrames() {
	
		String sql = 			
"SELECT " + FrameOperations.column_string + ", " +  GearsetOperations.column_string + " " +
"FROM Frames " +
"LEFT JOIN Gearsets " +
"ON Frames.gears_id = Gearsets.id;";

		
		
		Collection<Frameset> Framesets;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Framesets = new ArrayList<Frameset>();
			
			while (rs.next()) {
				
			    Frameset retrieved_frameset = parseFramesetFromResultSet(rs);
			   
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
	
	public static Frameset parseFramesetFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(FrameOperations.id);
		int serial_number = rs.getInt(FrameOperations.serial_number);
	    String brand_name = rs.getString(FrameOperations.brand_name);
	    double cost = rs.getDouble(FrameOperations.cost);
	    double size = rs.getDouble(FrameOperations.size);
	    boolean shocks = rs.getBoolean(FrameOperations.shocks);
	    int stock_num = rs.getInt(FrameOperations.stock_number);
	   
	    Gearset retrieved_gearset = GearsetOperations.parseGearsFromResultset(rs);
	    
	    
	   
	    return new Frameset(id, brand_name, serial_number, cost, size, retrieved_gearset, shocks, stock_num);
	}
	
	
	public static Frameset getFrameset(int idNum) {
		String sqlTemplate = "SELECT * FROM Frames WHERE id= ?;";
		
		
		Frameset currentFrame = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, idNum);
			
			ResultSet rs = statement.executeQuery();
						
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
		
		String sqlTemplate = "UPDATE Frames SET serial_number = ?, brand_name = ?, cost = ?, shocks = ?, size = ?, gears_id = ?, stock_num = ? WHERE id = ?;";
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, FramesetToUpdate.getSerialNumber());
			statement.setString(2, FramesetToUpdate.getBrandName());
			statement.setDouble(3, FramesetToUpdate.getCost());
			statement.setBoolean(4, FramesetToUpdate.get_shocks());
			statement.setDouble(5, FramesetToUpdate.get_size());
			statement.setInt(6, FramesetToUpdate.get_gearset().get_id());
			statement.setInt(7, FramesetToUpdate.getStockNum());
			statement.setInt(8, FramesetToUpdate.get_id());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean deleteFrameset(Frameset framesetToDelete) throws SQLIntegrityConstraintViolationException  {
		String sql = "DELETE FROM Frames WHERE id = ?;";
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, framesetToDelete.get_id());
			
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
	
	public static Frameset insertFrameset(int serialNumber, String brandName, double cost, double size, boolean shocks, Gearset gears, int stockNum) {
		String sqlTemplate = "INSERT INTO Frames(serial_number, brand_name, cost, size, shocks, gears_id, stock_num) VALUES(?,?,?,?,?,?,?);";
		
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
	
	public static void decreaseStock(int id, int newStock) {
		
	}
	
	
}
