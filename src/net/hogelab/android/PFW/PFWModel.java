package net.hogelab.android.PFW;


import java.util.ArrayList;
import java.util.List;


//--------------------------------------------------
// class PFWModel

public class PFWModel {

	@SuppressWarnings("unused")
	private static final String TAG = PFWPresenter.class.getSimpleName();


	private int serialNumber = 0;
	private List<PFWModelUpdateListener> listeners;


	//--------------------------------------------------
	// interfaces

	public interface PFWModelUpdateListener {
		public void onModelUpdate(PFWModel model, int updateHint);
	}


	//--------------------------------------------------
	// public functions

	public PFWModel() {
		listeners = new ArrayList<PFWModelUpdateListener>();
	}


	public synchronized int countListener() {
		return listeners.size();
	}

	public synchronized void addListener(PFWModelUpdateListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public synchronized void removeListener(PFWModelUpdateListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	public synchronized void removeAllListener() {
		listeners = new ArrayList<PFWModelUpdateListener>();
	}


	public synchronized int getUpdateHint() {
		return serialNumber;
	}


	//--------------------------------------------------
	// protected functions

	protected synchronized void update() {
		int updateHint;
		synchronized (this) {
			updateHint = ++serialNumber;
		}

		notifyUpdate(updateHint);
	}

	protected void notifyUpdate(int updateHint) {
		List<PFWModelUpdateListener> listenersCopy = new ArrayList<PFWModelUpdateListener>();
		synchronized (this) {
			listenersCopy.addAll(listeners);
		}

		for (PFWModelUpdateListener listener : listenersCopy) {
			listener.onModelUpdate(this, updateHint);
		}
	}
}
