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
import Domain.BrakeType;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import Domain.TyreType;
import Domain.Wheel;

public class WheelOperations {
	
	public final static String id = "wheel_id";
	public final static String serial_number = "wheel_serial_number";
	public final static String brand_name = "wheel_brand_name";
	public final static String cost = "wheel_cost";
	public final static String diameter = "diameter";
	public final static String tyre_type = "tyre_type";
	public final static String brake = "brake_type";
	public final static String stock_num = "wheel_stock_num";
	
	public final static String column_string = 
			"Wheels.id AS " + id + 
			", Wheels.serial_number AS " + serial_number + 
			", Wheels.brand_name AS " + brand_name + 
			", Wheels.cost AS " + cost + 
			", diameter AS " + diameter + 
			", tyre_type AS " + tyre_type + 
			", brake_type AS " + brake + 
			", Wheels.stock_num AS " + stock_num;
	
	public static Wheel parseWheelFromResultset(ResultSet rs) throws SQLException {
		int id = rs.getInt(WheelOperations.id);
		int serialNum = rs.getInt(WheelOperations.serial_number);
		String brandName = rs.getString(WheelOperations.brand_name);
		double cost = rs.getDouble(WheelOperations.cost);
		double diameter = rs.getDouble(WheelOperations.diameter);
		TyreType tyre = TyreType.valueOf((rs.getString(WheelOperations.tyre_type)).toUpperCase());
		BrakeType brake = BrakeType.valueOf((rs.getString(WheelOperations.brake)).toUpperCase());
		int stockNum = rs.getInt(WheelOperations.stock_num);
	   
	   return new Wheel(id, serialNum, brandName, cost, diameter, tyre, brake, stockNum);
	}

	/*
	 * Returns all the records in the Wheels table as Wheel objects
	 */
	public static Collection<Wheel> getAllWheels() {
	
		String sql = 				
"SELECT " + column_string + " " + 
"FROM Wheels;";
		
		
		Collection<Wheel> Wheels;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Wheels = new ArrayList<Wheel>();
			
			while (rs.next()) {
			    Wheel retrived_wheel = parseWheelFromResultset(rs);
			   
			    Wheels.add(retrived_wheel);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Wheels;		
	}
	
	public boolean checkForeignKeyUsage(int wheel_id){
		String sql = 
"SELECT COUNT(*) "+
"FROM Bicycles " +
"WHERE wheel_id=?;";
		
		Collection<Bicycle> bicyclesWithSelectedWheel;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			bicyclesWithSelectedWheel = new ArrayList<Bicycle>();
			
			while (rs.next()) {
			    Bicycle retrived_Bicycle = BicycleOperations.parseBicycleFromResultset(rs);
			   
			    bicyclesWithSelectedWheel.add(retrived_Bicycle);			   
			                    
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (bicyclesWithSelectedWheel.size() == 0){
			return true;
		}
		return false;
		
	}
	
	public static Wheel getWheel(int idNum) {
		String sql = 				
"SELECT "  + column_string + 
"FROM Wheels" + 
"WHERE id=?;";
		
		
		Wheel currentWheel = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, idNum);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
				
			   
			    currentWheel = parseWheelFromResultset(rs);
			   			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return currentWheel;	
	}
	
	/*
	 * Updates a Wheel in the database returning whether the update was successful or not
	 */
	public static boolean updateWheel(Wheel wheelToUpdate) {
		
		String sqlTemplate = """
UPDATE Frames
SET serial_number = ?, brand_name = ?, cost = ?, diameter = ?, tyre_type = ?, brake_type = ?, stock_num = ?
WHERE id = ?;
				""";
		
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, wheelToUpdate.SerialNumber());
			statement.setString(2, wheelToUpdate.BrandName());
			statement.setDouble(3, wheelToUpdate.Cost());
			statement.setDouble(4, wheelToUpdate.get_diameter());
			statement.setString(5, wheelToUpdate.get_tyre().toString());
			statement.setString(6, wheelToUpdate.get_brakes().toString());
			statement.setInt(7, wheelToUpdate.get_id());
			statement.setInt(8, wheelToUpdate.StockNum());
			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean deleteWheel(Wheel wheelToDelete) throws SQLIntegrityConstraintViolationException {
		String sql = """
DELETE
FROM Wheels
WHERE id = ?;
				""";
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, wheelToDelete.get_id());
			
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
	
	
	
	
	public static Wheel createWheel(String brandName, int serialNumber, double cost, double diameter, TyreType tyreType, BrakeType brakeType, int stockNum) {
		String sqlTemplate = """
				INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type, stock_num)
				VALUES(?,?,?,?,?,?,?);
				""";
						
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, serialNumber);
			statement.setString(2, brandName);
			statement.setDouble(3, cost);
			statement.setDouble(4, diameter);
			
			String tyre_string = null;
			if (tyreType == TyreType.ROAD) {
				tyre_string = "road";
			}
			else if (tyreType == TyreType.MOUNTAIN) {
				tyre_string = "mountain";
			}
			else if (tyreType == TyreType.HYBRID) {
				tyre_string = "hybrid";
			}
			statement.setString(5, tyre_string);
			
			String brake_string = null;
			if (brakeType == BrakeType.RIM) {
				brake_string = "rim";
			}
			else if (brakeType == BrakeType.DISK) {
				brake_string = "disk";
			}
				
			statement.setString(6, brake_string);
			
			statement.setInt(7,  stockNum);
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int wheelId = rs.getInt(1);
					
					return new Wheel(wheelId, serialNumber, brandName, cost, diameter, tyreType, brakeType, stockNum);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

