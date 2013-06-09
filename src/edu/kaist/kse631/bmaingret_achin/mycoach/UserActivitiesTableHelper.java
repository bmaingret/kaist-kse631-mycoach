package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserActivitiesTableHelper {
	public static final String TABLE_NAME = "UserActivities";
	public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITY_ID = "activity_id";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DURATION = "duration";
    public static final String[] ALL_COLUMNS = new String[]{COLUMN_ID, COLUMN_ACTIVITY_ID, COLUMN_DATETIME, COLUMN_DURATION}; 
	public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( "
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ACTIVITY_ID + " INTEGER, "
			+ COLUMN_DATETIME + " INTEGER, "
			+ COLUMN_DURATION + " INTEGER, "
			+ "FOREIGN KEY(" + COLUMN_ACTIVITY_ID 
				+ ") REFERENCES " + ActivitiesTableHelper.TABLE_NAME + "(" + ActivitiesTableHelper.COLUMN_ID + ")"
			+ " );";
	
	private static final String TAG = "UserActivitiesTableHelper";
	private DatabaseHelper dbHelper= null;
	private Context context = null;
	
	public UserActivitiesTableHelper(Context context){
		dbHelper = new DatabaseHelper(context);
		this.context = context;
	}
		
	public void saveActivity(long activityId, long datetime, long duration){
		ContentValues values = new ContentValues();
		values.put(COLUMN_ACTIVITY_ID, activityId);
		values.put(COLUMN_DATETIME, datetime);
		values.put(COLUMN_DURATION, duration);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(TABLE_NAME, null, values);
		Log.d(TAG, "Activity saved:" + id);
		try{if(null != db){ db.close(); db = null;}}catch(Exception e){}
	}

	public Cursor getHistory() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_NAME + " as UA, " + ActivitiesTableHelper.TABLE_NAME + " as A "
				+ "ON UA." + COLUMN_ACTIVITY_ID + "=A." + ActivitiesTableHelper.COLUMN_ID + ";";
		Log.d(TAG, "Executing: " + query);
		Cursor history = db.rawQuery(query, null);
		Log.d(TAG, "History: " + history.getCount() + "entries found");
		try{if(null != db){ db.close(); db = null;}}catch(Exception e){}
		return history;
	}
}
