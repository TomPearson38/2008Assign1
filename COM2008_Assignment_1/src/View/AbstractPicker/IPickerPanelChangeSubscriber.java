package View.AbstractPicker;

interface IPickerPanelChangeSubscriber<T> {
	public void PickerPanelChanged(T newObject);
}