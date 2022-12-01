package Database;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Domain.Staff;

/**
 * Class contains all the SQL operations of the Staff Class
 * @author tomap
 *
 */
public class StaffOperations {
	
	private static ArrayList<Staff> allStaff = (ArrayList<Staff>) getAllStaff();
	
	/**
	 * Returns all the records in the Staff table as Staff objects
	 * Queried at system start up
	 * @return all staff objects from database
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
				String salt = rs.getString("salt");
	
			    Staff retreived_staff = new Staff(username, password, salt);
			   
			    Staff.add(retreived_staff);			   
			                    
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return Staff;
		
	}
	
	/**
	 * Attempted login based upon provided information
	 * @param attemptUsername
	 * @param attemptPassword
	 * @return if successful, the staff member that it matches is returned
	 */
	public static Staff attemptLogin(String attemptUsername, char[] attemptPassword) {
		Staff foundUser = null;
		for(Staff currentStaff: allStaff) {
			if(currentStaff.get_username().equals(attemptUsername)) {
				String hashedPassword;
				try {
					char[] saltedPassword = mergeTwoCharArrays(attemptPassword , (currentStaff.getSalt()).toCharArray());
					hashedPassword = getSHA(saltedPassword);
					if(currentStaff.validLogin(attemptUsername, hashedPassword))
						foundUser = currentStaff;
					break;
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
					return null;
				}
				
			}
		}
		return foundUser;
	}
	
	private static char[] mergeTwoCharArrays(char[] array1, char[] array2) {				
		int c1 = array1.length + array2.length;
		char[] c = new char[c1];
		System.arraycopy(array1, 0, c, 0, array1.length);
		System.arraycopy(array2, 0, c, array1.length, array2.length);

		return c;
	}
	
	/*
	 * From https://stackoverflow.com/questions/5513144/converting-char-to-byte
	 */
	private static byte[] convertCharArraytoBytes(char[] chars) {
	  CharBuffer charBuffer = CharBuffer.wrap(chars);
	  ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	  byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	  Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	  return bytes;
	}

	/**
	 * Encrypts the provided password into SHA hashing before it is compared to saved password
	 * SHA encryption code is taken from 
	 * https://www.baeldung.com/sha-256-hashing-java
	 * Referenced from their GitHub repository (https://github.com/eugenp/tutorials)
	 * which is under MIT licence
	 * @param unEncoded Plain text string
	 * @return Hashed password
	 * @throws NoSuchAlgorithmException
	 */
	private static String getSHA(char[] unEncoded) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(convertCharArraytoBytes(unEncoded));
		String hashedPassword = bytesToHex(hash);
		
		return hashedPassword;
	}
	
	/**
	 * Converts hashed bytes to hexadecimal
	 * @param hash 
	 * @return Hex String
	 */
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
