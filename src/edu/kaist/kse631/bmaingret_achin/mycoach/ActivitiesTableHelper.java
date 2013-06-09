package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ActivitiesTableHelper{
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
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	public ActivitiesTableHelper(Context context) {
		dbHelper = new DatabaseHelper(context);
		try{
			dbHelper.createDataBase();
			dbHelper.openDataBase();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public List<String> getActivityList(){
		List<String> activityList = new ArrayList<String>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor activities = db.query(TABLE_NAME, 
				new String[]{ COLUMN_ACTIVITY},
				null, null, null, null, null);
		if (activities.moveToFirst()) {
			do {
				activityList.add(activities.getString(0));
			} while (activities.moveToNext());
		}

		// closing connection
		activities.close();
		db.close();

		return activityList;
	}
}
