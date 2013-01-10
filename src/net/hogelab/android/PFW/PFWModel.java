package net.hogelab.android.PFW;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;


//--------------------------------------------------
// class PFWModel

public class PFWModel {

	private static final String TAG = PFWModel.class.getSimpleName();

	public static final int		UPDATE_HINT_DATA_NONE = 0;

	private int					mSerialNumber = UPDATE_HINT_DATA_NONE;
	private List<PFWModelUpdateListener> mListeners;


	//--------------------------------------------------
	// public interfaces

	public interface PFWModelUpdateListener {
		public void onModelUpdate(PFWModel model, int updateHint);
	}


	//--------------------------------------------------
	// public functions

	public PFWModel() {
		mListeners = new ArrayList<PFWModelUpdateListener>();
	}


	public synchronized int countListener() {
		return mListeners.size();
	}

	public synchronized void addListener(PFWModelUpdateListener listener) {
		Log.v(TAG, "addListener");

		if (!mListeners.contains(listener)) {
			mListeners.add(listener);
			Log.v(TAG, "added listener:" + listener.toString());
		}
	}

	public synchronized void removeListener(PFWModelUpdateListener listener) {
		Log.v(TAG, "removeListener");

		if (mListeners.contains(listener)) {
			mListeners.remove(listener);
			Log.v(TAG, "removed listener:" + listener.toString());

			if (mListeners.size() == 0) {
				onNoListener();
			}
		}
	}

	public synchronized void removeAllListener() {
		Log.v(TAG, "removeAllListener");

		mListeners = new ArrayList<PFWModelUpdateListener>();

		onNoListener();
	}


	public synchronized int getUpdateHint() {
		return mSerialNumber;
	}


	//--------------------------------------------------
	// protected functions

	protected synchronized void updated() {
		Log.v(TAG, "updated");

		int updateHint;
		synchronized (this) {
			updateHint = ++mSerialNumber;
		}

		notifyUpdate(updateHint);
	}

	protected void notifyUpdate(int updateHint) {
		Log.v(TAG, "notifyUpdate");

		List<PFWModelUpdateListener> listenersCopy = new ArrayList<PFWModelUpdateListener>();
		synchronized (this) {
			listenersCopy.addAll(mListeners);
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
