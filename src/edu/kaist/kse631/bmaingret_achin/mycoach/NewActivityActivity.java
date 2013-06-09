package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class NewActivityActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_activity);
		setupActionBar();
		
		/* Start button Listener*/
		ImageButton startButton = (ImageButton) findViewById(R.id.newActivity_start_button);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(NewActivityActivity.this, "TODO: Start", Toast.LENGTH_SHORT);
				toast.show();
				Intent intent = new Intent(NewActivityActivity.this, MainActivity.class);
				setResult(RESULT_OK, intent);     
				finish();
			}
		});
		
		/* Populate activity list*/
		ActivitiesTableHelper helper = new ActivitiesTableHelper(getApplicationContext());
		List<String> activityList = helper.getActivityList();
		Spinner activitySpinner= (Spinner) findViewById(R.id.newActivity_activity_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		activitySpinner.setAdapter(adapter);
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
}
