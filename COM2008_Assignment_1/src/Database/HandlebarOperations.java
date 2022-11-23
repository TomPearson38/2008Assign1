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

public class HandlebarOperations {
	
	/*
	 * Returns all the records in the Handlebars table as Handlebar objects
	 */
	public static Collection<Handlebar> getAllHandlebars() throws EnumMappingException {
	
		String sql = """				
SELECT id, serial_number, brand_name, cost, style, stock_num
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
				
				int stock_num = rs.getInt("stock_num");

							   
				Handlebar retrieved_Handlebar = new Handlebar(id, brand_name, serial_number, cost, style, stock_num);
			   
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
	
	public static Handlebar getHandlebar(int idNum) {
		String sql = """				
SELECT *
FROM Handlebars
WHERE id='"""+idNum+"';";
		
		
		Handlebar currentHandleBar = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
						
			while (rs.next()) {
				int id = rs.getInt("id");
				int serialNum = rs.getInt("serial_number");
				String brandName = rs.getString("brand_name");
				double cost = rs.getDouble("cost");
				HandlebarStyles handlebarStyle = HandlebarStyles.valueOf((rs.getString("style")).toUpperCase());
				int stockNum = rs.getInt("stock_num");
			   
				currentHandleBar = new Handlebar(id, brandName, serialNum, cost, handlebarStyle, stockNum);
			   			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return currentHandleBar;	
	}
	
	public static Handlebar createHandlebar(String brandName, int serialNumber, double cost, HandlebarStyles style, int stockNum) {
		String sqlTemplate = """
				INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num)
				VALUES(?,?,?,?,?);
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
			statement.setInt(5, stockNum);
			
			int rowAffected = statement.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					
					int handlebarId = rs.getInt(1);
					
					return new Handlebar(handlebarId, brandName, serialNumber, cost, style, stockNum);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
