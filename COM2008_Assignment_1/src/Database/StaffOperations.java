package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.BrakeType;
import Domain.Staff;
import Domain.TyreType;
import Domain.Wheel;


public class StaffOperations {
	/*
	 * Returns all the records in the Wheels table as Wheel objects
	 */
	public static Collection<Staff> getAllStaff() {
	
		String sql = """				
SELECT *
FROM Staff;
""";
		
		
		Collection<Staff> Staff;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Staff = new ArrayList<Staff>();
			
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
	
			    Staff retreived_staff = new Staff(username, password);
			   
			    Staff.add(retreived_staff);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Staff;
		
}
}
