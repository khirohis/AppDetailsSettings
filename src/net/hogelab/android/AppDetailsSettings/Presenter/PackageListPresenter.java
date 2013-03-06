package net.hogelab.android.AppDetailsSettings.Presenter;

import java.util.List;

import android.app.Activity;
import android.util.Log;

import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.Model.ListSettingsModel;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.AppDetailsSettings.Model.Entity.PackageInfoEntity;
import net.hogelab.android.AppDetailsSettings.Presenter.Action.LoadPackageListAction;
import net.hogelab.android.PFW.PFWAction.PFWActionListener;
import net.hogelab.android.PFW.PFWAction.Status;
import net.hogelab.android.PFW.PFWAction;
import net.hogelab.android.PFW.PFWPresenter;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListPresenter extends PFWPresenter
		implements PFWModel.PFWModelListener {

	private static final String TAG = PackageListPresenter.class.getSimpleName();

	private boolean				mListSettingsModelChanged = false;
	private ListSettingsModel	mListSettingsModel = null;

	private boolean				mPackageListModelChanged = false;
	private PackageListModel	mPackageListModel = null;

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
		mPackageListModelChanged = true;
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
		if (mPackageList == null || mListSettingsModelChanged || mPackageListModelChanged) {
			LoadPackageListAction action = new LoadPackageListAction(createLoadPackageListActionListener(), tag);
			PFWAction.executeAction(action);
		} else {
			postContentLoaded(tag, mPackageList);
		}
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

			postContentUpdated(null);
		}
	}


	public void setLabelColor(String packageName, int labelColor) {
		mPackageListModel.setLabelColor(packageName, labelColor);
	}


	//--------------------------------------------------
	// private functions

	private PFWActionListener createLoadPackageListActionListener() {
		return new PFWActionListener() {

			@Override
			public void onActionStart(Object tag, int progressMax) {
				Log.d(TAG, "onActionStart");
			}

			@Override
			public void onActionProgress(Object tag, int currentProgress) {
				Log.d(TAG, "onActionProgress");
			}

			@Override
			public void onActionComplete(Object tag, Status status, Object result) {
				Log.d(TAG, "onActionComplete");

				if (status.equals(Status.SUCCESS) && result != null) {
					postContentLoaded(tag, result);
				} else {
					postContentLoadError(tag, null);
				}
			}
		};
	}
}
