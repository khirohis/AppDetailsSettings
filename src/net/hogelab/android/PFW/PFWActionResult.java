package net.hogelab.android.PFW;


//--------------------------------------------------
// class PFWActionResult

public class PFWActionResult<T> {

	@SuppressWarnings("unused")
	private static final String TAG = PFWActionResult.class.getSimpleName();

	private PFWActionRequest<?>	mRequest = null;
	private T					mResult = null;
	private Object				mError = null;


	public PFWActionResult(PFWActionRequest<?> request) {
		mRequest = request;
	}


	public PFWActionRequest<?> getRequest() {
		return mRequest;
	}

	public void setRequest(PFWActionRequest<?> request) {
		mRequest = request;
	}

	public T getResult() {
		return mResult;
	}

	public void setResult(T result) {
		mResult = result;
	}

	public Object getError() {
		return mError;
	}

	public void setError(Object error) {
		mError = error;
	}
}
