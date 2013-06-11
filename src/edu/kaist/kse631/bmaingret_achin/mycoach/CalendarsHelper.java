package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.util.Log;

public class CalendarsHelper {
	private static final String TAG = "CalendarsHelper";
	// Projection array. Creating indices for this array instead of doing
	// dynamic lookups improves performance.
	public static final String[] EVENT_PROJECTION = new String[] {
		Calendars._ID,                           // 0
		Calendars.ACCOUNT_NAME,                  // 1
		Calendars.CALENDAR_DISPLAY_NAME,         // 2
		Calendars.OWNER_ACCOUNT                  // 3
	};
	// The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
	private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
	private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

	private static final Uri calendar_URI = Calendars.CONTENT_URI;
	private static final Uri dates_URI = Instances.CONTENT_URI;

	public CalendarsHelper() {
		super();
	}

	public static List<String> getCalendarList(Context context) {
		List<String> calendarList = new ArrayList<String>();
		
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = null;

		String selection = null;
		String[] selectionArgs = null; 
		  
		cursor= contentResolver.query(calendar_URI, EVENT_PROJECTION, selection, selectionArgs, null);
		
		while (cursor.moveToNext()) {
		    long calID = 0;
		    String displayName = null;
		    String accountName = null;
		    String ownerName = null;
		      
		    // Get the field values
		    calID = cursor.getLong(PROJECTION_ID_INDEX);
		    displayName = cursor.getString(PROJECTION_DISPLAY_NAME_INDEX);
		    accountName = cursor.getString(PROJECTION_ACCOUNT_NAME_INDEX);
		    ownerName = cursor.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
		    
		    Log.d(TAG, "cal ID" + calID);
		    Log.d(TAG, displayName);
		    Log.d(TAG, accountName);
		    Log.d(TAG, ownerName);
		    
		    calendarList.add(displayName);		    
		}
		return calendarList;
	}

	public static long addEvent(Context context, long calID, long startMillis, long endMillis){
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, "myCoach Exercise");
		values.put(Events.DESCRIPTION, "Workout");
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
		Uri uri = cr.insert(Events.CONTENT_URI, values);

		// get the event ID that is the last element in the Uri
		long eventID = Long.parseLong(uri.getLastPathSegment());
		Log.d(TAG, "Event created: " + eventID);
		return eventID;
	}
}
