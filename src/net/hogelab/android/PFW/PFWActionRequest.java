package net.hogelab.android.PFW;


//--------------------------------------------------
// class PFWActionRequest

public class PFWActionRequest<T> {

	@SuppressWarnings("unused")
	private static final String TAG = PFWActionRequest.class.getSimpleName();

	private String				mActionName = null;
	private T					mActionParameter = null;


	PFWActionRequest(String actionName, T actionParameter) {
		mActionName = actionName;
		mActionParameter = actionParameter;
	}


	public String getActionName() {
		return mActionName;
	}

	public void setActionName(String actionName) {
		mActionName = actionName;
	}

	public T getActionParameter() {
		return mActionParameter;
	}

	public void setActionParameter(T actionParameter) {
		mActionParameter = actionParameter;
	}
}
