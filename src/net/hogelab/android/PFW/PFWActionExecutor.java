package net.hogelab.android.PFW;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;


//--------------------------------------------------
// class PFWActionExecutor

public class PFWActionExecutor {

	@SuppressWarnings("unused")
	private static final String TAG = PFWActionExecutor.class.getSimpleName();

	private static final String	THREAD_QUEUE_NAME = PFWAction.class.getSimpleName() + "_HandlerThreadQueue";

	private static HandlerThread mHandlerThread = null;
	private static Handler		mHandler = null;


	//--------------------------------------------------
	// static functions

	public static void initialize() {
		mHandlerThread = new HandlerThread(THREAD_QUEUE_NAME);
		mHandlerThread.start();

		mHandler = new Handler(mHandlerThread.getLooper());
	}


	public static void executeSerial(PFWAction action) {
		mHandler.post(action);
	}

	public static void executeParallel(PFWAction action) {
		AsyncTask.execute(action);
	}
}
