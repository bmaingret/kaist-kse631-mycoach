package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;

public class ProjectManager {
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
	
	public boolean isLevelUp(){
		return false;
	}
	
	public void levelUp(){
		
	}
	
	public void levelDown(){
		
	}
	
	public void pauseProject(){
		
	}
	
	public void resumeProject(){
		
	}
	
	public void endProject(){
		
	}
	
	public void restartProject(){
		
	}
	
	
}
