package edu.kaist.kse631.bmaingret_achin.mycoach;

public class UserActivitiesTableHelper {
	public static final String TABLE_NAME = "UserActivities";
	public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITY_ID = "activity_id";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DURATION = "duration";
	public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " IF NOT EXISTS ( "
			+ COLUMN_ID + " PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ACTIVITY_ID + " INTEGER "
			+ COLUMN_DATETIME + "INTEGER"
			+ COLUMN_DURATION + "INTEGER"
			+ "FOREIGN KEY(" + COLUMN_ACTIVITY_ID 
				+ ") REFERENCES " + ActivitiesTableHelper.TABLE_NAME + "(" + ActivitiesTableHelper.COLUMN_ID + ")"
			+ " )";
}
