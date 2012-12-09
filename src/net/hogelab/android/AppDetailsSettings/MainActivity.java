package net.hogelab.android.AppDetailsSettings;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


//--------------------------------------------------
// class MainActivity

public class MainActivity extends ListActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final String APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";


	private PackageModel	mPackageModel = null;
	private String			mCurrentListupApplicationType = null;


	//--------------------------------------------------
	// public functions

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mPackageModel = new PackageModel(this);
		mCurrentListupApplicationType = SettingsActivity.getSetupListupApplicationTypeSetting(this);
        setListAdapter(getPackageListAdapter());
	}

	@Override
	protected void onResume() {
		super.onResume();

		String setupApplicationType = SettingsActivity.getSetupListupApplicationTypeSetting(this);
		if (mCurrentListupApplicationType != setupApplicationType) {
			mCurrentListupApplicationType = setupApplicationType;
	        setListAdapter(getPackageListAdapter());
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

	private PackageListAdapter getPackageListAdapter() {
		List<PackageInfo> list = null;

		if (mCurrentListupApplicationType.equals("0")) {
			list = mPackageModel.getSystemPackages();
		} else if (mCurrentListupApplicationType.equals("1")) {
			list = mPackageModel.getDownloadedPackages();
		} else if (mCurrentListupApplicationType.equals("2")) {
			list = mPackageModel.getAllPackages();
		}

		return new PackageListAdapter(this, list);
	}


	private void doSettings() {
		Intent intent = new Intent();
		intent.setClassName(getPackageName(), getPackageName() + ".SettingsActivity");
		startActivity(intent);
	}
}
