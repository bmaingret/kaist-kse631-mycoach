package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
				
				SharedPreferences prefs = getApplicationContext().getSharedPreferences(C.PREF, Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(C.P_ACTIVITY_DAYS, getActivityDays()); //Deliminate the string by using a commar
				editor.putStringSet(C.P_ACTIVITIES, getActivities());
				editor.commit();
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
	
	private Set<String> getActivities(){
		Set<String> set = new HashSet<String>();
		ListView lv = (ListView) findViewById(R.id.projSetting_activities);
		SparseBooleanArray checkedItems = lv.getCheckedItemPositions();
		if (checkedItems != null) {
		    for (int i=0; i<checkedItems.size(); i++) {
		        if (checkedItems.valueAt(i)) {
		            String item = ((Cursor)lv.getAdapter().getItem(
		                                  checkedItems.keyAt(i))).getString(1);
		            set.add(item);
		            Log.i("ProjectSettingsActivity",item + " was selected");
		        }
		    }
		}
		return set;
	}
	
	private String getActivityDays(){
		//Monday = 1; Tuesday = 2; so on and so forth... you got what i mean right?
		String r = "";
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Monday)).isChecked()){
			r += "1";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Tuesday)).isChecked()){
			r += ",2";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Wednesday)).isChecked()){
			r += ",3";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Thursday)).isChecked()){
			r += ",4";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Friday)).isChecked()){
			r += ",5";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Saturday)).isChecked()){
			r += ",6";
		}
		if(((CheckBox)findViewById(R.id.projSetting_dayCheckbox_Sunday)).isChecked()){
			r += ",7";
		}
		return r;
	}
}
