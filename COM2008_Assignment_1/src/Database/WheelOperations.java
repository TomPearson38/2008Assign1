package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.BrakeType;
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
			   
			    Wheel retrived_wheel = new Wheel(id, serialNum, brandName, cost, diameter, tyre, brake);
			   
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
	
	public static Wheel createWheel(String brandName, int serialNumber, double cost, double diameter, TyreType tyreType, BrakeType brakeType) {
		String sqlTemplate = """
				INSERT INTO Wheels(serial_number, brand_name, cost, diameter, tyre_type, brake_type)
				VALUES(?,?,?,?,?,?);
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
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int wheelId = rs.getInt(1);
					
					return new Wheel(wheelId, serialNumber, brandName, cost, diameter, tyreType, brakeType);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

