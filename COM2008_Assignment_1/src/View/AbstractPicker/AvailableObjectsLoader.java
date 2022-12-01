package View.AbstractPicker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.SwingWorker;

class AvailableObjectsLoader<T> extends SwingWorker<Collection<T>, Object> {
		private final Supplier<Collection<T>> availableObjectsSupplier;

		private Collection<Consumer<Collection<T>>> completedListeners = new ArrayList<Consumer<Collection<T>>>();
		
		AvailableObjectsLoader(Supplier<Collection<T>> supplierToRun) {
			this.availableObjectsSupplier = supplierToRun;
		}

		@Override
		protected Collection<T> doInBackground() throws Exception {
			return this.availableObjectsSupplier.get();
		}
		
		@Override
        protected void done() {
            try {
				Collection<T> finishedCollection = get();
				completedListeners.forEach(x -> x.accept(finishedCollection));
			} catch (Exception idontcare) {}
        }
		
		public void addCompletedListener(Consumer<Collection<T>> value) { completedListeners.add(value); }
		public void removeCompletedListener(Consumer<Collection<T>> value) { completedListeners.remove(value); }

    }