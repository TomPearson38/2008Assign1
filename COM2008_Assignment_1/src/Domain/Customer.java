package Domain;

public class Customer extends DataRecord implements IDataRecord, IToUIString{
	private String _forename;
	private String _surname;
	private Address _address;
	
	public Customer(int id, String forename, String surname, Address a) {
		super(id);
		_forename = forename;
		_surname = surname;
		_address = a;
	}

	public String get_full_name() {
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

	@Override
	public String toUIString() {
		return "<html>ID: " + get_id() + "<br>Name:" + get_full_name() 
				+ "<br>Address:" + get_address().toUIString() + "</html>";
	}
}
