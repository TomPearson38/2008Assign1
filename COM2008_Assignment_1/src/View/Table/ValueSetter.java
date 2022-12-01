package View.Table;

/**
 * Contains a method for setting a value on an object
 * @author Alex Dobson
 *
 * @param <O> the type of the object that the value is being set on
 * @param <T> the type of the value that is being set on the object
 */
public interface ValueSetter<O, T> {
	public void setValueOnObject(O object, T value);
}
