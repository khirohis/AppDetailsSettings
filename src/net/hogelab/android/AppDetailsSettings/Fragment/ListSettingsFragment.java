package net.hogelab.android.AppDetailsSettings.Fragment;

import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.AppDetailsSettings.ApplicationSettings;
import net.hogelab.android.AppDetailsSettings.Model.ListSettingsModel;

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
		setupListingApplicationTypePreference();
	}


	private ListPreference getListingApplicationTypePreference() {
		CharSequence key = getText(R.string.packagelist_listing_type_key);
		return (ListPreference)findPreference(key);
	}

	private void setupListingApplicationTypePreference() {
		getListingApplicationTypePreference().setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object value) {
				setListingApplicationTypeSummary((CharSequence)value);

				ListSettingsModel lsm = AppDetailsSettingsApplication.getApplication().getListSettingsModel();
				lsm.onPackageListListingTypeChange();

				return true;
			}
		});

		setListingApplicationTypeSummary(ApplicationSettings.getPackageListListingTypeSetting());
	}

	private void setListingApplicationTypeSummary(CharSequence value) {
		ListPreference lp = getListingApplicationTypePreference();
		int index = lp.findIndexOfValue((String)value);
		lp.setSummary(lp.getEntries()[index]);
	}
}
