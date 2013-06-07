package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProjectSettingsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_settings);
		
		Button saveProject = (Button) findViewById(R.id.projSetting_save_project);
		saveProject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProjectSettingsActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu;  this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_settings, menu);
		return true;
	}
}
