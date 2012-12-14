package net.hogelab.android.AppDetailsSettings.Activity;

import net.hogelab.android.AppDetailsSettings.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


//--------------------------------------------------
// class MainActivity

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();


	//--------------------------------------------------
	// public functions

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// from Activity starts, onStop
		// to onStart
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
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
		// to Activity is running
		Log.v(TAG, "onResume");
		super.onResume();
	}


	@Override
	protected void onPause() {
		// from Activity running
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
		// to Activity is shut down
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


	//--------------------------------------------------
	// private functions

	private void doSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		intent.setAction(Intent.ACTION_VIEW);
		startActivity(intent);
	}
}
