package net.hogelab.android.AppDetailsSettings.Model;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.ApplicationSettings;
import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class ListSettingsModel extends PFWModel {

	@SuppressWarnings("unused")
	private static final String TAG = ListSettingsModel.class.getSimpleName();


	//--------------------------------------------------
	// public functions

	public ListSettingsModel() {
		/*
		OnSharedPreferenceChangeListener changeListener = new OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			}
		};
		registerOnSharedPreferenceChangeListener(changeListener);
		*/
	}


	public String getPackageListListingType() {
		return ApplicationSettings.getPackageListListingTypeSetting();
	}

	public void setPackageListListingType(String value) {
		ApplicationSettings.setPackageListListingTypeSetting(value);

		updated();
	}


	public boolean getLabelColorVisible(int colorIndex) {
		return ApplicationSettings.getLabelColorVisibleSetting(colorIndex);
	}

	public void setLabelColorVisible(int colorIndex, boolean value) {
		ApplicationSettings.setLabelColorVisibleSetting(colorIndex, value);

		updated();
	}


	//--------------------------------------------------
	// private functions
}
