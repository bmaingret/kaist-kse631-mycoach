package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LevelUpActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_up, menu);
		return true;
	}

}
