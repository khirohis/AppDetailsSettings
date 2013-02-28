package net.hogelab.android.AppDetailsSettings.Presenter;

import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.Entity.PackageInfoEntity;
import net.hogelab.android.AppDetailsSettings.Model.ListSettingsModel;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.PFW.PFWPresenter;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListPresenter extends PFWPresenter
		implements PFWModel.PFWModelListener,
			LoaderCallbacks<List<PackageInfoEntity>> {

	private static final String TAG = PackageListPresenter.class.getSimpleName();

	private static final int	PACKAGE_LIST_LOADER_ID = 1;

	private boolean				mListSettingsModelChanged = false;
	private ListSettingsModel	mListSettingsModel = null;

	private PackageListModel	mPackageListModel = null;

	private Loader<List<PackageInfoEntity>> mLoader = null;
	private List<PackageInfoEntity> mPackageList = null;


	//--------------------------------------------------
	// public interfaces

	public interface PackageListPresentView extends PFWPresentView {
	}


	//--------------------------------------------------
	// public functions

	public PackageListPresenter() {
		super();

		mListSettingsModel = AppDetailsSettingsApplication.getApplication().getListSettingsModel();
		mListSettingsModel.addListener(this);
		mListSettingsModelChanged = true;

		mPackageListModel = AppDetailsSettingsApplication.getApplication().getPackageListModel();
		mPackageListModel.addListener(this);
	}


	@Override
	public synchronized void onPresentViewCreate(Activity activity, PFWPresentView view) {
		Log.v(TAG, "onViewCreate");
		super.onPresentViewCreate(activity, view);
	}


	@Override
	public synchronized void onPresentViewShow() {
		Log.v(TAG, "onViewShow");
		super.onPresentViewShow();

		if (mListSettingsModelChanged) {
			loadContent(null);
		}
	}


	@Override
	public synchronized void onPresentViewHide() {
		Log.v(TAG, "onViewHide");
		super.onPresentViewHide();
	}


	@Override
	public synchronized void onPresentViewDestroy() {
		Log.v(TAG, "onViewDestroy");
		super.onPresentViewDestroy();

		mListSettingsModel.removeListener(this);
		mListSettingsModel = null;

		mPackageListModel.removeListener(this);
		mPackageListModel = null;
	}


	@Override
	public void loadContent(Object tag) {
		if (mPackageList != null && !mListSettingsModelChanged) {
			doContentLoaded(null);
		}

		if (mLoader != null) {
	        mActivity.getLoaderManager().restartLoader(PACKAGE_LIST_LOADER_ID, null, this);
		} else {
	        mActivity.getLoaderManager().initLoader(PACKAGE_LIST_LOADER_ID, null, this);
			mLoader = mActivity.getLoaderManager().getLoader(PACKAGE_LIST_LOADER_ID);
		}

		mLoader.forceLoad();
	}

	@Override
	public void reloadContent(Object tag) {
		mPackageListModel.resetAllPackages();
		loadContent(tag);
	}


	@Override
	public void onModelUpdate(PFWModel model, int updateHint) {
		if (model instanceof ListSettingsModel) {
			mListSettingsModelChanged = true;
		}
	}


	@Override
	public Loader<List<PackageInfoEntity>> onCreateLoader(int id, Bundle args) {
		Log.v(TAG, "onCreateLoader");
		return new PackageListLoader(mActivity);
	}


	@Override
	public void onLoadFinished(
			Loader<List<PackageInfoEntity>> loader,
			List<PackageInfoEntity> packages) {
		Log.v(TAG, "onLoadFinished");

		mPackageList = packages;
		doContentLoaded(null);
	}


	@Override
	public void onLoaderReset(Loader<List<PackageInfoEntity>> loader) {
		Log.v(TAG, "onLoaderReset");
	}


	public void setLabelColor(String packageName, int labelColor) {
		mPackageListModel.setLabelColor(packageName, labelColor);
	}


	//--------------------------------------------------
	// private class

	private static class PackageListLoader extends AsyncTaskLoader<List<PackageInfoEntity>> {
		public PackageListLoader(Context context) {
			super(context);
		}

		@Override
		public List<PackageInfoEntity> loadInBackground() {
			PackageListModel packageListModel = AppDetailsSettingsApplication.getApplication().getPackageListModel();
			List<PackageInfoEntity> packages = packageListModel.selectBothPackages();

			return packages;
		}
	}


	//--------------------------------------------------
	// private functions

	public void doContentLoaded(Object tag) {
		if (isViewVisible()) {
			final PFWPresentView presentView = mPresentView;
			final Object ftag = tag;

			if (presentView != null) {
				Handler handler = new Handler(mActivity.getMainLooper());
				handler.post(new Runnable() {
					@Override
					public void run() {
						mPresentView.onContentLoaded(ftag);
					}
				});
			}
		}
	}
}
