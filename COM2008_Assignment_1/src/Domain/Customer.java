package Domain;

public class Customer {
	private int _id;
	private String _forename;
	private String _surname;
	private Address _address;
	
	public Customer(int id, String forename, String surname, Address a) {
		_id = id;
		_forename = forename;
		_surname = surname;
		_address = a;
	}

	public String get_full_Name() {
		return _forename + " " + _surname;
	}
	
	public String get_forename() {
		return _forename;
	}
	
	public String get_surname() {
		return _surname;
	}
	
	public Address get_address() {
		return _address;
	}
	
	public void set_forename(String newName) {
		_forename = newName;
	}
	
	public void set_surname(String newName) {
		_surname = newName;
	}
	
	public void set_address(Address newAddres) {
		_address = newAddres;
	}
}
