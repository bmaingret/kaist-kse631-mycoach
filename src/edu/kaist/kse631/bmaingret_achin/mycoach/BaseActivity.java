package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
			case R.id.action_settings:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
		}
		return true;
	}
}
