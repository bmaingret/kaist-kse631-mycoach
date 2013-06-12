package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;

public class HistoryActivity extends Activity {

	private GraphicalView mChart;     
	private XYSeries visitsSeries ;    
	private XYMultipleSeriesDataset dataset;     
	private XYSeriesRenderer visitsRenderer;    
	private XYMultipleSeriesRenderer multiRenderer;
	private ListView historyView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		//Populating history listview
		/* History listview */
        historyView = (ListView) findViewById(R.id.history_listview);
        historyView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				Intent intent = new Intent(HistoryActivity.this, ActivityDetailsActivity.class);
				intent.putExtra("activityId", id);
				startActivity(intent);	
			}
		});
        updateHistory();
		
		// Show the Up button in the action bar.
		setupActionBar();
		setupChart();
		
		new ChartTask().execute();
	}
	
	private void setupChart(){
		// Creating an  XYSeries for Visits        
		visitsSeries = new XYSeries("Running");         
		// Creating a dataset to hold each series        
		dataset = new XYMultipleSeriesDataset();        
		// Adding Visits Series to the dataset        
		dataset.addSeries(visitsSeries);         
		// Creating XYSeriesRenderer to customize visitsSeries        
		visitsRenderer = new XYSeriesRenderer();        
		visitsRenderer.setColor(Color.CYAN);        
		visitsRenderer.setPointStyle(PointStyle.CIRCLE);        
		visitsRenderer.setFillPoints(true);        
		visitsRenderer.setLineWidth(2);        
		visitsRenderer.setDisplayChartValues(true);         
		// Creating a XYMultipleSeriesRenderer to customize the whole chart        
		multiRenderer = new XYMultipleSeriesRenderer();         
		multiRenderer.setChartTitle("Activity Progress Chart");        
		multiRenderer.setXTitle("Date");        
		multiRenderer.setYTitle("Average duration (mins)");        
		multiRenderer.setZoomButtonsVisible(true);         
		multiRenderer.setXAxisMin(0);        
		multiRenderer.setXAxisMax(10);         
		multiRenderer.setYAxisMin(0);        
		multiRenderer.setYAxisMax(10);         
		multiRenderer.setBarSpacing(2);         
		// Adding visitsRenderer to multipleRenderer        
		// Note: The order of adding dataseries to dataset and renderers to multipleRenderer        
		// should be same        
		multiRenderer.addSeriesRenderer(visitsRenderer);         
		// Getting a reference to LinearLayout of the MainActivity Layout        
		LinearLayout chartContainer = (LinearLayout) findViewById(R.id.lineChart_container);         
		mChart = (GraphicalView) ChartFactory.getBarChartView(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);         
		// Adding the Line Chart to the LinearLayout        
		chartContainer.addView(mChart);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void updateHistory(){
        UserActivitiesTableHelper helper = new UserActivitiesTableHelper(this);
        Cursor history = helper.getHistory();
        String[] from = new String[]{ActivitiesTableHelper.COLUMN_ACTIVITY, UserActivitiesTableHelper.COLUMN_DATETIME};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter historyAdapter = new SimpleCursorAdapter(this,
        		android.R.layout.simple_list_item_2,
        		history,
        		from,
        		to);
        historyAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View aView, Cursor aCursor, int aColumnIndex) {
                if (aColumnIndex == aCursor.getColumnIndex(UserActivitiesTableHelper.COLUMN_DATETIME)) {
                        long datetime= aCursor.getLong(aColumnIndex);
                        TextView textView = (TextView) aView;
            			Date date = new Date(datetime);
            			DateFormat formatter = new SimpleDateFormat(" MM/dd/yyyy");
            			String dateFormatted = formatter.format(date);
                        textView.setText(dateFormatted);
                        return true;
                 }
                 return false;
            }
        });
        historyView.setAdapter(historyAdapter);
    }


private class ChartTask extends AsyncTask<Void, String, Void>{         
	// Generates dummy data in a non-ui thread        
	@Override        
	protected Void doInBackground(Void... params) {            
		int i = 0;            
		try{                
			do{                    
				String [] values = new String[2];                    
				Random r = new Random();                    
				int visits = r.nextInt(10);                     
				values[0] = Integer.toString(i);                    
				values[1] = Integer.toString(visits);                     
				publishProgress(values);                    
				Thread.sleep(1000);                    
				i++;                
				}while(i<=10);                    
			}catch(Exception e){ }                
		return null;            
	}             
	
	// Plotting generated data in the graph            
	@Override            
	protected void onProgressUpdate(String... values) {                
		visitsSeries.add(Integer.parseInt(values[0]), Integer.parseInt(values[1]));                
		mChart.repaint();            
		}        
	}         
		
}

