package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Domain.Handlebar;
import Domain.HandlebarStyles;

/**
 * Class contains all SQL operations of the Handlebar class
 * @author Alex Dobson
 *
 */
public class HandlebarOperations {
	
	public final static String id = "handlebar_id";
	public final static String serial_number = "handlebar_serial_number";
	public final static String brand_name = "handlebar_brand_name";
	public final static String cost = "handlebar_cost";
	public final static String style = "style";
	public final static String stock = "handlebar_stock";
	
	public final static String column_string = 
			"Handlebars.id AS " + id + 
			", Handlebars.serial_number AS " + serial_number + 
			", Handlebars.brand_name AS " + brand_name + 
			", Handlebars.cost AS " + cost +
			", Handlebars.style AS " + style +
			", Handlebars.stock_num AS " + stock;
	
	/**
	 * Provided all elements match, converts provided results set into a handle bar object
	 * @param rs 
	 * @return Handlebar object parsed
	 * @throws SQLException
	 * @throws EnumMappingException
	 */
	public static Handlebar parseHandlebarFromResultset(ResultSet rs) throws SQLException, EnumMappingException {
		int id = rs.getInt(HandlebarOperations.id);
		int serial_number = rs.getInt(HandlebarOperations.serial_number);
		String brand_name = rs.getString(HandlebarOperations.brand_name);
		double cost = rs.getDouble(HandlebarOperations.cost);
		
		String style_string = rs.getString(HandlebarOperations.style);
		HandlebarStyles handlebarStyle = HandlebarStyles.valueOf(style_string.toUpperCase());
		
		int stockNum = rs.getInt(HandlebarOperations.stock);
	   
		return new Handlebar(id, brand_name, serial_number, cost, handlebarStyle, stockNum);
	}
	
	/**
	 * Returns all the records in the Handlebars table as Handlebar objects
	 * @return all handlebars
	 * @throws EnumMappingException
	 */
	public static Collection<Handlebar> getAllHandlebars() throws EnumMappingException {
	
		String sql = 				
"SELECT " + column_string + " " +
"FROM Handlebars;";
		
		
		Collection<Handlebar> Handlebars;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			Statement statement = mySQLConnection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			Handlebars = new ArrayList<Handlebar>();
			
			while (rs.next()) {	   
				Handlebar retrieved_Handlebar = parseHandlebarFromResultset(rs);
			   
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
	
	/**
	 * Looks up handlebars from the database based upon provided id
	 * @param idNum ID to look up
	 * @return handlebar object, if not found null
	 */
	public static Handlebar getHandlebar(int idNum) {
		String sql = 				
"SELECT " + column_string + " " +
"FROM Handlebars " +
"WHERE id=?;";
		
		
		Handlebar currentHandleBar = null;
		try (Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, idNum);
			
			ResultSet rs = statement.executeQuery();
						
			while (rs.next()) {			   
				currentHandleBar = parseHandlebarFromResultset(rs);
			   			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (EnumMappingException e) {
			e.printStackTrace();
		}
		
		return currentHandleBar;	
	}
	
	/**
	 * Updates a handlebar in the database returning whether the update was 
	 * successful or not
	 * @param handlebarToUpdate handlebar object to update
	 * @return successful?
	 */
	public static boolean updateHandlebar(Handlebar handlebarToUpdate) {
		
		String sqlTemplate = "UPDATE Handlebars SET serial_number = ?, brand_name = ?, cost = ?, style = ?, stock_num = ? WHERE id = ?;";
		
		
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sqlTemplate);
			
			statement.setInt(1, handlebarToUpdate.getSerialNumber());
			statement.setString(2, handlebarToUpdate.getBrandName());
			statement.setDouble(3, handlebarToUpdate.getCost());
			statement.setString(4, handlebarToUpdate.get_style().toString());
			statement.setInt(5, handlebarToUpdate.getStockNum());
			statement.setInt(6, handlebarToUpdate.get_id());

			
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Deletes provided handlebar object from the database
	 * @param handleBarToDelete 
	 * @return successful?
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public static boolean deleteHandlebar(Handlebar handleBarToDelete) throws SQLIntegrityConstraintViolationException  {
		String sql = "DELETE FROM Handlebars WHERE id = ?;";
		try(Connection mySQLConnection = ConnectionManager.getConnection()) {
			PreparedStatement statement = mySQLConnection.prepareStatement(sql);
			
			statement.setInt(1, handleBarToDelete.get_id());
			
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
	
	/**
	 * Saves provided fields in the database. Returns an object of new handlebar
	 * @param brandName
	 * @param serialNumber
	 * @param cost
	 * @param style
	 * @param stockNum
	 * @return Handlebar created from information
	 */
	public static Handlebar createHandlebar(String brandName, int serialNumber, double cost, HandlebarStyles style, int stockNum) {
		String sqlTemplate = "INSERT INTO Handlebars(serial_number, brand_name, cost, style, stock_num) VALUES(?,?,?,?,?);";
						
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
