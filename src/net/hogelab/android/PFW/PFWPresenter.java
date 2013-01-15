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
		public void onContentLoading();
		public void onContentLoaded();
		public void onContentLoadError();
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter() {
	}


	public synchronized void onViewCreate(Activity activity, PFWPresentView view) {
		Log.v(TAG, "onViewCreate");

		mActivity = activity;
		mPresentView = view;

		mPresentViewVisible = false;
	}


	public synchronized void onViewShow() {
		Log.v(TAG, "onViewShow");

		mPresentViewVisible = true;
	}


	public synchronized void onViewHide() {
		Log.v(TAG, "onViewHide");

		mPresentViewVisible = false;
	}


	public synchronized void onViewDestroy() {
		Log.v(TAG, "onViewDestroy");

		mActivity = null;
		mPresentView = null;
		mPresentViewVisible = false;
	}


	public boolean isViewVisible() {
		return mPresentViewVisible;
	}


	public abstract void loadContent();
	public abstract void reloadContent();
	//public abstract boolean isLoadingContent();
	//public abstract void cancelLoadingContent();
}
