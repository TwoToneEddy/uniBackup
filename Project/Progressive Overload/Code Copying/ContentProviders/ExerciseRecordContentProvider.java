package com.example.progressiveoverload.ContentProviders;

import com.example.progressiveoverload.*;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

//Just workout record so far
public class ExerciseRecordContentProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.progressiveoverload.ContentProviders.exerciserecordprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/exerciserecordprovider");

	private SQLiteDatabase exerciseRecordDB;
	private static final String EXERCISE_RECORD_PROVIDER = "exerciserecordprovider";
	public static final String KEY_EXERCISE_RECORD_ID = "_id";
	public static final String KEY_EXERCISE_ID = "Exercise_id";
	public static final String KEY_DATE = "Date";
	public static final String KEY_SET_1 = "1st_set";
	public static final String KEY_SET_2 = "2nd_set";
	public static final String KEY_SET_3 = "3rd_set";
	public static final String KEY_SET_4 = "4th_set";
	public static final String KEY_SET_5 = "5th_set";
	public static final String KEY_WEIGHT_1 = "1st_weight";
	public static final String KEY_WEIGHT_2 = "2nd_weight";
	public static final String KEY_WEIGHT_3 = "3rd_weight";
	public static final String KEY_WEIGHT_4 = "4th_weight";
	public static final String KEY_WEIGHT_5 = "5th_weight";

	private static final int DAY = 1;
	private static final int GROUP = 2;
	private static final UriMatcher uriMatcher;

	private static final String DATABASE_NAME = "exerciserecordprovider.db";
	private static final int DATABASE_VERSION = 7;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "exerciserecordprovider", DAY);
		uriMatcher.addURI(AUTHORITY, "exerciserecordprovider/#", GROUP);
	}

	private String makeNewWhere(String where, Uri uri, int matchResult) {

		if (matchResult != GROUP) {
			return where;
		} else {
			String newWhereSoFar = KEY_EXERCISE_RECORD_ID + "=" + uri.getPathSegments().get(1);
			if (TextUtils.isEmpty(where))
				return newWhereSoFar;
			else
				return newWhereSoFar + " AND (" + where + ')';
		}
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {

		return exerciseRecordDB.delete(EXERCISE_RECORD_PROVIDER, where, whereArgs);
		
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case DAY:
			return "vnd.android.cursor.dir/vnd.brookes.lh09092543.othello.PerformanceRecord";
		case GROUP:
			return "vnd.android.cursor.item/vnd.brookes.lh09092543.othello.PerformanceRecord";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.d("debugLee_2","Inserting...");
		long rowID = exerciseRecordDB.insert(EXERCISE_RECORD_PROVIDER, KEY_EXERCISE_ID, values);
		Log.d("debugLee_2","rowID = "+rowID);
		if (rowID > 0) {
			Log.d("debugLee_2","rowID > 0 ");
			Uri newuri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(newuri, null);
			return uri;
		} else{
			Log.d("debugLee_2","rowID !> 0 ");
			throw new SQLException("Failed to insert row into " + uri);
		}
	}

	@Override
	public boolean onCreate() {
		Log.d("test","test2");
		ScoresDatabaseHelper helper = new ScoresDatabaseHelper(
				this.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
		this.exerciseRecordDB = helper.getWritableDatabase();
		return (exerciseRecordDB != null);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sort) {

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(EXERCISE_RECORD_PROVIDER);

		if (uriMatcher.match(uri) == GROUP) {
			qb.appendWhere(KEY_EXERCISE_RECORD_ID + "=" + uri.getPathSegments().get(1));
		}

		Cursor c = qb.query(exerciseRecordDB, projection, selection, selectionArgs,
				null, null, sort);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {

		int matchResult = uriMatcher.match(uri);
		String newWhere = makeNewWhere(where, uri, matchResult);

		if (matchResult == GROUP || matchResult == DAY) {
			int count = exerciseRecordDB.update(EXERCISE_RECORD_PROVIDER, values, newWhere,
					whereArgs);

			getContext().getContentResolver().notifyChange(uri, null);
			return count;
		} else
			throw new IllegalArgumentException("Unknown URI " + uri);
	}

	private static class ScoresDatabaseHelper extends SQLiteOpenHelper {

		public ScoresDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + EXERCISE_RECORD_PROVIDER + " (" + KEY_EXERCISE_RECORD_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_EXERCISE_ID
					+ " INTEGER," + KEY_DATE + " DATETIME," + KEY_SET_1 + "INTEGER" + KEY_SET_2 + "INTEGER"
					+ KEY_SET_3 + "INTEGER" + KEY_SET_4 + "INTEGER" + KEY_SET_5 + "INTEGER"
					+ KEY_WEIGHT_1 + "INTEGER" + KEY_WEIGHT_2 + "INTEGER" + KEY_WEIGHT_3 + "INTEGER" 
					+ KEY_WEIGHT_4 + "INTEGER" + KEY_WEIGHT_5 + "INTEGER);");
			Log.d("test","onCreate called again");
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_RECORD_PROVIDER);
			onCreate(db);
		}

	}
	
	
}
