package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivityDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_details, menu);
		return true;
	}

}
