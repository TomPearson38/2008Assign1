package View.AbstractPicker;

import java.awt.Frame;

public interface IEditor<T> {
	public T openEditor(Frame owner, T objectToEdit);
}
