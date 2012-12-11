package net.hogelab.android.AppDetailsSettings;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//--------------------------------------------------
// class PackageListAdapter

public class PackageListAdapter extends ArrayAdapter<PackageInfo> {

	@SuppressWarnings("unused")
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

		private View			mView = null;
		private PackageInfo		mInfo = null;
		private int				mLabelColor = 0;


		public PackageInfoCell(View cellView) {
			mView = cellView;
		}


		public void setInfo(Context context, PackageInfo info) {
			mInfo = info;
			ApplicationInfo appInfo = info.applicationInfo;
			PackageManager pm = context.getPackageManager();

			PackageModel pmodel = AppDetailsSettingsApplication.getApplication().getPackageModel();
			mLabelColor = pmodel.getLabelColor(mInfo.packageName);
			if (mLabelColor != 0) {
				ImageView labelColor = (ImageView)mView.findViewById(R.id.labelcolor);
				if (labelColor != null) {
					LabelColorModel lcmodel = AppDetailsSettingsApplication.getApplication().getLabelColorModel();
					Drawable drawable = lcmodel.getLabelColorDrawable(mLabelColor);
					labelColor.setImageDrawable(drawable);
					drawable.setCallback(null);
				}
			}

			Drawable icon = appInfo.loadIcon(pm);
			if (icon != null) {
				ImageView applicationIcon = (ImageView)mView.findViewById(R.id.application_icon);
				if (applicationIcon != null) {
					applicationIcon.setImageDrawable(icon);
				}
			}

			TextView packageName = (TextView)mView.findViewById(R.id.package_name);
			if (packageName != null) {
				packageName.setText(mInfo.packageName);
			}

			CharSequence label = appInfo.loadLabel(pm);
			if (label != null) {
				TextView applicationLabel = (TextView)mView.findViewById(R.id.application_label);
				if (applicationLabel != null) {
					applicationLabel.setText(label);
				}
			}
		}
	}
}
