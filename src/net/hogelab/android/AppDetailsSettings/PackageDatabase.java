package net.hogelab.android.AppDetailsSettings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//--------------------------------------------------
// class PackageDatabase

public class PackageDatabase extends SQLiteOpenHelper {

	@SuppressWarnings("unused")
	private static final String TAG = PackageDatabase.class.getSimpleName();

	private static final String DATABASE_NAME = "packagedb";
	private static final int	DATABASE_VERSION = 1;

	private static final String	TABLE_NAME_LABEL_INFO = "label_info";
	private static final String	FIELD_NAME_ID = "id";
	private static final String FIELD_NAME_PACKAGE_NAME = "package_name";
	private static final String FIELD_NAME_PACKAGE_NAME_INDEX = "package_name_index";
	private static final String FIELD_NAME_LABEL_COLOR = "label_color";

	private static final String	SQL_LABEL_INFO_CREATE_TABLE =
		"CREATE TABLE " + TABLE_NAME_LABEL_INFO + " (" +
		FIELD_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
		FIELD_NAME_PACKAGE_NAME + " TEXT," +
		FIELD_NAME_LABEL_COLOR + " INTEGER" +
		");";

	private static final String	SQL_LABEL_INFO_CREATE_INDEX_PACKAGE_NAME =
		"CREATE INDEX " + FIELD_NAME_PACKAGE_NAME_INDEX +
		" ON " + TABLE_NAME_LABEL_INFO +
		" (" + FIELD_NAME_PACKAGE_NAME + ")";


	private SQLiteDatabase mDb = null;


	//--------------------------------------------------
	// public functions

	public PackageDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(SQL_LABEL_INFO_CREATE_TABLE);
            db.execSQL(SQL_LABEL_INFO_CREATE_INDEX_PACKAGE_NAME);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing
	}


	public void open() {
		getDb();
	}

	public void close() {
		if (mDb != null) {
			mDb.close();
			mDb = null;
		}
	}


	public boolean set(String packageName, int labelColor) {
		boolean result = false;

		int id = 0;

		String[] fields = new String[] { FIELD_NAME_ID, FIELD_NAME_LABEL_COLOR };
		String where = FIELD_NAME_PACKAGE_NAME + " = ?";
		String[] params = new String[] { packageName };

		SQLiteDatabase db = getDb();
		Cursor cursor = db.query(TABLE_NAME_LABEL_INFO, fields, where, params, null, null, null);
		if (cursor != null) {
			try {
				if (cursor.moveToFirst()) {
	               id = cursor.getInt(cursor.getColumnIndex(FIELD_NAME_ID));
				}
	        } finally {
        		cursor.close();
        	}
		}

		if (id != 0) {
			result = update(id, labelColor);
		} else {
			result = insert(packageName, labelColor);
		}

		return result;
	}

	public int get(String packageName) {
		int result = 0;

		String[] fields = new String[] { FIELD_NAME_ID, FIELD_NAME_LABEL_COLOR };
		String where = FIELD_NAME_PACKAGE_NAME + " = ?";
		String[] params = new String[] { packageName };

		SQLiteDatabase db = getDb();
		Cursor cursor = db.query(TABLE_NAME_LABEL_INFO, fields, where, params, null, null, null);
		if (cursor != null) {
			try {
				if (cursor.moveToFirst()) {
					result = cursor.getInt(cursor.getColumnIndex(FIELD_NAME_LABEL_COLOR));
				}
	        } finally {
        		cursor.close();
        	}
		}

		return result;
	}



	//--------------------------------------------------
	// private functions

	private SQLiteDatabase getDb() {
		if (mDb == null) {
			mDb = getWritableDatabase();
		}

		return mDb;
	}


	private boolean insert(String packageName, int labelColor) {
		boolean result = false;

		SQLiteDatabase db = getDb();
		db.beginTransaction();

		try {
			ContentValues values = new ContentValues();
			values.put(FIELD_NAME_PACKAGE_NAME, packageName);
			values.put(FIELD_NAME_LABEL_COLOR, labelColor);

			db.insert(TABLE_NAME_LABEL_INFO, null, values);
			db.setTransactionSuccessful();

			result = true;
		} finally {
			db.endTransaction();
		}

        return result;
	}


	private boolean update(int id, int labelColor) {
		boolean result = false;

		SQLiteDatabase db = getDb();
		db.beginTransaction();

		try {
			ContentValues values = new ContentValues();
			values.put(FIELD_NAME_LABEL_COLOR, labelColor);

			String[] params = new String[] { Integer.toString(id) };

			db.update(TABLE_NAME_LABEL_INFO, values, "id = ?", params);
			db.setTransactionSuccessful();

			result = true;
		} finally {
			db.endTransaction();
		}

		return result;
	}
}
