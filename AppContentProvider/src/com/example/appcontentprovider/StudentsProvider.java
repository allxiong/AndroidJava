package com.example.appcontentprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class StudentsProvider extends ContentProvider {
	static final String NAME = "name";
	static final String GRADE = "grade";
	static final String ID = "_id";
	static final String AUTHORITY = "com.example.appcontentprovider";
	static final String uri = "content://" + AUTHORITY + "/student";
	static final Uri CONTENT_URI = Uri.parse(uri);
	private static HashMap<String, String> STUDENTS_PROJECTS_MAP;
	static final int STUDENTS = 1;
	static final int STUDENT_ID = 2;
	static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "students", STUDENTS);
		uriMatcher.addURI(AUTHORITY, "students/#", STUDENT_ID);
	}

	// Database informaiton
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	public static final String KEY_NAME = "name";
	public static final int COL_NAME = 1;
	public static final String KEY_GRADE = "grade";
	public static final int COL_GRADE = 2;
	// database information
	public static final String DATABASE_NAME = "MyDB";
	public static final String DATABASE_TABLE = "dbTable";
	public static final int DATABASE_VERSION = 1;
	public static final String CREATE_DB_SQL = "create table " + DATABASE_TABLE
			+ "(" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, " + KEY_GRADE + " text not null"
			+ ")";
	public Context context;
	public DatabaseHelper dbHelper;
	public SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
		DatabaseHelper myDBHelper = new 		
			DatabaseHelper(context);
		db = myDBHelper.getWritableDatabase();
		if (db!=null){
			Log.i("DEBUG", "StudentsProvider onCreate() Database created");
		}
		//map string name to column name
		STUDENTS_PROJECTS_MAP = new HashMap<String, String>();
		STUDENTS_PROJECTS_MAP.put(ID, ID);
		STUDENTS_PROJECTS_MAP.put(NAME, NAME);
		STUDENTS_PROJECTS_MAP.put(GRADE, GRADE);
		return (db==null)?false:true ;

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(DATABASE_TABLE); // set the table to query
		switch (uriMatcher.match(uri)) {
		case STUDENTS:
			qb.setProjectionMap(STUDENTS_PROJECTS_MAP);
			break;
		case STUDENT_ID:
			qb.appendWhere(ID + "=" + uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		if (sortOrder == null || sortOrder == "") {
			sortOrder = NAME; // sort by name
		}
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;

	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case STUDENTS:
			return "vnd.android.cursor.dir/vnd.example.students";
		case STUDENT_ID:
			return "vnd.android.cursor.item/vnd.example.students";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Log.i("DEBUG", "StudentsProvider insert uri=" + uri + " values="
				+ values.toString());
		long rowid = db.insert(DATABASE_TABLE, "", values);
		if (rowid > 0) {
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowid);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to add a record into " + uri);

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case STUDENTS:
			count = db.delete(DATABASE_TABLE, selection, selectionArgs);
			break;
		case STUDENT_ID:
			String id = uri.getPathSegments().get(1);
			count = db.delete(DATABASE_TABLE,
					ID
							+ " = "
							+ id
							+ (!TextUtils.isEmpty(selection) ? " AND ("
									+ selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case STUDENTS:
			count = db.update(DATABASE_TABLE, values, selection, selectionArgs);
			break;
		case STUDENT_ID:
			count = db.update(
					DATABASE_TABLE,
					values,
					ID
							+ " = "
							+ uri.getPathSegments().get(1)
							+ (!TextUtils.isEmpty(selection) ? " AND ("
									+ selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_DB_SQL);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
			onCreate(db);

		}

	}
}
