package net.hogelab.android.PFW;

import android.os.Handler;
import android.os.HandlerThread;


//--------------------------------------------------
// class PFWAction

public abstract class PFWAction implements Runnable {

	private static final String TAG = PFWAction.class.getSimpleName();
	private static final String	THREAD_NAME = PFWAction.class.getSimpleName() + "Thread";

	protected static HandlerThread mHandlerThread = null;
	protected static Handler	mHandler = null;

}
