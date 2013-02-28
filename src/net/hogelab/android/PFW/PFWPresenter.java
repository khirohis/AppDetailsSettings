package net.hogelab.android.PFW;

import android.app.Activity;
import android.util.Log;


//--------------------------------------------------
// class PFWPresenter

public abstract class PFWPresenter {

	private static final String TAG = PFWPresenter.class.getSimpleName();


	protected Activity			mActivity = null;
	protected PFWPresentView	mPresentView = null;
	protected boolean			mPresentViewVisible = false;


	//--------------------------------------------------
	// public interfaces

	public interface PFWPresentView {
		public void onStartContentLoading(Object tag, int progressMax);
		public void onProgressContentLoading(Object tag, int currentProgress);

		public void onContentLoaded(Object tag);
		public void onContentLoadError(Object tag);

		public void onContentUpdated(Object tag);
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter() {
	}


	public synchronized void onPresentViewCreate(Activity activity, PFWPresentView view) {
		Log.v(TAG, "onViewCreate");

		mActivity = activity;
		mPresentView = view;

		mPresentViewVisible = false;
	}


	public synchronized void onPresentViewShow() {
		Log.v(TAG, "onViewShow");

		mPresentViewVisible = true;
	}


	public synchronized void onPresentViewHide() {
		Log.v(TAG, "onViewHide");

		mPresentViewVisible = false;
	}


	public synchronized void onPresentViewDestroy() {
		Log.v(TAG, "onViewDestroy");

		mActivity = null;
		mPresentView = null;
		mPresentViewVisible = false;
	}


	public boolean isViewVisible() {
		return mPresentViewVisible;
	}


	public abstract void loadContent(Object tag);
	public abstract void reloadContent(Object tag);

	//public abstract boolean isLoadingContent();
	//public abstract void cancelLoadingContent();
}
