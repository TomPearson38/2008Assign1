package View.Table;

import java.util.Collection;

/**
 * Interface that allows an object to be called if the objects in an AbstractTable change
 * @author Alex Dobson
 *
 * @param <T>
 */
public interface EditedObjectsChangedListener<T> {
    /**
     * 
     * @param source    The AbstractTable that is disseminating this event
     * @param editedObjects The collection of objects <T> that has been edited in the table
     */
	public void editedObjectsChanged(Object source, Collection<T> editedObjects);
}