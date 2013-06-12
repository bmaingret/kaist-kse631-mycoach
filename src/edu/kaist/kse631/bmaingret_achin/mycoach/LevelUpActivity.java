package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LevelUpActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_up);
		
		final ProjectManager manager = new ProjectManager(this);
		
		TextView weeksTextView = (TextView) findViewById(R.id.levelup_weeksLbl);
		String weeksString = getResources().getString(R.string.level_up_time_past);  
		String weeksFormatted = String.format(weeksString, String.valueOf(ProjectManager.TIME_BASE));  
		weeksTextView.setText(weeksFormatted);
		
		TextView percentageTextView = (TextView) findViewById(R.id.levelup_percentLbl);
		String percentageString = getResources().getString(R.string.level_up_percent);  
		String percentageFormatted = String.format(percentageString, String.valueOf(manager.getPeriodAchievment()));  
		percentageTextView.setText(percentageFormatted);
		
		Button continueButton = (Button) findViewById(R.id.btnContinue_levelup);
		continueButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				manager.handleChoice(getUserChoice());
			}
		});
		
	}
	
	private int getUserChoice(){
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		int radioButtonID = radioGroup.getCheckedRadioButtonId();
		
		switch (radioButtonID){
			case R.id.rbtn_leveldown:
				return ProjectManager.LEVEL_DOWN;
			case R.id.rbtn_levelup:
				return ProjectManager.LEVEL_UP;
			case R.id.rbtn_same:
				return ProjectManager.LEVEL_SAME;
			default:
				return -1;	
		}
	}
}
