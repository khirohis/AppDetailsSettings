package net.hogelab.android.AppDetailsSettings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//--------------------------------------------------
// class PackageDatabase

public class PackageDatabase extends SQLiteOpenHelper {

	@SuppressWarnings("unused")
	private static final String TAG = PackageDatabase.class.getSimpleName();

	private static final String DATABASE_NAME = "packagedb";
	private static final int	DATABASE_VERSION = 1;

	private static final String	SQL_CREATE =
		"CREATE TABLE label_info (" +
		"id INTEGER PRIMARY KEY AUTOINCREMENT," +
		"package_name TEXT" +
		"label_color INTEGER" +
		");";


	//--------------------------------------------------
	// public functions

	public PackageDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(SQL_CREATE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing
	}
}
