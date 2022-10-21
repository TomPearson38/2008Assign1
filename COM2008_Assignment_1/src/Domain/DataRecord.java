package Domain;

public abstract class DataRecord implements IDataRecord {
	private int _id;

	public int get_id() {
		return _id;
	}

	public DataRecord(int _id) {
		super();
		this._id = _id;
	}
	
}
