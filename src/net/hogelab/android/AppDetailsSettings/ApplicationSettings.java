package net.hogelab.android.AppDetailsSettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class ApplicationSettings {

	@SuppressWarnings("unused")
	private static final String TAG = ApplicationSettings.class.getSimpleName();

	private static final String	KEY_LISTUP_APPLICATION_TYPE = "listup_application_type_key";
	private static final String	DEFAULT_LISTUP_APPLICATION_TYPE = "0";


	//--------------------------------------------------
	// static functions

	public static String getListupApplicationTypeSetting() {
		return getStringSetting(KEY_LISTUP_APPLICATION_TYPE, DEFAULT_LISTUP_APPLICATION_TYPE);
	}

	public static void setListupApplicationTypeSetting(String value) {
		setStringSetting(KEY_LISTUP_APPLICATION_TYPE, value);
	}


	private static String getStringSetting(String key, String defaultValue) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

		return sp.getString(key, defaultValue);
	}

	private static void setStringSetting(String key, String value) {
		Context context = AppDetailsSettingsApplication.getApplication();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
