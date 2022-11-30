package Domain;

/**
 * Parent class to most of data in database as the standard primary
 * key is _id
 * @author Alex Dobson
 *
 */
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
