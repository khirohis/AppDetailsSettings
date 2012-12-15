package net.hogelab.android.AppDetailsSettings.Model;

import net.hogelab.android.AppDetailsSettings.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;


//--------------------------------------------------
//class LabelColorModel

public class LabelColorModel {

	@SuppressWarnings("unused")
	private static final String TAG = LabelColorModel.class.getSimpleName();

	private static final int	INTRINSIC_SHAPE_SIZE = 52;

	private Context				mContext = null;
	private LabelColorDatabase	mDb = null;
	private String[]			mNames = null;
	private TypedArray			mColors = null;


	public LabelColorModel(Context context) {
		mContext = context;

		mDb = new LabelColorDatabase(mContext);
		mDb.open();

		mNames = mContext.getResources().getStringArray(R.array.labelcolor_color_names);
		mColors = mContext.getResources().obtainTypedArray(R.array.labelcolor_colors);
	}

	public void shutdown() {
		mContext = null;

		mDb.close();
		mDb = null;

		mNames = null;
		mColors = null;
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


	public Drawable getLabelColorDrawable(int index) {
		float[] r = new float[] { 4, 4, 4, 4, 4, 4, 4, 4 };
		ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(r, null, null));
		shape.setIntrinsicWidth(INTRINSIC_SHAPE_SIZE);
		shape.setIntrinsicHeight(INTRINSIC_SHAPE_SIZE);
		shape.getPaint().setColor(getColor(index));

		return shape;
	}
}