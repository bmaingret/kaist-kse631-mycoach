package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseArray;

public class ActivitiesTableHelper{
	public static final String TABLE_NAME = "activities";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACTIVITY = "activity";
	public static final String COLUMN_W1 = "w1"; 
	public static final String COLUMN_W2 = "w2";
	public static final String COLUMN_W3 = "w3";
	public static final String COLUMN_W4 = "w4";
	public static final String[] ALL_COLUMNS = new String[]{COLUMN_ID, COLUMN_ACTIVITY, COLUMN_W1, COLUMN_W3, COLUMN_W3, COLUMN_W4 };

	public static final int W1 = 59;
	public static final int W2 = 70;
	public static final int W3 = 82;
	public static final int W4 = 93;

	private static final String TAG = "ActivitiesTable";
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db = null;

	public ActivitiesTableHelper(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public Cursor getActivityList(){
		SparseArray<String> activityList = new SparseArray<String>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor activities = db.query(TABLE_NAME, 
				new String[]{ COLUMN_ID, COLUMN_ACTIVITY},
				null, null, null, null, null);
		if (activities.moveToFirst()) {
			do {
				activityList.put(activities.getInt(0), activities.getString(1));
			} while (activities.moveToNext());
		}
		try{if(null != db){ db.close(); db = null;}}catch(Exception e){}
		return activities;
	}
	
	public Cursor getActivity(long id){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor activity = db.query(TABLE_NAME, 
				ALL_COLUMNS,
				COLUMN_ID + " = ? ",
				new String[]{String.valueOf(id)},
				null, null, null, null);
		try{if(null != db){ db.close(); db = null;}}catch(Exception e){}
		return activity;
	}
}
