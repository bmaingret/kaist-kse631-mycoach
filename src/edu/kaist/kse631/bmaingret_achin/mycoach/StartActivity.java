package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
