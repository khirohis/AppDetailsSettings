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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;


//--------------------------------------------------
// class MainActivity

public class MainActivity extends ListActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final String APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";


	private String			mCurrentListupApplicationType = null;


	//--------------------------------------------------
	// public functions

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// from Activity start, onStop
		// to onStart
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mCurrentListupApplicationType = SettingsActivity.getListupApplicationTypeSetting(this);
		setListAdapter(getPackageListAdapter());

		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				return true;
			}
		});
	}


	@Override
	protected void onRestart() {
		// from onStop
		// to onStart
		Log.v(TAG, "onRestart");
		super.onRestart();
	}


	@Override
	protected void onStart() {
		// from onCreate, onRestart
		// to onResume
		Log.v(TAG, "onStart");
		super.onStart();
	}


	@Override
	protected void onResume() {
		// from onStart, onPause
		// to onPause
		Log.v(TAG, "onResume");
		super.onResume();

		String setupApplicationType = SettingsActivity.getListupApplicationTypeSetting(this);
		if (mCurrentListupApplicationType != setupApplicationType) {
			mCurrentListupApplicationType = setupApplicationType;
	        setListAdapter(getPackageListAdapter());
		}
	}


	@Override
	protected void onPause() {
		// from onResume
		// to onResume, onStop
		Log.v(TAG, "onPause");
		super.onPause();
	}


	@Override
	protected void onStop() {
		// from onPause
		// to onDestroy, onRestart, onCreate
		Log.v(TAG, "onStop");
		super.onStop();
	}


	@Override
	protected void onDestroy() {
		// from onStop
		// to Activity shut down
		Log.v(TAG, "onDestroy");
		super.onDestroy();
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

		PackageModel model = AppDetailsSettingsApplication.getApplication().getPackageModel();
		if (mCurrentListupApplicationType.equals("0")) {
			list = model.getSystemPackages();
		} else if (mCurrentListupApplicationType.equals("1")) {
			list = model.getDownloadedPackages();
		} else if (mCurrentListupApplicationType.equals("2")) {
			list = model.getAllPackages();
		}

		return new PackageListAdapter(this, list);
	}


	private void doSettings() {
		Intent intent = new Intent();
		intent.setClassName(getPackageName(), getPackageName() + ".SettingsActivity");
		startActivity(intent);
	}
}
