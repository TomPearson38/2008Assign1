package Domain;

public class Staff {
	private String _username;
	private String _password;
	
	public Staff(String username, String password) {
		_username = username;
		_password = password;
	}

	public String get_username() {
		return _username;
	}

	public boolean password_valid(String testPassword) {
		if(_password == testPassword) 
			return true;
		else 
			return false;
	}
}