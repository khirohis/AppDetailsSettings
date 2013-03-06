package net.hogelab.android.PFW;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;


//--------------------------------------------------
// class PFWPresenter

public abstract class PFWPresenter {

	private static final String TAG = PFWPresenter.class.getSimpleName();

	protected Activity			mActivity = null;
	protected PFWPresentView	mPresentView = null;
	protected boolean			mPresentViewVisible = false;

	protected Map<String, PFWAction> mRunningAction = null;


	//--------------------------------------------------
	// public interfaces

	public interface PFWPresentView {
		public void onContentLoadingStart(Object tag, int progressMax);
		public void onContentLoadingProgress(Object tag, int currentProgress);

		public void onContentLoaded(Object tag, Object content);
		public void onContentLoadError(Object tag, Object error);

		public void onContentUpdated(Object tag);
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter() {
		mRunningAction = new HashMap<String, PFWAction>();
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


	public synchronized boolean isViewVisible() {
		return mPresentViewVisible;
	}


	public abstract void loadContent(Object tag);
	public abstract void reloadContent(Object tag);

	//public abstract boolean isLoadingContent();
	//public abstract void cancelLoadingContent();


	//--------------------------------------------------
	// protected functions

	protected synchronized void postContentLoadingStart(Object tag, int progressMax) {
		if (mActivity != null && mPresentView != null) {
			final Object ft = tag;
			final int fpm = progressMax;

			Handler handler = new Handler(mActivity.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					onContentLoadingStart(ft, fpm);
				}
			});
		}
	}

	protected synchronized void onContentLoadingStart(Object tag, int progressMax) {
		if (mActivity != null && mPresentView != null) {
			mPresentView.onContentLoadingStart(tag, progressMax);
		}
	}


	protected synchronized void postContentLoadingProgress(Object tag, int currentProgress) {
		if (mActivity != null && mPresentView != null) {
			final PFWPresentView fpv = mPresentView;
			final Object ft = tag;
			final int fcp = currentProgress;

			Handler handler = new Handler(mActivity.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					fpv.onContentLoadingProgress(ft, fcp);
				}
			});
		}
	}


	protected synchronized void postContentLoaded(Object tag, Object content) {
		if (mActivity != null && mPresentView != null) {
			final PFWPresentView fpv = mPresentView;
			final Object ft = tag;
			final Object fc = content;

			Handler handler = new Handler(mActivity.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					fpv.onContentLoaded(ft, fc);
				}
			});
		}
	}

	protected synchronized void postContentLoadError(Object tag, Object error) {
		if (mActivity != null && mPresentView != null) {
			final PFWPresentView fpv = mPresentView;
			final Object ft = tag;
			final Object fe = error;

			Handler handler = new Handler(mActivity.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					fpv.onContentLoadError(ft, fe);
				}
			});
		}
	}


	protected synchronized void postContentUpdated(Object tag) {
		if (mActivity != null && mPresentView != null) {
			final PFWPresentView fpv = mPresentView;
			final Object ft = tag;

			Handler handler = new Handler(mActivity.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					fpv.onContentUpdated(ft);
				}
			});
		}
	}
}
