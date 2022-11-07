package Domain;

public class Address {
	private int _id;
	private String _houseNumName;
	private String _streetName;
	private String _postCode;
	
	public Address(int id, String houseNumName, String streetName, String postCode) {
		_id = id;
		_houseNumName = houseNumName;
		_streetName = streetName;
		_postCode = postCode;
	}

	public String get_houseNumName() {
		return _houseNumName;
	}
	
	public String get_streetName() {
		return _streetName;
	}
	
	public String get_postCode() {
		return _postCode;
	}
	
	
	public void set_houseNumName(String newNumName) {
		_houseNumName = newNumName;
	}
	
	public void set_streetName(String newStreet) {
		_streetName = newStreet;
	}
	
	public void set_postCode(String newPost) {
		_postCode = newPost;
	}
}
