package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NewActivityActivity extends BaseActivity {
	private long activityId = -1;
	private Cursor activities = null;
	private Spinner activitySpinner = null;
	private static final int NOTIFICATION_ID = 631;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_activity);
		setupActionBar();

		/* Spinned listener */
		activitySpinner = (Spinner) findViewById(R.id.newActivity_activity_list);

		/* Start button Listener */
		ImageButton startButton = (ImageButton) findViewById(R.id.newActivity_start_button);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activityId = activitySpinner.getSelectedItemId();
				String activity = ((Cursor) activitySpinner.getSelectedItem())
						.getString(1);
				Toast toast = Toast.makeText(NewActivityActivity.this,
						"Activity id:" + activityId, Toast.LENGTH_SHORT);
				toast.show();
				
				//Notification
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				@SuppressWarnings("deprecation")
				Notification notification = new Notification(R.drawable.ic_launcher, "Ongoing activity: " + activity, System.currentTimeMillis());
				notification.flags = Notification.FLAG_ONGOING_EVENT;
				Intent notificationIntent = new Intent(NewActivityActivity.this,
						MainActivity.class);
				notificationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				PendingIntent contentPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				notification.setLatestEventInfo(getApplicationContext(), "myCoach", "Ongoing activity: " + activity, contentPendingIntent);
				mNotificationManager.notify(NOTIFICATION_ID, notification);

				Intent intent = new Intent(NewActivityActivity.this,
						MainActivity.class);
				setResult(RESULT_OK, intent);
				intent.putExtra("activityId", activityId);
				intent.putExtra("activity", activity);
				//intent.putExtra("notificationID", NOTIFICATION_ID);
				finish();
			}
		});

		/* Populate activity list */
		ActivitiesTableHelper helper = new ActivitiesTableHelper(
				getApplicationContext());
		activities = helper.getActivityList();

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, activities, new String[] {
						ActivitiesTableHelper.COLUMN_ACTIVITY,
						ActivitiesTableHelper.COLUMN_ID },
				new int[] { android.R.id.text1 });

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		activitySpinner.setAdapter(adapter);

		setActivityIconListener();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	private void setActivityIconListener() {
		// Activity Icon Listener
		ImageButton btnSoccer = (ImageButton) findViewById(R.id.btnSoccer);
		btnSoccer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleCursorAdapter adapter = (SimpleCursorAdapter) activitySpinner
						.getAdapter();
				for (int i = 0; i < adapter.getCount(); i++) {
					// Log.d("New Activity", "Item:" +
					// ((Cursor)adapter.getItem(i)).getString(1));
					if (adapter.getItem(i) != null
							&& ((Cursor) adapter.getItem(i)).getString(1)
									.equalsIgnoreCase("playing soccer")) {
						activitySpinner.setSelection(i);
						break;
					}
				}

			}
		});

		ImageButton btnTennis = (ImageButton) findViewById(R.id.btnTennis);
		btnTennis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleCursorAdapter adapter = (SimpleCursorAdapter) activitySpinner
						.getAdapter();
				for (int i = 0; i < adapter.getCount(); i++) {
					// Log.d("New Activity", "Item:" +
					// ((Cursor)adapter.getItem(i)).getString(1));
					if (adapter.getItem(i) != null
							&& ((Cursor) adapter.getItem(i)).getString(1)
									.equalsIgnoreCase("playing tennis")) {
						activitySpinner.setSelection(i);
						break;
					}
				}

			}
		});

		ImageButton btnRunning = (ImageButton) findViewById(R.id.btnRunning);
		btnRunning.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleCursorAdapter adapter = (SimpleCursorAdapter) activitySpinner
						.getAdapter();
				for (int i = 0; i < adapter.getCount(); i++) {
					// Log.d("New Activity", "Item:" +
					// ((Cursor)adapter.getItem(i)).getString(1));
					if (adapter.getItem(i) != null
							&& ((Cursor) adapter.getItem(i)).getString(1)
									.equalsIgnoreCase("Running, general")) {
						activitySpinner.setSelection(i);
						break;
					}
				}

			}
		});

		ImageButton btnBasketball = (ImageButton) findViewById(R.id.btnBasketball);
		btnBasketball.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleCursorAdapter adapter = (SimpleCursorAdapter) activitySpinner
						.getAdapter();
				for (int i = 0; i < adapter.getCount(); i++) {
					// Log.d("New Activity", "Item:" +
					// ((Cursor)adapter.getItem(i)).getString(1));
					if (adapter.getItem(i) != null
							&& ((Cursor) adapter.getItem(i)).getString(1)
									.equalsIgnoreCase(
											"Playing basketball, non game")) {
						activitySpinner.setSelection(i);
						break;
					}
				}

			}
		});
	}
}
