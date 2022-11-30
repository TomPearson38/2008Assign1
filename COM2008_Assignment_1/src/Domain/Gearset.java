package Domain;

/**
 * Gearset object. Contains name of gears and their ID number
 * @author Alex Dobson
 *
 */
public class Gearset extends DataRecord implements IToUIString {
	private String _name;

	/**
	 * Create new gearset object
	 * @param _id
	 * @param _name
	 */
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

    @Override
    public String toUIString() {
        // TODO Auto-generated method stub
        return toString();
    }

}
