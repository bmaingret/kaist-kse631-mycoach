package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class NewProjectActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		
//		if(customTitleSupported){
//			setTitle("New Project");
//		}
//		
//		
//		
		setContentView(R.layout.activity_new_project);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_project, menu);
		return true;
	}

}
