package Domain;

/**
 * Staff are allowed to make changes to the orders and view all orders
 * They are also required to login to make any changes to the databases and orders
 * @author tomap
 *
 */
public class Staff implements IToUIString{
	private String _username;
	private String _password;
	
	/**
	 * New staff member is initialised
	 * @param username 
	 * @param password
	 */
	public Staff(String username, String password) {
		_username = username;
		_password = password;
	}

	public String get_username() {
		return _username;
	}
	
	private boolean username_valid(String testUsername) {
		if(_username.equals(testUsername))
			return true;
		else
			return false;
	}
	
	private boolean password_valid(String testPassword) {
		if(_password.equals(testPassword)) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Validates if login details presented match current object
	 * @param testUsername 
	 * @param testPassword
	 * @return Valid
	 */
	public boolean validLogin(String testUsername, String testPassword) {
		return (username_valid(testUsername) && password_valid(testPassword));
	}
	
	
	@Override
	public String toUIString() {
		return "<html> Username: " + _username + "</html>";
	}
	
	public String toString() {
		return _username;
	}
}
