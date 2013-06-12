package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;

public class TipHelper {
	private Context context;
	
	public TipHelper(Context context){
		this.context = context;
	}
	
	public String getRandomTip(){
		return "TIPS";
	}
	
	protected void setTipDisplayed(int id){
		
	}
}
