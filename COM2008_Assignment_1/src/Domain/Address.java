package Domain;

public class Address extends DataRecord implements IDataRecord, IToUIString{
	private String _houseNumName;
	private String _streetName;
	private String _postCode;
	
	public Address(int id, String houseNumName, String streetName, String postCode) {
		super(id);
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
	
	public String toString() {
		return "" + get_houseNumName() + ", " + get_streetName() + ", " + get_postCode();
	}

	@Override
	public String toUIString() {
		return "<html>House Number/Name" + get_houseNumName() + "<br>Street Name: " + get_streetName() + "<br>Postcode: " + get_postCode() + "</html>";
	}
}
