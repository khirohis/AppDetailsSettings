package net.hogelab.android.AppDetailsSettings.Presenter;

import android.util.Log;
import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.PFW.PFWPresenter;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListPresenter extends PFWPresenter implements PFWModel.PFWModelUpdateListener {

	private static final String TAG = PackageListPresenter.class.getSimpleName();


	private PackageListModel packageListModel;
	private int packageListModelUpdateHint = 0;


	//--------------------------------------------------
	// public functions

	public PackageListPresenter(PFWPassiveView view) {
		super(view);

		packageListModel = AppDetailsSettingsApplication.getApplication().getPackageListModel();
		packageListModel.addListener(this);
		packageListModelUpdateHint = packageListModel.getUpdateHint();
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

		presentView = null;
	}


	@Override
	public void updateContent(Object tag) {
	}


	@Override
	public void onModelUpdate(PFWModel model, int updateHint) {
	}


	//--------------------------------------------------
	// private functions
}
