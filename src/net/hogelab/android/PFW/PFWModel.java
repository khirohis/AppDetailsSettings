package net.hogelab.android.PFW;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;


//--------------------------------------------------
// class PFWModel

public class PFWModel {

	private static final String TAG = PFWModel.class.getSimpleName();

	public static final int		HINT_DATA_NONE = 0;

	private int serialNumber = HINT_DATA_NONE;
	private List<PFWModelUpdateListener> listeners;


	//--------------------------------------------------
	// public interfaces

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
		Log.v(TAG, "addListener");

		if (!listeners.contains(listener)) {
			listeners.add(listener);
			Log.v(TAG, "added listener:" + listener.toString());
		}
	}

	public synchronized void removeListener(PFWModelUpdateListener listener) {
		Log.v(TAG, "removeListener");

		if (listeners.contains(listener)) {
			listeners.remove(listener);
			Log.v(TAG, "removed listener:" + listener.toString());

			if (listeners.size() == 0) {
				onNoListener();
			}
		}
	}

	public synchronized void removeAllListener() {
		Log.v(TAG, "removeAllListener");

		listeners = new ArrayList<PFWModelUpdateListener>();

		onNoListener();
	}


	public synchronized int getUpdateHint() {
		return serialNumber;
	}


	//--------------------------------------------------
	// protected functions

	protected synchronized void updated() {
		Log.v(TAG, "updated");

		int updateHint;
		synchronized (this) {
			updateHint = ++serialNumber;
		}

		notifyUpdate(updateHint);
	}

	protected void notifyUpdate(int updateHint) {
		Log.v(TAG, "notifyUpdate");

		List<PFWModelUpdateListener> listenersCopy = new ArrayList<PFWModelUpdateListener>();
		synchronized (this) {
			listenersCopy.addAll(listeners);
		}

		for (PFWModelUpdateListener listener : listenersCopy) {
			Log.v(TAG, "notify update to:" + listener.toString());
			listener.onModelUpdate(this, updateHint);
		}
	}


	protected void onNoListener() {
		Log.v(TAG, "onNoListener");
	}
}
