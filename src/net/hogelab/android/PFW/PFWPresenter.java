package net.hogelab.android.PFW;

import android.util.Log;


//--------------------------------------------------
// class PFWPresenter

public abstract class PFWPresenter {

	private static final String TAG = PFWPresenter.class.getSimpleName();


	protected PFWPassiveView presentView;


	//--------------------------------------------------
	// public interfaces

	public interface PFWPassiveView {
		public void onUpdatingContent(Object tag);
		public void onUpdatedContent(Object tag);
		public void onUpdateContentError(Object tag);
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter(PFWPassiveView view) {
		presentView = view;
	}


	public void onViewCreate() {
		Log.v(TAG, "onViewCreate");
	}

	public void onViewShow() {
		Log.v(TAG, "onViewShow");
	}

	public void onViewHide() {
		Log.v(TAG, "onViewHide");
	}

	public void onViewDestroy() {
		Log.v(TAG, "onViewDestroy");

		presentView = null;
	}


	public abstract void updateContent(Object tag);
}
