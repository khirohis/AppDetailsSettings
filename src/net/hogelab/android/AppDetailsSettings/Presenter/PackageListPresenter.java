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
import net.hogelab.android.AppDetailsSettings.ListRow.PackageListRow;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.PFW.PFWPresenter;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListPresenter extends PFWPresenter
		implements PFWModel.PFWModelUpdateListener,
			LoaderCallbacks<List<PackageListRow>> {

	private static final String TAG = PackageListPresenter.class.getSimpleName();

	private static final int	PACKAGE_LIST_LOADER_ID = 1;

	private PackageListModel	mPackageListModel;
	private int					mPackageListModelUpdateHint = 0;

	private Loader<List<PackageListRow>> mLoader = null;


	//--------------------------------------------------
	// public interfaces

	public interface PackageListPresentView extends PFWPresentView {
	}


	//--------------------------------------------------
	// public functions

	public PackageListPresenter(Activity activity, PFWPresentView view) {
		super(activity, view);

		mPackageListModel = AppDetailsSettingsApplication.getApplication().getPackageListModel();
		mPackageListModel.addListener(this);
		mPackageListModelUpdateHint = mPackageListModel.getUpdateHint();
	}


	@Override
	public void onViewCreate() {
		Log.v(TAG, "onViewCreate");
		super.onViewCreate();
	}


	@Override
	public void onViewShow() {
		Log.v(TAG, "onViewShow");
		super.onViewShow();

		int hint = mPackageListModel.getUpdateHint();
		if (hint != mPackageListModelUpdateHint) {
			// update view
			mPackageListModelUpdateHint = hint;
		}
	}


	@Override
	public void onViewHide() {
		Log.v(TAG, "onViewHide");
		super.onViewHide();
	}


	@Override
	public void onViewDestroy() {
		Log.v(TAG, "onViewDestroy");
		super.onViewDestroy();

		mPresentView = null;
	}


	@Override
	public void loadContent() {
		int hint = mPackageListModel.getUpdateHint();
		if (hint == PFWModel.DATA_NONE_HINT) {
			mLoader = mActivity.getLoaderManager().getLoader(PACKAGE_LIST_LOADER_ID);
			mLoader.forceLoad();

			mPackageListModelUpdateHint = hint;
		} else {
			// とってきてセット
		}
	}


	@Override
	public void onModelUpdate(PFWModel model, int updateHint) {
	}


	@Override
	public Loader<List<PackageListRow>> onCreateLoader(int id, Bundle args) {
		return new PackageListLoader(mActivity, mPackageListModel);
	}


	@Override
	public void onLoadFinished(Loader<List<PackageListRow>> arg0, List<PackageListRow> arg1) {
	}


	@Override
	public void onLoaderReset(Loader<List<PackageListRow>> arg0) {
	}


	//--------------------------------------------------
	// private class

	private static class PackageListLoader extends AsyncTaskLoader<List<PackageListRow>> {
		private PackageListModel	mPackageListModel;

		public PackageListLoader(Context context, PackageListModel model) {
			super(context);

			mPackageListModel = model;
		}

		@Override
		public List<PackageListRow> loadInBackground() {
			mPackageListModel.getAllPackages();
			return null;
		}
	}


	//--------------------------------------------------
	// private functions
}
