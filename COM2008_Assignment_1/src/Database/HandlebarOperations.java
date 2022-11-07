package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Frameset;
import Domain.Handlebar;
import Domain.HandlebarStyles;
import Domain.TyreType;

public class HandlebarOperations {
	
	/*
	 * Returns all the records in the Handlebars table as Handlebar objects
	 */
	public static Collection<Handlebar> getAllHandlebars() throws EnumMappingException {
	
		String sql = """				
SELECT id, serial_number, brand_name, cost, style
FROM Handlebars;
""";
		
		
		Collection<Handlebar> Handlebars;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Handlebars = new ArrayList<Handlebar>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int serial_number = rs.getInt("serial_number");
				String brand_name = rs.getString("brand_name");
				double cost = rs.getDouble("cost");
				String style_string = rs.getString("style");
				

				HandlebarStyles style  = null;
				if (style_string.equals("high")) {
					style = HandlebarStyles.HIGH;
				}
				else if (style_string.equals("dropped")) {
					style = HandlebarStyles.DROPPED;
				}
				else if (style_string.equals("straight")) {
					style = HandlebarStyles.STRAIGHT;
				} else {
					throw new EnumMappingException("HandlebarStyle " + style_string + " had no valid domain enum");
				}

							   
				Handlebar retrieved_Handlebar = new Handlebar(id, brand_name, serial_number, cost, style);
			   
				Handlebars.add(retrieved_Handlebar);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return Handlebars;
		
	}
	
	public static Handlebar createHandlebar(String brandName, int serialNumber, double cost, HandlebarStyles style) {
		String sqlTemplate = """
				INSERT INTO Handlebars(serial_number, brand_name, cost, style)
				VALUES(?,?,?,?);
				""";
						
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, serialNumber);
			statement.setString(2, brandName);
			statement.setDouble(3, cost);
			
			String style_string = null;
			if (style == HandlebarStyles.DROPPED) {
				style_string = "dropped";
			}
			else if (style == HandlebarStyles.HIGH) {
				style_string = "high";
			}
			else if (style == HandlebarStyles.STRAIGHT) {
				style_string = "straight";
			}
			statement.setString(4, style_string);
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int handlebarId = rs.getInt(1);
					
					return new Handlebar(handlebarId, brandName, serialNumber, cost, style);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
