package net.hogelab.android.AppDetailsSettings.Model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import net.hogelab.android.AppDetailsSettings.Model.Entity.PackageInfoEntity;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListModel extends PFWModel {

	private static final String TAG = PackageListModel.class.getSimpleName();

	private Context				mContext = null;

	private LabelColorDatabase	mDb = null;

	private List<PackageInfo>	mAllPackages = null;


	//--------------------------------------------------
	// public functions

	public PackageListModel(Context context) {
		mContext = context;

		mDb = new LabelColorDatabase(mContext);

		mAllPackages = null;
	}


	public synchronized List<PackageInfo> getAllPackages() {
		if (mAllPackages == null) {
			mAllPackages = loadAllPackages();

			updated();
		}

		return mAllPackages;
	}

	public synchronized void resetAllPackages() {
		mAllPackages = null;
	}


	public List<PackageInfoEntity> selectSystemPackages() {
		List<PackageInfo> allPackages = getAllPackages();
		List<PackageInfoEntity> packages = new ArrayList<PackageInfoEntity>();

		for (PackageInfo info : allPackages) {
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				int colorIndex = getLabelColor(info.packageName);
				packages.add(new PackageInfoEntity(info, colorIndex));
			}
		}

		return packages;
	}

	public List<PackageInfoEntity> selectDownloadedPackages() {
		List<PackageInfo> allPackages = getAllPackages();
		List<PackageInfoEntity> packages = new ArrayList<PackageInfoEntity>();

		for (PackageInfo info : allPackages) {
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				int colorIndex = getLabelColor(info.packageName);
				packages.add(new PackageInfoEntity(info, colorIndex));
			}
		}

		return packages;
	}

	public List<PackageInfoEntity> selectBothPackages() {
		List<PackageInfo> allPackages = getAllPackages();
		List<PackageInfoEntity> packages = new ArrayList<PackageInfoEntity>();

		for (PackageInfo info : allPackages) {
			int colorIndex = getLabelColor(info.packageName);
			packages.add(new PackageInfoEntity(info, colorIndex));
		}

		return packages;
	}


	public int getLabelColor(String packageName) {
		return mDb.get(packageName);
	}

	public boolean setLabelColor(String packageName, int labelColor) {
		if (mDb.set(packageName, labelColor)) {
			// TODO: set labelColor to mAllPackages
			return true;
		}

		return false;
	}


	//--------------------------------------------------
	// private functions

	protected void onNoListener() {
		Log.v(TAG, "onNoListener");

		if (mDb != null) {
			mDb.close();
		}
	}


	private List<PackageInfo> loadAllPackages() {
		PackageManager pm = mContext.getPackageManager();
		List<PackageInfo> packages = pm.getInstalledPackages(
				PackageManager.GET_UNINSTALLED_PACKAGES |
				PackageManager.GET_DISABLED_COMPONENTS);

		return packages;
	}
}
