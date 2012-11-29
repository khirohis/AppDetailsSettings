package net.hogelab.android.AppDetailsSettings;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//--------------------------------------------------
//class PackageListAdapter

public class PackageListAdapter extends ArrayAdapter<PackageInfo> {

	private static final String TAG = PackageListAdapter.class.getSimpleName();


	private LayoutInflater mInflater = null;


	//--------------------------------------------------
	// public functions

	public PackageListAdapter(Context context, List<PackageInfo> objects) {
		super(context, 0, objects);

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PackageInfoCell cell = null;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_packageinfo, parent, false);
			cell = new PackageInfoCell(convertView);
			convertView.setTag(cell);
		} else {
			cell = (PackageInfoCell)convertView.getTag();
		}

		PackageInfo data= getItem(position);
		cell.setInfo(getContext(), data);

		return convertView;
	}


	//--------------------------------------------------
	// class PackageInfoCell

	private static class PackageInfoCell {

		private PackageInfo		mInfo = null;

		private TextView		mPackageName = null;
		private TextView		mApplicationLabel = null;

		public PackageInfoCell(View cellView) {
			Log.d(TAG, "construct PackageInfoCell");

			mPackageName = (TextView)cellView.findViewById(R.id.packageName);
			mApplicationLabel = (TextView)cellView.findViewById(R.id.applicationLabel);
		}


		public void setInfo(Context context, PackageInfo info) {
			mInfo = info;

			mPackageName.setText(mInfo.packageName);

			ApplicationInfo appInfo = info.applicationInfo;
			if (appInfo != null) {
				PackageManager pm = context.getPackageManager();
				CharSequence label = appInfo.loadLabel(pm);
				if (label != null) {
					mApplicationLabel.setText(label);
				}
			}
		}
	}
}
