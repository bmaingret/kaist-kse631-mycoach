package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ProjectSettingsActivity extends BaseActivity {
	private Cursor activities = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_settings);

		/* Save project */
		Button saveProject = (Button) findViewById(R.id.projSetting_save_project);
		saveProject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProjectSettingsActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		/* Populate activity list*/
		ActivitiesTableHelper helper = new ActivitiesTableHelper(getApplicationContext());
		activities = helper.getActivityList();

	    ListAdapter adapter = new SimpleCursorAdapter(this,
	        R.layout.checkable_listview,
	        activities, 
	        new String[] { ActivitiesTableHelper.COLUMN_ACTIVITY, ActivitiesTableHelper.COLUMN_ID },
	        new int[] { R.id.activity });

	    // Bind to our new adapter.
	    ListView activityList = (ListView) findViewById(R.id.projSetting_activities);
	    activityList.setAdapter(adapter);
	}
}
