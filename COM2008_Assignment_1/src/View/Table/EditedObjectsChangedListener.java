package View.Table;

import java.util.Collection;

public interface EditedObjectsChangedListener<T> {
	public void editedObjectsChanged(Object source, Collection<T> editedObjects);
}