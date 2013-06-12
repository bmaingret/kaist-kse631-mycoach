package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ProjectManager {
	public static final int TIME_BASE = 3;
	public static final int LEVEL_UP = 0;
	public static final int LEVEL_DOWN = 1;
	public static final int LEVEL_SAME = 2;
	private Context context = null;
	
	public ProjectManager(Context context){
		this.context = context;
	}
	
	public boolean isProjectPaused(){
		return false;
	}
	
	public void updateCalendar(){
		
	}
	
	public int getLevel(int startCount, int startAverage, int endcount, int endAverage){
		return 0;
	}
	
	public int getWeeklyAchievementPercentage(){
		return 0;
	}
	
	public int getActivityAchievment(long goalTimeMillis, long achievedTimeMillis){
		return 0;
	}
	
	public int getPeriodAchievment(){
		return 80;
	}
	
	public boolean isEndPeriod(){
		return true;
	}
	
	public boolean isLevelUp(){
		return true;
	}
	
	public void levelUp(){
		showToast("Level up");
		Intent intent = new Intent(context, TipsActivity.class);
		context.startActivity(intent);
	}
	
	public void levelDown(){
		showToast("Level down");
	}
	
	private void levelSame() {
		showToast("Level same");
	}
	
	public void pauseProject(){
		
	}
	
	public void resumeProject(){
		
	}
	
	public void endProject(){
		
	}
	
	public void restartProject(){
		
	}

	public boolean longTimeNoWork() {
		return false;
	}

	public void handleChoice(int userChoice) {
		switch (userChoice){
		case LEVEL_UP:
			levelUp();
			break;
		case LEVEL_DOWN:
			levelDown();
			break;
		case LEVEL_SAME:
			levelSame();
			break;
		}
	}	
	
	private void showToast(String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
