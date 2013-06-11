package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;
import android.util.Log;

public class CaloriesHelper {
	private static final String TAG = "CaloriesHelper";
	protected Context context = null;
	private static final long ONE_HOUR_IN_MILLI = 3600000L;

	public CaloriesHelper(Context context){
		this.context = context;
	}

	public static int getCalories(int weight, int[] base, long durationInMilli){
		Log.d(TAG, "Weight/duration" + weight + "/" + durationInMilli);
		return (int) ((double)durationInMilli/ONE_HOUR_IN_MILLI * getCaloriesForOneHour(weight, base));		
	}
	
	public static double getCaloriesForOneHour(int weight, int[] base){
		int indice = getIndice(weight);
		return (weight*getLinearIncrease(indice, base)+getZeroValue(indice, base));
	}

	protected static int getIndice(int weight){
		if (weight <= ActivitiesTableHelper.W2){
			return 0;
		}
		else if (weight <= ActivitiesTableHelper.W3){
			return 1;
		}
		else return 2;
	}

	protected static int getWeightReference(int indice){
		switch(indice){
		case 0:
			return ActivitiesTableHelper.W1;
		case 1:
			return ActivitiesTableHelper.W2;
		case 2:
			return ActivitiesTableHelper.W3;
		case 3:
			return ActivitiesTableHelper.W2;
		default:
			Log.e(TAG, "Invalid indice");
			return 0;					
		}
	}
	
	protected static double getLinearIncrease(int indice, int[] base){
		double increase = (base[indice+1] - base[indice])/(double) (getWeightReference(indice+1)- getWeightReference(indice));
		Log.d(TAG, "indice: " + indice);
		Log.d(TAG, "base: " + base[indice]);
		Log.d(TAG, "ref: "+ getWeightReference(indice));
		Log.d(TAG, "increase: " + increase );
		return increase; 		
	}
	
	protected static double getZeroValue(int indice, int[] base){
		double zeroV = (double)base[indice]-getLinearIncrease(indice, base)*getWeightReference(indice);
		Log.d(TAG, "ZeroV: "+ zeroV);
		return zeroV;
	}
}
