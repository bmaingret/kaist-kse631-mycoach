package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class StartActivity extends BaseActivity {
	private static final String TAG = "StartActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Everything that must be done on every startup */

		
		
		
		/* Is a project already started */
		SharedPreferences prefs = getApplicationContext().getSharedPreferences(C.PREF, Context.MODE_PRIVATE);
		String projectState = prefs.getString(C.P_PROJECT_STATE, null);
		boolean projectCreatedOrStarted = (null != projectState);
		
		/* Act accordingly */
		if (projectCreatedOrStarted && !C.DEBUG){
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		else{
			setContentView(R.layout.activity_start);

			//Event listener
			Button btnSubmit = (Button) findViewById(R.id.newProject_submitButton);
			btnSubmit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(StartActivity.this, NewProjectActivity.class);
					startActivity(intent);
				}
			});
		}
	}
}
