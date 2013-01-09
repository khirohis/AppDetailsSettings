package net.hogelab.android.AppDetailsSettings;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


//--------------------------------------------------
// class MainActivity

public class ApplicationSettings {

	@SuppressWarnings("unused")
	private static final String TAG = ApplicationSettings.class.getSimpleName();

	private static final String	KEY_LISTING_APPLICATION_TYPE = "listing_application_type_key";
	private static final String	DEFAULT_LISTING_APPLICATION_TYPE = "0";

	private static final String KEY_LISTING_LABEL_COLOR_FORMAT = "label_color_%02d";
	private static final boolean DEFAULT_LISTING_LABLE_COLOR = true;


	//--------------------------------------------------
	// static functions

	public static String getListingApplicationTypeSetting() {
		return getSetting(KEY_LISTING_APPLICATION_TYPE, DEFAULT_LISTING_APPLICATION_TYPE);
	}

	public static void setListingApplicationTypeSetting(String value) {
		setSetting(KEY_LISTING_APPLICATION_TYPE, value);
	}

	public static boolean getListingLabelColorSetting(int colorIndex) {
		String key = String.format(Locale.JAPAN, KEY_LISTING_LABEL_COLOR_FORMAT, colorIndex);
		return getSetting(key, DEFAULT_LISTING_LABLE_COLOR);
	}

	public static void setListingLabelColorSetting(int colorIndex, boolean value) {
		String key = String.format(Locale.JAPAN, KEY_LISTING_LABEL_COLOR_FORMAT, colorIndex);
		setSetting(key, value);
	}


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
