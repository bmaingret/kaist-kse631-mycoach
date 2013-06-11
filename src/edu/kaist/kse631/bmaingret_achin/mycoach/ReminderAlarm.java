package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class ReminderAlarm {
	private BroadcastReceiver broadcastReceiver = null;
	private Context context = null;
	private PendingIntent pendingIntent = null;
	private AlarmManager alarmManager = null;
	private static final long intervalMillis = C.ONE_WEEK_MILLIS;

	public ReminderAlarm(Context context){
		this.context = context;

		broadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(c, "Rise and Shine!", Toast.LENGTH_LONG).show();
			}
		};

		context.registerReceiver(broadcastReceiver, new IntentFilter(C.INTENT_FILTER_BC) );
		pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(C.INTENT_FILTER_BC), 0);
		alarmManager = (AlarmManager)(context.getSystemService( Context.ALARM_SERVICE ));

		long triggerAtMillis = Calendar.getInstance().getTimeInMillis() + intervalMillis;
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, pendingIntent);
	}
}
