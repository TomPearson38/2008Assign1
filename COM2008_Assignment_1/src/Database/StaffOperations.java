package Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	private static ArrayList<Staff> allStaff = (ArrayList<Staff>) getAllStaff();
	
	/*
	 * Returns all the records in the Staff table as Staff objects
	 */
	private static Collection<Staff> getAllStaff() {
	
		String sql = "SELECT * FROM Staff;";
		
		
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
			e.printStackTrace();
			return null;
		}
		
		return Staff;
		
	}
	
	public static Staff attemptLogin(String attemptUsername, String attemptPassword) {
		String hashedPassword;
		try {
			hashedPassword = getSHA(attemptPassword);
		} catch (NoSuchAlgorithmException e1) {
			hashedPassword = attemptPassword;
			e1.printStackTrace();
		}
		
		Staff foundUser = null;
		
		for(Staff currentStaff: allStaff) {
			if(currentStaff.validLogin(attemptUsername, hashedPassword)) {
				foundUser = currentStaff;
				break;
			}
		}
		
		return foundUser;
	}
	
	
	//SHA encryption code is taken from 
	//https://www.baeldung.com/sha-256-hashing-java
	//Referenced from their GitHub repository (https://github.com/eugenp/tutorials) which is under MIT licence
	private static String getSHA(String unEncoded) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(unEncoded.getBytes(StandardCharsets.UTF_8));
		String hashedPassword = bytesToHex(hash);
		
		return hashedPassword;
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
}
