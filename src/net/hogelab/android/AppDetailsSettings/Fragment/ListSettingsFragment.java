package net.hogelab.android.AppDetailsSettings.Fragment;

import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.AppDetailsSettings.ApplicationSettings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.Preference.OnPreferenceChangeListener;


//--------------------------------------------------
// class ListSettingsFragment

public class ListSettingsFragment extends PreferenceFragment {

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

		setListupApplicationTypeSummary(ApplicationSettings.getListupApplicationTypeSetting());
	}

	private void setListupApplicationTypeSummary(CharSequence value) {
		ListPreference lp = getListupApplicationTypePreference();
		int index = lp.findIndexOfValue((String)value);
		lp.setSummary(lp.getEntries()[index]);
	}
}
