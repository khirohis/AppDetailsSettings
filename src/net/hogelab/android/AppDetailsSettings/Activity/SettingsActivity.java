package net.hogelab.android.AppDetailsSettings.Activity;

import net.hogelab.android.AppDetailsSettings.R;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


//--------------------------------------------------
//class PackageDatabase

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("unused")
	private static final String TAG = SettingsActivity.class.getSimpleName();


	//--------------------------------------------------
	// static functions

	public static String getListupApplicationTypeSetting(Context context) {
		CharSequence key = context.getText(R.string.listup_application_type_key);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getString(key.toString(), "0");
	}


	//--------------------------------------------------
	// public functions

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new SettingsFragment());
        ft.commit();
	}


	//--------------------------------------------------
	// class

	public static class SettingsFragment extends PreferenceFragment {

		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			addPreferencesFromResource(R.xml.preferences);
			setupListupApplicationTypePreference();
		}


		private ListPreference getListupApplicationTypePreference() {
			CharSequence key = getText(R.string.listup_application_type_key);
			return (ListPreference)findPreference(key);
		}

		private void setupListupApplicationTypePreference() {
			getListupApplicationTypePreference().setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object value) {
					setListupApplicationTypeSummary((CharSequence)value);
					return true;
				}
			});

			setListupApplicationTypeSummary(getListupApplicationTypeSetting(getActivity()));
		}

		private void setListupApplicationTypeSummary(CharSequence value) {
			ListPreference lp = getListupApplicationTypePreference();
			int index = lp.findIndexOfValue((String)value);
			lp.setSummary(lp.getEntries()[index]);
		}
	}
}
