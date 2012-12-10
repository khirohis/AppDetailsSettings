package net.hogelab.android.AppDetailsSettings;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;


//--------------------------------------------------
// class PackageModel

public class PackageModel {

	@SuppressWarnings("unused")
	private static final String TAG = PackageModel.class.getSimpleName();

	private List<PackageInfo>	mAllPackages = null;
	private PackageDatabase		mDb = null;


	//--------------------------------------------------
	// public functions

	public PackageModel(Context context) {
		setupAllPackages(context);

		mDb = new PackageDatabase(context);
		mDb.open();
	}


	public void shutdown() {
		mAllPackages = null;

		mDb.close();
		mDb = null;
	}


	public void resetAllPackages(Context context) {
		setupAllPackages(context);
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


	public Drawable getLabelDrawable(int label_color) {
		float[] r = new float[] { 4, 4, 4, 4, 4, 4, 4, 4 };
		ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(r, null, null));
		shape.setIntrinsicWidth(52);
		shape.setIntrinsicHeight(52);
		shape.getPaint().setColor(Color.RED);
		return shape;
	}

	//--------------------------------------------------
	// private functions

	private void setupAllPackages(Context context) {
		PackageManager pm = context.getPackageManager();
		mAllPackages = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
	}
}