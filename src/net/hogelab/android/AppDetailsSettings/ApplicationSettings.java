package net.hogelab.android.AppDetailsSettings;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.Log;


//--------------------------------------------------
// class MainActivity

public class ApplicationSettings {

	private static final String TAG = ApplicationSettings.class.getSimpleName();

	private static final String	KEY_PACKAGELIST_LISTING_TYPE = "packagelist_listing_type_key";
	private static final String	DEFAULT_PACKAGELIST_LISTING_TYPE = "0";

	private static final String KEY_LABELCOLOR_VISIBLE_KEY_FORMAT = "labelcolor_visible_%02d";
	private static final boolean DEFAULT_LABLECOLOR_VISIBLE = true;

	private static String[]		sNames;
	private static int[]		sColors;


	//--------------------------------------------------
	// static functions

	public static void initialize(Context context) {
		sNames = context.getResources().getStringArray(R.array.labelcolor_color_names);

		TypedArray colors = context.getResources().obtainTypedArray(R.array.labelcolor_colors);
		int count = colors.length();
		sColors = new int[count];
		for (int i = 0; i < count; i++) {
			sColors[i] = colors.getColor(i, 0xFF000000);
		}

		colors.recycle();
	}


	public static String getPackageListListingTypeSetting() {
		return getSetting(KEY_PACKAGELIST_LISTING_TYPE, DEFAULT_PACKAGELIST_LISTING_TYPE);
	}

	public static void setPackageListListingTypeSetting(String value) {
		setSetting(KEY_PACKAGELIST_LISTING_TYPE, value);
	}


	public static boolean getLabelColorVisibleSetting(int colorIndex) {
		String key = String.format(Locale.JAPAN, KEY_LABELCOLOR_VISIBLE_KEY_FORMAT, colorIndex);
		return getSetting(key, DEFAULT_LABLECOLOR_VISIBLE);
	}

	public static void setLabelColorVisibleSetting(int colorIndex, boolean value) {
		String key = String.format(Locale.JAPAN, KEY_LABELCOLOR_VISIBLE_KEY_FORMAT, colorIndex);
		setSetting(key, value);
	}


	public static String getColorName(int index) {
		if (sNames == null) {
			Log.d(TAG, "Not initialized");
			return "";
		} else if (index < 0 || index > sNames.length) {
			return "";
		}

		return sNames[index];
	}


	public static int getColor(int index) {
		if (sColors != null) {
			return sColors[index];
		}

		Log.d(TAG, "Not initialized");

		return 0xFF000000;
	}


	//--------------------------------------------------
	// private static functions

	private static String getSetting(String key, String defaultValue) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

		return sp.getString(key, defaultValue);
	}

	private static void setSetting(String key, String value) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	private static boolean getSetting(String key, boolean defaultValue) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

		return sp.getBoolean(key, defaultValue);
	}

	private static void setSetting(String key, boolean value) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}
