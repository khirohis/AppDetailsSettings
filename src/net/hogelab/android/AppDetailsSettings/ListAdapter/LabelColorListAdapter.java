package net.hogelab.android.AppDetailsSettings.ListAdapter;

import java.util.List;

import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.AppDetailsSettings.Model.LabelColorModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//--------------------------------------------------
// class PackageListAdapter
public class LabelColorListAdapter extends ArrayAdapter<Integer> {

	@SuppressWarnings("unused")
	private static final String TAG = PackageListAdapter.class.getSimpleName();


	private LayoutInflater mInflater = null;


	//--------------------------------------------------
	// public functions

	public LabelColorListAdapter(Context context, List<Integer> objects) {
		super(context, 0, objects);

		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LabelColorCell cell = null;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_labelcolor, parent, false);
			cell = new LabelColorCell(convertView);
			convertView.setTag(cell);
		} else {
			cell = (LabelColorCell)convertView.getTag();
		}

		int data = getItem(position);
		cell.setInfo(getContext(), data);

		return convertView;
	}


	//--------------------------------------------------
	// class LabelColorCell

	private static class LabelColorCell {

		private View			mView = null;
		private int				mData = 0;


		public LabelColorCell(View cellView) {
			mView = cellView;
		}


		public void setInfo(Context context, int data) {
			mData = data;

			LabelColorModel model = AppDetailsSettingsApplication.getApplication().getLabelColorModel();

			ImageView labelColor = (ImageView)mView.findViewById(R.id.labelcolor_sample);
			if (labelColor != null) {
				Drawable drawable = model.getLabelColorDrawable(mData);
				labelColor.setImageDrawable(drawable);
				drawable.setCallback(null);
				
			}

			TextView labelColorName = (TextView)mView.findViewById(R.id.labelcolor_name);
			if (labelColorName != null) {
				String colorName = model.getColorName(mData);
				labelColorName.setText(colorName);
			}
		}
	}
}
