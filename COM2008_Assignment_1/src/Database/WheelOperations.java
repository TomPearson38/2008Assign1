package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.BrakeType;
import Domain.Frameset;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import Domain.TyreType;
import Domain.Wheel;

public class WheelOperations {

	/*
	 * Returns all the records in the Wheels table as Wheel objects
	 */
	public static Collection<Wheel> getAllWheels() {
	
		String sql = """				
SELECT *
FROM Wheels;
""";
		
		
		Collection<Wheel> Wheels;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Wheels = new ArrayList<Wheel>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int serialNum = rs.getInt("serial_number");
				String brandName = rs.getString("brand_name");
				double cost = rs.getDouble("cost");
				double diameter = rs.getDouble("diameter");
				TyreType tyre = TyreType.valueOf((rs.getString("tyre_type")).toUpperCase());
				BrakeType brake = BrakeType.valueOf((rs.getString("brake_type")).toUpperCase());
				int stockNum = rs.getInt("stock_num");
			   
			    Wheel retrived_wheel = new Wheel(id, serialNum, brandName, cost, diameter, tyre, brake, stockNum);
			   
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
	
	public static Wheel getWheel(int idNum) {
		String sql = """				
SELECT *
FROM Wheels
WHERE id=?;""";
		
		
		Wheel currentWheel = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, idNum);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {
				int id = rs.getInt("id");
				int serialNum = rs.getInt("serial_number");
				String brandName = rs.getString("brand_name");
				double cost = rs.getDouble("cost");
				double diameter = rs.getDouble("diameter");
				TyreType tyre = TyreType.valueOf((rs.getString("tyre_type")).toUpperCase());
				BrakeType brake = BrakeType.valueOf((rs.getString("brake_type")).toUpperCase());
				int stockNum = rs.getInt("stock_num");
			   
			    currentWheel = new Wheel(id, serialNum, brandName, cost, diameter, tyre, brake, stockNum);
			   			                    
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
	
	public static boolean deleteWheel(Wheel wheelToDelete) {
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
		} catch (SQLException e) {
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

