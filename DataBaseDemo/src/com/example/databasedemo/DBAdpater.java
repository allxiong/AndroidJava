package com.example.databasedemo;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdpater {
	
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	
	public static final String KEY_NAME = "name";
	public static final String KEY_MAJOR = "major";
	
	//set up your field number here
	public static final int COL_NAME = 1;
	public static final int COL_MAJOR = 2;
	
	public static final String[] ALL_KEYS = new String[]{KEY_ROWID, KEY_NAME, KEY_MAJOR}; 
	
	//Database information
	public static final String DATABASE_NAME = "MyDB";
	public static final String DATABASE_TABLE = "dbTable";
	public static final int DATABASE_VERSION = 1;
	
	public static final String CREATE_DB_SQL = "create table "+ DATABASE_TABLE
			+ " (" + KEY_ROWID +" integer primary key autoincrement, "
			+ KEY_NAME +" text not null, "
			+ KEY_MAJOR + " text not null "
			+");";
	
	private Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;
	
	public DBAdpater(Context ctx){
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	public DBAdpater open(){
		db = myDBHelper.getWritableDatabase();
		File dbFile = context.getDatabasePath(DATABASE_NAME);
		Log.i("AX", dbFile.getAbsolutePath());
		return this;
	}
	
	public void close(){
		myDBHelper.close();
	}
	
	public long insertRow(String name, String major){
		ContentValues contentV = new ContentValues();
		contentV.put(KEY_NAME, name);
		contentV.put(KEY_MAJOR, major);
		
		return db.insert(DATABASE_TABLE, null, contentV);
	}
	
	public boolean deleteRow(long rowid){
		String whereStr = KEY_ROWID + " = "+ rowid;
		return db.delete(DATABASE_TABLE, whereStr, null)>0;
	}
	
	public void deleteAll(){
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()){
			do{
				deleteRow(c.getLong((int)rowId));
				
			}while (c.moveToNext());
		}
	}
	
	public Cursor getAllRows(){
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c!=null){
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getRow(long rowId){
		String where = KEY_ROWID +" = "+rowId;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c!=null){
			c.moveToFirst();
		}
		return c;
	}
	
	public boolean updateRow(long rowId, String name, String major){
		String where = KEY_ROWID +" = "+rowId;
		ContentValues contentV = new ContentValues();
		contentV.put(KEY_NAME, name);
		contentV.put(KEY_MAJOR, major);
		
		return db.update(DATABASE_TABLE, contentV, where, null)>0;
	}
	
	//inner class that handles creating and updating the database
	class DatabaseHelper extends SQLiteOpenHelper{
		
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
			
			//destroy old DB
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
			//create new DB
			onCreate(db);
		}
	}
}
