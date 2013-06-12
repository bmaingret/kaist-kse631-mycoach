package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TipsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips);
		
		TipHelper helper = new TipHelper(this);
		
		TextView tipsTextView =  (TextView) findViewById(R.id.tips_text);
		tipsTextView.setText(helper.getRandomTip());
		
		Button saveButton = (Button) findViewById(R.id.tips_save_btn);
		saveButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TipsActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
