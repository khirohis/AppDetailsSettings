package net.hogelab.android.AppDetailsSettings;

import net.hogelab.android.AppDetailsSettings.Model.ListSettingsModel;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.AppDetailsSettings.Model.LabelColorModel;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;


//--------------------------------------------------
// class AppDetailsSettingsApplication

public class AppDetailsSettingsApplication extends Application {

	private static final String TAG = AppDetailsSettingsApplication.class.getSimpleName();

	private static AppDetailsSettingsApplication sSingleton = null;

	private ListSettingsModel	mListSettingsModel = null;
	private PackageListModel	mPackageListModel = null;


	// TODO: deprecate
	private LabelColorModel		labelColorModel = null;


	//--------------------------------------------------
	// static functions

	public static AppDetailsSettingsApplication getApplication() {
		return sSingleton;
	}


	//--------------------------------------------------
	// public functions

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");

		if (sSingleton == null) {
			sSingleton = this;
		}
	}
 

	@Override
	public void onTerminate() {
		Log.v(TAG, "onTerminate");
	}


	@Override
	public void onLowMemory() {
		Log.v(TAG, "onLowMemory");
	}


	@Override
	public void onTrimMemory(int level) {
		Log.v(TAG, "onTrimMemory:level=" + level);
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.v(TAG, "onCreate:newConfig=" + newConfig.toString());
	}


	public ListSettingsModel getListSettingsModel() {
		if (mListSettingsModel == null) {
			mListSettingsModel = new ListSettingsModel();
		}

		return mListSettingsModel;
	}

	public PackageListModel getPackageListModel() {
		if (mPackageListModel == null) {
			mPackageListModel = new PackageListModel(this);
		}

		return mPackageListModel;
	}


	// TODO: deprecate
	public LabelColorModel getLabelColorModel() {
		if (labelColorModel == null) {
			labelColorModel = new LabelColorModel(this);
		}

		return labelColorModel;
	}

	// TODO: deprecate
	public void shutdownLabelColorModel() {
		if (labelColorModel != null) {
			labelColorModel.shutdown();
			labelColorModel = null;
		}
	}
}
