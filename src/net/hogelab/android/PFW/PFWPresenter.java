package net.hogelab.android.PFW;

import android.app.Activity;
import android.util.Log;


//--------------------------------------------------
// class PFWPresenter

public abstract class PFWPresenter {

	private static final String TAG = PFWPresenter.class.getSimpleName();


	protected Activity			mActivity;

	protected PFWPresentView	mPresentView;
	protected boolean			mPresentViewVisible;


	//--------------------------------------------------
	// public interfaces

	public interface PFWPresentView {
		public void onContentLoading();
		public void onContentLoaded();
		public void onContentLoadError();
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter(Activity activity, PFWPresentView view) {
		mActivity = activity;

		mPresentView = view;
		mPresentViewVisible = false;
	}


	public void onViewCreate() {
		Log.v(TAG, "onViewCreate");
	}


	public void onViewShow() {
		Log.v(TAG, "onViewShow");

		mPresentViewVisible = true;
	}


	public void onViewHide() {
		Log.v(TAG, "onViewHide");

		mPresentViewVisible = false;
	}


	public void onViewDestroy() {
		Log.v(TAG, "onViewDestroy");

		mPresentView = null;
	}


	public abstract void loadContent();
}
