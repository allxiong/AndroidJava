package com.example.testapp;

import java.util.HashMap;

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
import android.net.Uri;
import android.text.TextUtils;

public class StudentsProvider extends ContentProvider
{
	static final String NAME="name";
	static final String GRADE="grade";
	static final String ID="_id";
	static final String AUTHORITY="com.example.testapp";
	static final String URI="content://"+AUTHORITY+"/student";
	static final Uri CONTENT_URI = Uri.parse(URI);
	private static HashMap<String, String> STUDENTS_PROJECTS_MAP;
	static final int STUDENTS = 1;
	static final int STUDENT_ID = 2;
	static final UriMatcher uriMatcher;
	static{
	uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	uriMatcher.addURI(AUTHORITY, "students", STUDENTS);
	uriMatcher.addURI(AUTHORITY, "students/#", STUDENT_ID);
	}
	
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	public static final String KEY_NAME = "name";
	public static final int COL_NAME = 1;
	public static final String KEY_GRADE = "grade";
	public static final int COL_GRADE = 2;
	//database information
	public static final String DATABASE_NAME="MyDB";
	public static final String DATABASE_TABLE="dbTable";
	public static final int DATABASE_VERSION=1;
	public static final String CREATE_DB_SQL = "create table "+DATABASE_TABLE+"("+KEY_ROWID
			+" integer primary key autoincrement, "+KEY_NAME + " text not null, "
			+KEY_GRADE+" text not null" +");";
	public Context context;
	public DatabaseHelper dbHelper;
	public SQLiteDatabase db;
	
	public boolean onCreate() 
	{
		Context context = getContext();
		DatabaseHelper myDBHelper = new DatabaseHelper(context);
		db = myDBHelper.getWritableDatabase();
		STUDENTS_PROJECTS_MAP = new HashMap<String, String>();
		STUDENTS_PROJECTS_MAP.put(ID, ID);
		STUDENTS_PROJECTS_MAP.put(NAME, NAME);
		STUDENTS_PROJECTS_MAP.put(GRADE, GRADE);
		return (db==null)?false:true ;
	}
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String
			sortOrder) 
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(DATABASE_TABLE); // set the table to query
		switch (uriMatcher.match(uri)) {
		case STUDENTS: qb.setProjectionMap(STUDENTS_PROJECTS_MAP);
		break;
		case STUDENT_ID: qb.appendWhere( ID + "=" + uri.getPathSegments().get(1));
		break;
		default: throw new IllegalArgumentException("Unknown URI " + uri);
		}
		if (sortOrder == null || sortOrder == ""){
		sortOrder = NAME; //sort by name
		}
		Cursor c = qb.query(db, projection, selection, selectionArgs,
		null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;

	}
	public String getType(Uri uri) 
	{
		switch (uriMatcher.match(uri)){
		case STUDENTS:
		return "vnd.android.cursor.dir/vnd.example.students";
		case STUDENT_ID:
		return "vnd.android.cursor.item/vnd.example.students";
		default:
		throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}
	public Uri insert(Uri uri, ContentValues values) 
	{
		long rowid = db.insert(DATABASE_TABLE, "", values);
		if (rowid >0){
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowid);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to add a record into " + uri);
	}
	public int delete(Uri uri, String selection, String[] selectionArgs) 
	{
		int count = 0;
		switch (uriMatcher.match(uri)){
		case STUDENTS:
		count = db.delete(DATABASE_TABLE, selection, selectionArgs);
		break;
		case STUDENT_ID:
		String id = uri.getPathSegments().get(1);
		count = db.delete( DATABASE_TABLE, ID + " = " + id +
		(!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
		break;
		default:
		throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) 
	{
		int count = 0;
		switch (uriMatcher.match(uri)){
		case STUDENTS:
		count = db.update(DATABASE_TABLE, values, selection, selectionArgs);
		break;
		case STUDENT_ID:
		count = db.update(DATABASE_TABLE, values, ID + " = " + uri.getPathSegments().get(1) +
		(!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
		break;
		default:
		throw new IllegalArgumentException("Unknown URI " + uri );
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_DB_SQL);
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int
				newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
			onCreate(db);
		}
	}
	
	
}
