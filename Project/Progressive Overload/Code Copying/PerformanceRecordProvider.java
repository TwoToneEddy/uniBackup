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
public class PerformanceRecordProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.progressiveoverload";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/PerformanceRecord");

	private SQLiteDatabase scoresDB;
	private static final String PERFORMANCE_RECORD = "PerformanceRecord";
	public static final String KEY_WORKOUT_ID = "Workout_id";
	public static final String KEY_WORKOUT_DAY = "Day";
	public static final String KEY_MUSCLE_GROUP = "Muscle_group";
	public static final String IMAGE_URI = "Image";
	public static final int NAME_COLUMN = 1;
	public static final int SCORE_COLUMN = 2;

	private static final int SCORES = 1;
	private static final int PLAYER_ID = 2;
	private static final UriMatcher uriMatcher;

	private static final String DATABASE_NAME = "performanceRecord.db";
	private static final int DATABASE_VERSION = 1;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "capitals", SCORES);
		uriMatcher.addURI(AUTHORITY, "capitals/#", PLAYER_ID);
	}

	private String makeNewWhere(String where, Uri uri, int matchResult) {

		if (matchResult != PLAYER_ID) {
			return where;
		} else {
			String newWhereSoFar = KEY_WORKOUT_ID + "=" + uri.getPathSegments().get(1);
			if (TextUtils.isEmpty(where))
				return newWhereSoFar;
			else
				return newWhereSoFar + " AND (" + where + ')';
		}
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {

		return scoresDB.delete(PERFORMANCE_RECORD, where, whereArgs);
		
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case SCORES:
			return "vnd.android.cursor.dir/vnd.brookes.lh09092543.othello.PerformanceRecord";
		case PLAYER_ID:
			return "vnd.android.cursor.item/vnd.brookes.lh09092543.othello.PerformanceRecord";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = scoresDB.insert(PERFORMANCE_RECORD, KEY_WORKOUT_DAY, values);
		if (rowID > 0) {
			Uri newuri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(newuri, null);
			return uri;
		} else
			throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		Log.d("test","test2");
		ScoresDatabaseHelper helper = new ScoresDatabaseHelper(
				this.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
		this.scoresDB = helper.getWritableDatabase();
		return (scoresDB != null);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sort) {

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(PERFORMANCE_RECORD);

		if (uriMatcher.match(uri) == PLAYER_ID) {
			qb.appendWhere(KEY_WORKOUT_ID + "=" + uri.getPathSegments().get(1));
		}

		Cursor c = qb.query(scoresDB, projection, selection, selectionArgs,
				null, null, sort);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {

		int matchResult = uriMatcher.match(uri);
		String newWhere = makeNewWhere(where, uri, matchResult);

		if (matchResult == PLAYER_ID || matchResult == SCORES) {
			int count = scoresDB.update(PERFORMANCE_RECORD, values, newWhere,
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
			db.execSQL("CREATE TABLE " + PERFORMANCE_RECORD + " (" + KEY_WORKOUT_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_WORKOUT_DAY
					+ " TEXT," + KEY_MUSCLE_GROUP + " TEXT);");
			Log.d("test","onCreate called again");
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + PERFORMANCE_RECORD);
			onCreate(db);
		}

	}
	
	
}