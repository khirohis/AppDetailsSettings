package net.hogelab.android.AppDetailsSettings;

import net.hogelab.android.AppDetailsSettings.Model.PackageModel;
import net.hogelab.android.AppDetailsSettings.Model.LabelColorModel;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;


//--------------------------------------------------
//class MainActivity

public class AppDetailsSettingsApplication extends Application {

	private static final String TAG = AppDetailsSettingsApplication.class.getSimpleName();

	private static AppDetailsSettingsApplication sSingleton = null;

	private PackageModel		mPackageModel = null;
	private LabelColorModel		mLabelColorModel = null;


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


	public PackageModel getPackageModel() {
		if (mPackageModel == null) {
			mPackageModel = new PackageModel(this);
		}

		return mPackageModel;
	}

	public void shutdownPackageModel() {
		if (mPackageModel != null) {
			mPackageModel.shutdown();
			mPackageModel = null;
		}
	}


	public LabelColorModel getLabelColorModel() {
		if (mLabelColorModel == null) {
			mLabelColorModel = new LabelColorModel(this);
		}

		return mLabelColorModel;
	}

	public void shutdownLabelColorModel() {
		if (mLabelColorModel != null) {
			mLabelColorModel.shutdown();
			mLabelColorModel = null;
		}
	}
}
