package net.hogelab.android.AppDetailsSettings;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//--------------------------------------------------
//class PackageListAdapter

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


		public PackageInfoCell(View cellView) {
			mView = cellView;
		}


		public void setInfo(Context context, PackageInfo info) {
			mInfo = info;

			TextView packageName = (TextView)mView.findViewById(R.id.packageName);
			if (packageName != null) {
				packageName.setText(mInfo.packageName);
			}

			ApplicationInfo appInfo = info.applicationInfo;
			if (appInfo != null) {
				PackageManager pm = context.getPackageManager();

				TextView applicationLabel = (TextView)mView.findViewById(R.id.applicationLabel);
				if (applicationLabel != null) {
					CharSequence label = appInfo.loadLabel(pm);
					if (label != null) {
						applicationLabel.setText(label);
					}
				}

				ImageView applicationIcon = (ImageView)mView.findViewById(R.id.applicationIcon);
				if (applicationIcon != null) {
					Drawable icon = appInfo.loadIcon(pm);
					if (icon != null) {
						applicationIcon.setImageDrawable(icon);
					}
				}

				ImageView colorLabel = (ImageView)mView.findViewById(R.id.colorLabel);
				if (colorLabel != null) {
			        float[] r = new float[] { 10, 10, 10, 10, 10, 10, 10, 10 };
					ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(r, null, null));
					//shape.setBounds(0, 0, 52, 52);
					shape.setIntrinsicWidth(100);
					shape.setIntrinsicHeight(100);
					shape.getPaint().setColor(Color.RED);
					colorLabel.setImageDrawable(shape);
				}
			}
		}
	}
}
