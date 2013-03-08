package net.hogelab.android.PFW;

import java.util.HashMap;
import java.util.Map;


//--------------------------------------------------
// class PFWActionQueue

public class PFWActionHandler {

	@SuppressWarnings("unused")
	private static final String TAG = PFWActionHandler.class.getSimpleName();

	private static int			ACTION_RESULT_SUCCESS = 0;

	protected Map<String, PFWAction> mRunningAction = null;


	//--------------------------------------------------
	// public classes

	public class ActionTag {
		private String			mTag;

		public ActionTag(String tag) {
			mTag = tag;
		}

		public String getTag() {
			return mTag;
		}

		public void setTag(String tag) {
			mTag = tag;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}

			if (obj == this) {
				return true;
			}

			if (obj.getClass() != getClass()) {
				return false;
			}

			ActionTag actionTag = (ActionTag)obj;
			if (actionTag.mTag != null) {
				if (actionTag.mTag.equals(mTag)) {
					return true;
				}
			} else if (mTag == null) {
				return true;
			}

			return false;
		}
	}


	public class ActionResult {
		private Object			mResult;
		private int				mResultCode = ACTION_RESULT_SUCCESS;
		private String			mResultDescription = null;

		public ActionResult(Object result) {
			mResult = result;
		}

		public ActionResult(Object result, int resultCode) {
			mResult = result;
			mResultCode = resultCode;
		}

		public ActionResult(Object result, int resultCode, String resultDescription) {
			mResult = result;
			mResultCode = resultCode;
			mResultDescription = resultDescription;
		}

		public Object getResult() {
			return mResult;
		}

		public void setResult(String result) {
			mResult = result;
		}

		public int getResultCode() {
			return mResultCode;
		}

		public void setResultCode(int resultCode) {
			mResultCode = resultCode;
		}
	}


	protected class ActionHandle {
		private ActionTag		mActionTag;
		private PFWAction		mAction = null;
		private ActionResult	mResult = null;

		public ActionHandle(ActionTag actionTag) {
			mActionTag = actionTag;
		}

		public ActionTag getActionTag() {
			return mActionTag;
		}

		public PFWAction getAction() {
			return mAction;
		}

		public void setAction(PFWAction action) {
			mAction = action;
		}

		public ActionResult getResult() {
			return mResult;
		}

		public void setResult(ActionResult result) {
			mResult = result;
		}
	}


	//--------------------------------------------------
	// public functions

	public PFWActionHandler() {
		mRunningAction = new HashMap<String, PFWAction>();
	}
}
