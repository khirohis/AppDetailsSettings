package net.hogelab.android.AppDetailsSettings;

import java.util.List;

import android.content.Context;
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
			convertView = mInflater.inflate(R.layout.list_item_packageinfo, parent, false);
			cell = new LabelColorCell(convertView);
			convertView.setTag(cell);
		} else {
			cell = (LabelColorCell)convertView.getTag();
		}

		int data= getItem(position);
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

			ImageView labelColor = (ImageView)mView.findViewById(R.id.labelColorSample);
			if (labelColor != null) {
				PackageModel model = AppDetailsSettingsApplication.getApplication().getPackageModel();
				labelColor.setImageDrawable(model.getLabelDrawable(data));
			}

			TextView labelColorName = (TextView)mView.findViewById(R.id.labelColorName);
			if (labelColorName != null) {
				labelColorName.setText(Integer.toString(mData));
			}
		}
	}
}
