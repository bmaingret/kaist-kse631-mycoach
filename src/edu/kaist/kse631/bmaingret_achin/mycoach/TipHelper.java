package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;

public class TipHelper {
	private Context context;
	
	public TipHelper(Context context){
		this.context = context;
	}
	
	public String getRandomTip(){
		return "1) This week, we will try putting down chopsticks for a moment after each mouthful."
				+ System.getProperty ("line.separator")
				+ "Once you have a mouthful of food, put down your chopstick and concentrate on chewing."
				+ System.getProperty ("line.separator")
				+ "Taste your food slowly. You can prevent overeating.";
	}
	
	protected void setTipDisplayed(int id){
		
	}
}
