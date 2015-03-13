/**
 * @author Jerry
 *
 */
package com.example.mydashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.achartengine.chart.BarChart.Type;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.dbContrl.DataLoader;
import com.example.models.Monitoring_Monthly;
import com.example.models.Monitoring_Weekly;

import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class FragmentRestroomTabMonthly extends Fragment implements IDrawGraph{
	private boolean isLoad;
	private GraphicalView mChartView;
	ArrayList<Monitoring_Monthly> monthDataList;
	private static ExecutorService exec = Executors.newSingleThreadExecutor();
	
	private String pid;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_container, container, false);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!isLoad) {
			Log.i("onResume", "NOT loaded.");
			pid = getActivity().getIntent().getStringExtra(Define.TAG_ID);
			Log.d("pid is ", pid);

			DataLoader dataLoader=new DataLoader(getActivity(), getFragmentManager(), this);  //use call back function
			dataLoader.executeOnExecutor(exec,
				"sql_string", // param1 name 
				"select patient_id, location_id, substring(date_id,1,8) date_month, sum(place_frequency) place_frequency, sum(duration) duration "
				+ "from monitoring_daily_r1 group by date_month, patient_id, location_id having patient_id = 1 and location_id = 2 and date_month between 20110101 and 20110131", // param1 value
				
				"params_string", // param2 name
				Define.TAG_PATIENT_ID + ","  // param2 value
				+ Define.TAG_LOCATION_ID + ","
				+ Define.TAG_DATE_MONTH + ","
				+ Define.TAG_PLACE_FREQUENCY + ","
				+ Define.TAG_DURATION
			);
		}
	}

	@Override
	public void drawGraph(JSONObject json) {
		
		if(json!=null){
			JSONArray dataArray;// JSON Array
			monthDataList=new ArrayList<Monitoring_Monthly>();
			try{
				dataArray = json.getJSONArray(Define.TAG_OBJECT_ARRAY);
				for (int i = 0; i < dataArray.length(); i++) {
					JSONObject c = dataArray.getJSONObject(i);
	
					// Storing each json item in variable
					int patient_id = c.getInt(Define.TAG_PATIENT_ID);
					int location_id = c.getInt(Define.TAG_LOCATION_ID);
					int date_weekday=c.getInt(Define.TAG_DATE_MONTH);//actually it is the day of the month, DB problem
					int place_frequency=c.getInt(Define.TAG_PLACE_FREQUENCY);
					int duration = c.getInt(Define.TAG_DURATION);
	
					Monitoring_Monthly obj = new Monitoring_Monthly(patient_id, location_id,date_weekday,place_frequency, duration);
					monthDataList.add(obj);
				}
				// change isLoad flag to true prevents when parent tab (activity) changed
				isLoad = true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			Log.d("json", pid);
			return;
		}
		
		// TODO Auto-generated method stub
		String[] titles = new String[] { "Duration"};
		List<double[]> values = new ArrayList<double[]>();
		
		int monthDataListSize=monthDataList.size();
		
		int[] colors = new int[] { Color.CYAN};
		XYMultipleSeriesRenderer renderer = ChartHelper.buildBarRenderer(colors);
		renderer.setOrientation(Orientation.VERTICAL);
		ChartHelper.setChartSettings(renderer, "",
				"Day", "Duration", 0.5, 12.5, 0, 100, Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(1);
		renderer.setYLabels(10);
		
		double[] durations=new double[monthDataListSize]; //render DB data
		for(int i=0;i<monthDataListSize;i++){
			durations[i]=monthDataList.get(i).getDuration();
			renderer.addXTextLabel(i+1, i+1+"");
		}
		values.add(durations);
		renderer.setBarSpacing(0.2);
		
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setDisplayChartValues(true);
		}
		
		if (mChartView == null) {
			FrameLayout layout = (FrameLayout) getView().findViewById(R.id.frag);
			mChartView=ChartFactory.getBarChartView(getActivity(), ChartHelper.buildBarDataset(titles, values), renderer, Type.DEFAULT);
			layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
	}
}
