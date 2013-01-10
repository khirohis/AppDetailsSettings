package net.hogelab.android.AppDetailsSettings.Presenter;

import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
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
		implements PFWModel.PFWModelUpdateListener,
			LoaderCallbacks<List<PackageInfoEntity>> {

	private static final String TAG = PackageListPresenter.class.getSimpleName();

	private static final int	PACKAGE_LIST_LOADER_ID = 1;

	private ListSettingsModel	mListSettingsModel = null;
	private boolean				mListSettingsModelChanged = false;

	private PackageListModel	mPackageListModel = null;

	private Loader<List<PackageInfoEntity>> mLoader = null;


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
	public synchronized void onViewCreate(Activity activity, PFWPresentView view) {
		Log.v(TAG, "onViewCreate");
		super.onViewCreate(activity, view);
	}


	@Override
	public synchronized void onViewShow() {
		Log.v(TAG, "onViewShow");
		super.onViewShow();
	}


	@Override
	public synchronized void onViewHide() {
		Log.v(TAG, "onViewHide");
		super.onViewHide();
	}


	@Override
	public synchronized void onViewDestroy() {
		Log.v(TAG, "onViewDestroy");
		super.onViewDestroy();
	}


	@Override
	public void loadContent() {
		// TODO: 色々手当て
		if (mLoader == null) {
	        mActivity.getLoaderManager().initLoader(PACKAGE_LIST_LOADER_ID, null, this);
			mLoader = mActivity.getLoaderManager().getLoader(PACKAGE_LIST_LOADER_ID);
		}

		if (mListSettingsModelChanged) {
			mLoader.forceLoad();
		} else {
			// とってきてセット
		}
	}

	@Override
	public void forceLoadContent() {
	}


	@Override
	public void onModelUpdate(PFWModel model, int updateHint) {
		if (model instanceof ListSettingsModel) {
			mListSettingsModelChanged = true;
		}
	}


	@Override
	public Loader<List<PackageInfoEntity>> onCreateLoader(int id, Bundle args) {
		return new PackageListLoader(mActivity, mPackageListModel);
	}


	@Override
	public void onLoadFinished(Loader<List<PackageInfoEntity>> arg0, List<PackageInfoEntity> arg1) {
	}


	@Override
	public void onLoaderReset(Loader<List<PackageInfoEntity>> arg0) {
	}


	//--------------------------------------------------
	// private class

	private static class PackageListLoader extends AsyncTaskLoader<List<PackageInfoEntity>> {
		private PackageListModel	mPackageListModel;

		public PackageListLoader(Context context, PackageListModel model) {
			super(context);

			mPackageListModel = model;
		}

		@Override
		public List<PackageInfoEntity> loadInBackground() {
			mPackageListModel.getAllPackages();
			return null;
		}
	}


	//--------------------------------------------------
	// private functions
}
