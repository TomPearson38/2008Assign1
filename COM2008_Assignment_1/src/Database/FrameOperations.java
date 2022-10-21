package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Frameset;
import Domain.Gearset;

public class FrameOperations implements IFrameOperations {

	@Override
	/*
	 * Returns all the records in the Framesets table as Frameset objects
	 */
	public Collection<Frameset> getAllFrames() {
		Connection mySQLConnection = ConnectionManager.getConnection();
		
		Statement statement;
		try {
			statement = mySQLConnection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		String sql = """				
SELECT Frames.id, Frames.serial_number, Frames.brand_name, Frames.cost, Frames.size, Frames.shocks, Gearsets.name AS gearset_name
FROM Frames
LEFT JOIN Gearsets
ON Frames.gears_id = Gearsets.id;
""";
		
		ResultSet rs;
		Collection<Frameset> Framesets;
		try {
			rs = statement.executeQuery(sql);
			
			Framesets = new ArrayList<Frameset>();
			
			while (rs.next()) {
			   int serial_number = rs.getInt("serial_number");
			   String brand_name = rs.getString("brand_name");
			   double cost = rs.getDouble("cost");
			   double size = rs.getDouble("size");
			   boolean shocks = rs.getBoolean("shocks");
			   
			   String gearset_name = rs.getString("gearset_name");
			   
			   Gearset retrieved_gearset = new Gearset(gearset_name);
			   
			   Frameset retrieved_frameset = new Frameset(brand_name, serial_number, cost, size, retrieved_gearset, shocks);
			   
			   Framesets.add(retrieved_frameset);			   
			                    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Framesets;
		
	}

}
