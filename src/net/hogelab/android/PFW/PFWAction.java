package net.hogelab.android.PFW;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;


//--------------------------------------------------
// class PFWAction

public abstract class PFWAction implements Runnable {

	private static final String TAG = PFWAction.class.getSimpleName();

	private static final String	THREAD_QUEUE_NAME = PFWAction.class.getSimpleName() + "_HandlerThreadQueue";

	private static HandlerThread mHandlerThread = null;
	private static Handler		mHandler = null;

	protected PFWActionListener	mListener = null;
	protected Object			mTag = null;
	protected Status			mStatus = Status.NOT_RUNNING;

	protected int				mProgressMax = 0;
	protected int				mCurrentProgress = 0;

	protected Object			mResult = null;


	//--------------------------------------------------
	// public interfaces

	public enum Status {
		NOT_RUNNING,
		RUNNING,
		SUCCESS,
		FAILURE,
		CANCELLED,
	};


	public interface PFWActionListener {
		public void onActionStart(Object tag, int progressMax);
		public void onActionProgress(Object tag, int currentProgress);
		public void onActionComplete(Object tag, Status status, Object result);
	}


	//--------------------------------------------------
	// static functions

	public static void initialize(Context context) {
		mHandlerThread = new HandlerThread(THREAD_QUEUE_NAME);
		mHandlerThread.start();

		mHandler = new Handler(mHandlerThread.getLooper());
	}


	//--------------------------------------------------
	// public functions

	public PFWAction(PFWActionListener listener, Object tag) {
		mListener = listener;
		mTag = tag;
	}


	public void execute() {
		mHandler.post(this);
	}

	public void asyncExecute() {
	}


	public void cancel() {
		synchronized (this) {
			mStatus = Status.CANCELLED;
		}

		onCancelled();
	}

	public boolean isCancelled() {
		synchronized (this) {
			return mStatus == Status.CANCELLED;
		}
	}


	@Override
	public void run() {
		Log.d(TAG, "run");

		synchronized (this) {
			mStatus = Status.RUNNING;
		}

		onPreAction();

		synchronized (this) {
			if (mListener != null) {
				if (mStatus != Status.CANCELLED && mProgressMax > 0) {
					mListener.onActionStart(mTag, mProgressMax);
				}
			}
		}

		if (doAction()) {
			synchronized (this) {
				if (mStatus != Status.CANCELLED) {
					mStatus = Status.SUCCESS;
				}
			}
		} else {
			synchronized (this) {
				if (mStatus != Status.CANCELLED) {
					mStatus = Status.FAILURE;
				}
			}
		}

		onPostAction();

		synchronized (this) {
			if (mListener != null) {
				if (mStatus != Status.CANCELLED && mProgressMax > 0) {
					mListener.onActionProgress(mTag, mProgressMax);
				}

				mListener.onActionComplete(mTag, mStatus, mResult);
			}
		}
	}


	//--------------------------------------------------
	// protected functions

	protected void onPreAction() {
	}

	protected void onPostAction() {
	}

	protected void onCancelled() {
	}


	protected abstract boolean doAction();
}
