package net.hogelab.android.AppDetailsSettings;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;


//--------------------------------------------------
// class ApplicationUtilities

public class ApplicationUtilities {

	@SuppressWarnings("unused")
	private static final String TAG = ApplicationUtilities.class.getSimpleName();

	private static final int	INTRINSIC_SHAPE_SIZE = 52;



	//--------------------------------------------------
	// static functions

	public static Drawable getLabelColorDrawable(int index) {
		float[] r = new float[] { 4, 4, 4, 4, 4, 4, 4, 4 };
		ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(r, null, null));
		shape.setIntrinsicWidth(INTRINSIC_SHAPE_SIZE);
		shape.setIntrinsicHeight(INTRINSIC_SHAPE_SIZE);
		shape.getPaint().setColor(ApplicationSettings.getColor(index));

		return shape;
	}
}
