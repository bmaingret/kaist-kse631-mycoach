package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ActivitiesTableHelper {
	public static final String TABLE_NAME = "activities";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACTIVITY = "activity";
	public static final String COLUMN_W1 = "w1"; 
	public static final String COLUMN_W2 = "w2";
	public static final String COLUMN_W3 = "w3";
	public static final String COLUMN_W4 = "w4";
	
	public static final int W1 = 59;
	public static final int W2 = 70;
	public static final int W3 = 82;
	public static final int W4 = 93;
	
	private static final String TAG = "ActivitiesTable";
	
	public Cursor getActivityList(SQLiteDatabase readableDB){
		Cursor activities = readableDB.query(TABLE_NAME, 
				new String[]{ COLUMN_ACTIVITY},
				null, null, null, null, null);
		return activities;
	}
}
