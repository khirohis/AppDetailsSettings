package net.hogelab.android.PFW;

import android.content.Context;


//--------------------------------------------------
// class PFWModel

public class PFW {

	public static boolean initialize(Context context) {
		PFWActionExecutor.initialize();

		return true;
	}
}
