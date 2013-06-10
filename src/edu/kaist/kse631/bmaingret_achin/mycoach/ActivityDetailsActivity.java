package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_details);
		Intent intent = getIntent();
		
		/* Retrieving userAactivityId*/
		long activityId = intent.getLongExtra("activityId", -1);
		
		/* Fecthing databse entry*/
		UserActivitiesTableHelper helper = new UserActivitiesTableHelper(this);
		Cursor activity = helper.getUserActivity(activityId);
		
		/* Filling the fields */
		if (activity.moveToFirst()){
			TextView type = (TextView) findViewById(R.id.details_type);
			type.setText(activity.getString(activity.getColumnIndex(ActivitiesTableHelper.COLUMN_ACTIVITY)));
			
			TextView durationTextView = (TextView) findViewById(R.id.details__duration);
			long duration = activity.getLong(activity.getColumnIndex(UserActivitiesTableHelper.COLUMN_DURATION));
			String durationStr = String.format("%02d:%02d::%d", 
					TimeUnit.MILLISECONDS.toHours(duration),
				    TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
				    TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.HOURS.toSeconds(TimeUnit.MILLISECONDS.toHours(duration)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
			);
			durationTextView.setText(durationStr);
			
			TextView dateTextView = (TextView) findViewById(R.id.details_date);
			long datetime = activity.getLong(activity.getColumnIndex(UserActivitiesTableHelper.COLUMN_DATETIME));
			Date date = new Date(datetime);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String dateFormatted = formatter.format(date);
			dateTextView.setText(dateFormatted);
		}
		
		/*Continue button */
		String from = intent.getStringExtra(C.DETAILS_FROM);
		if (null == from){
			Button continueButton = (Button) findViewById(R.id.details_continue_button);
			continueButton.setVisibility(View.GONE);
		}
		
	}
}
