package net.hogelab.android.AppDetailsSettings.Model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


//--------------------------------------------------
// class PackageModel

public class PackageModel {

	@SuppressWarnings("unused")
	private static final String TAG = PackageModel.class.getSimpleName();

	private Context				mContext = null;
	private List<PackageInfo>	mAllPackages = null;


	//--------------------------------------------------
	// public functions

	public PackageModel(Context context) {
		mContext = context;
		setupAllPackages();
	}


	public void shutdown() {
		mContext = null;
		mAllPackages = null;
	}


	public void resetAllPackages() {
		setupAllPackages();
	}


	public List<PackageInfo> getAllPackages() {
		return mAllPackages;
	}

	public List<PackageInfo> getSystemPackages() {
		List<PackageInfo> packages = new ArrayList<PackageInfo>();

		for (PackageInfo info : mAllPackages) {
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				packages.add(info);
			}
		}

		return packages;
	}

	public List<PackageInfo> getDownloadedPackages() {
		List<PackageInfo> packages = new ArrayList<PackageInfo>();

		for (PackageInfo info : mAllPackages) {
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				packages.add(info);
			}
		}

		return packages;
	}


	//--------------------------------------------------
	// private functions

	private void setupAllPackages() {
		PackageManager pm = mContext.getPackageManager();
		mAllPackages = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
	}
}
