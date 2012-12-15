package net.hogelab.android.AppDetailsSettings.Activity;

import net.hogelab.android.AppDetailsSettings.Fragment.ListSettingsFragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;


//--------------------------------------------------
//class PackageDatabase

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("unused")
	private static final String TAG = SettingsActivity.class.getSimpleName();


	//--------------------------------------------------
	// public functions

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new ListSettingsFragment());
        ft.commit();
	}
}
