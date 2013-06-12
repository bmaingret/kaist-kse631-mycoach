package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
	private static final String TAG = "CheckableLinearLayout";
	private CheckedTextView _checkbox;
    	
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
	}
    
    @Override
    protected void onFinishInflate() {
    	super.onFinishInflate();
    	// find checked text view
		int childCount = getChildCount();
		for (int i = 0; i < childCount; ++i) {
			View v = getChildAt(i);
			if (v instanceof CheckedTextView) {
				_checkbox = (CheckedTextView)v;
				Log.d(TAG, "Found checkbox!");
			}
		}    	
    }
    
    @Override 
    public boolean isChecked() { 
        return _checkbox != null ? _checkbox.isChecked() : false; 
    }
    
    @Override 
    public void setChecked(boolean checked) {
    	if (_checkbox != null) {
    		_checkbox.setChecked(checked);
    	}
    }
    
    @Override 
    public void toggle() { 
    	if (_checkbox != null) {
    		_checkbox.toggle();
    	}
    } 
} 