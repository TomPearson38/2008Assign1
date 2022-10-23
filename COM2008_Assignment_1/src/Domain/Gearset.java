package Domain;

public class Gearset extends DataRecord {
	private String _name;

	public Gearset(int _id, String _name) {
		super(_id);
		this._name = _name;
	}
	
	public String get_name() {
		return _name;
	}

	@Override
	public String toString() {
		return _name;
	}

}
