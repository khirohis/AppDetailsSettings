package net.hogelab.android.AppDetailsSettings.Activity;

import net.hogelab.android.AppDetailsSettings.R;
import net.hogelab.android.AppDetailsSettings.Fragment.ListSettingsFragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
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
        ft.replace(android.R.id.content, new ListSettingsFragment());
        ft.commit();
	}
}
