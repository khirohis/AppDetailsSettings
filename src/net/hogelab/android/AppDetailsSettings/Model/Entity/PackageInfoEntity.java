package net.hogelab.android.AppDetailsSettings.Model.Entity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

public class PackageInfoEntity {

	public PackageInfo		mPackageInfo = null;
	public int				mColorIndex = 0;


	public PackageInfoEntity(PackageInfo info) {
		mPackageInfo = info;
		mColorIndex = 0;
	}

	public PackageInfoEntity(PackageInfo info, int colorIndex) {
		mPackageInfo = info;
		mColorIndex = colorIndex;
	}


	public PackageInfo getInfo() {
		return mPackageInfo;
	}

	public ApplicationInfo getApplicationInfo() {
		if (mPackageInfo != null) {
			return mPackageInfo.applicationInfo;
		}

		return null;
	}

	public void setInfo(PackageInfo info) {
		mPackageInfo = info;
	}


	public int getColorIndex() {
		return mColorIndex;
	}

	public void setColorIndex(int colorIndex) {
		mColorIndex = colorIndex;
	}
}
