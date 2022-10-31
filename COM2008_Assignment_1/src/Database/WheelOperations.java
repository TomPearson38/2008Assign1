package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.BrakeType;
import Domain.TyreType;
import Domain.Wheel;

public class WheelOperations {

	/*
	 * Returns all the records in the Wheels table as Wheel objects
	 */
	public static Collection<Wheel> getAllGears() {
	
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
				TyreType tyre = TyreType.valueOf(rs.getString("tyre_type"));
				BrakeType brake = BrakeType.valueOf(rs.getString("brake_type"));
			   
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
}
