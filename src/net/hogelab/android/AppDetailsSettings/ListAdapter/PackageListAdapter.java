package net.hogelab.android.AppDetailsSettings.ListAdapter;

import java.util.List;

import net.hogelab.android.AppDetailsSettings.ApplicationUtilities;
import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.AppDetailsSettings.Entity.PackageInfoEntity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
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

public class PackageListAdapter extends ArrayAdapter<PackageInfoEntity> {

	@SuppressWarnings("unused")
	private static final String TAG = PackageListAdapter.class.getSimpleName();


	private LayoutInflater mInflater = null;


	//--------------------------------------------------
	// public functions

	public PackageListAdapter(Context context, List<PackageInfoEntity> objects) {
		super(context, 0, objects);

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PackageInfoCell cell = null;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_packagelist, parent, false);
			cell = new PackageInfoCell(convertView);
			convertView.setTag(cell);
		} else {
			cell = (PackageInfoCell)convertView.getTag();
		}

		PackageInfoEntity entity= getItem(position);
		cell.setInfo(getContext(), entity);

		return convertView;
	}


	//--------------------------------------------------
	// class PackageInfoCell

	private static class PackageInfoCell {

		private View			mView = null;
		private PackageInfoEntity mInfo = null;


		public PackageInfoCell(View cellView) {
			mView = cellView;
		}


		public void setInfo(Context context, PackageInfoEntity info) {
			mInfo = info;

			ImageView labelColor = (ImageView)mView.findViewById(R.id.labelcolor);
			if (labelColor != null) {
				int color = mInfo.getColorIndex();
				if (color != 0) {
					Drawable drawable = ApplicationUtilities.getLabelColorDrawable(color);
					labelColor.setImageDrawable(drawable);
					drawable.setCallback(null);
				} else {
					labelColor.setImageDrawable(null);
				}
			}

			PackageManager pm = context.getPackageManager();
			ApplicationInfo appInfo = mInfo.getApplicationInfo();

			ImageView applicationIcon = (ImageView)mView.findViewById(R.id.application_icon);
			if (applicationIcon != null) {
				Drawable icon = appInfo.loadIcon(pm);
				if (icon != null) {
					applicationIcon.setImageDrawable(icon);
				}
			}

			TextView packageName = (TextView)mView.findViewById(R.id.package_name);
			if (packageName != null) {
				packageName.setText(mInfo.getInfo().packageName);
			}

			TextView applicationLabel = (TextView)mView.findViewById(R.id.application_label);
			if (applicationLabel != null) {
				CharSequence label = appInfo.loadLabel(pm);
				if (label != null) {
					applicationLabel.setText(label);
				} else {
					applicationLabel.setText("");
				}
			}
		}
	}
}
