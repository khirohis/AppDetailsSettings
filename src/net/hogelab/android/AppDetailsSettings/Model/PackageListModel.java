package net.hogelab.android.AppDetailsSettings.Model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.util.Log;
import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class PackageListModel extends PFWModel {

	private static final String TAG = PackageListModel.class.getSimpleName();

	private Context				mContext = null;

	private List<PackageInfo>	mAllPackages = null;

	private LabelColorDatabase	mDb = null;
	private String[]			mNames = null;
	private TypedArray			mColors = null;


	//--------------------------------------------------
	// public functions

	public PackageListModel(Context context) {
		mContext = context;

		mAllPackages = new ArrayList<PackageInfo>();

		mDb = new LabelColorDatabase(mContext);

		mNames = mContext.getResources().getStringArray(R.array.labelcolor_color_names);
		mColors = mContext.getResources().obtainTypedArray(R.array.labelcolor_colors);
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


	public int getLabelColor(String packageName) {
		return mDb.get(packageName);
	}

	public boolean setLabelColor(String packageName, int labelColor) {
		return mDb.set(packageName, labelColor);
	}


	public String getColorName(int index) {
		if (index < 0 || index > mNames.length) {
			return "";
		}

		return mNames[index];
	}


	public int getColor(int index) {
		return mColors.getColor(index, 0xFF000000);
	}


	//--------------------------------------------------
	// private functions

	protected void onNoListener() {
		Log.v(TAG, "onNoListener");

		if (mDb != null) {
			mDb.close();
		}
	}


	private void setupAllPackages() {
		PackageManager pm = mContext.getPackageManager();
		mAllPackages = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);

		updated();
	}
}
