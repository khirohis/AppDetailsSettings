package net.hogelab.android.PFW;

import java.util.HashMap;
import java.util.Map;


//--------------------------------------------------
// class PFWActionQueue

public class PFWActionHandler {

	@SuppressWarnings("unused")
	private static final String TAG = PFWActionHandler.class.getSimpleName();

	protected Map<String, PFWAction> mRunningAction = null;


	//--------------------------------------------------
	// public functions

	public PFWActionHandler() {
		mRunningAction = new HashMap<String, PFWAction>();
	}
}
