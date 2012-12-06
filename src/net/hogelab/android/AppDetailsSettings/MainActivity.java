package net.hogelab.android.AppDetailsSettings;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


//--------------------------------------------------
//class MainActivity

public class MainActivity extends ListActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final String APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";


	private String mCurrentListupApplicationType;


	//--------------------------------------------------
	// public functions

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mCurrentListupApplicationType = SettingsActivity.getSetupListupApplicationTypeSetting(this);

		PackageListAdapter adapter = new PackageListAdapter(this, getSystemPackages());
        setListAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

		String setupApplicationType = SettingsActivity.getSetupListupApplicationTypeSetting(this);
		if (mCurrentListupApplicationType != setupApplicationType) {
			// reset list
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    super.onOptionsItemSelected(item);

	    switch(item.getItemId()){

	    case R.id.menu_settings:
	    	doSettings();
	        return true;
	    }

	    return false;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		PackageInfo info = (PackageInfo)getListAdapter().getItem(position);
		if (info != null) {
			Uri uri = Uri.parse("package:" + info.packageName);
			Intent intent = new Intent(APPLICATION_DETAILS_SETTINGS, uri);
			startActivityForResult(intent, 0);
		}
    }


	//--------------------------------------------------
	// private functions

	private List<PackageInfo> getSystemPackages() {
		List<PackageInfo> systemPackages = new ArrayList<PackageInfo>();

		PackageManager pm = this.getPackageManager();
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo info : packages) {
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				systemPackages.add(info);
			}
		}

		return systemPackages;
	}


	private void doSettings() {
		Intent intent=new Intent();
		intent.setClassName(getPackageName(), getPackageName() + ".SettingsActivity");
		startActivity(intent);
	}
}
