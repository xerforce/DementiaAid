/**
 * 
 */
package com.example.mydashboard;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.BubbleChart;
import org.achartengine.chart.CubicLineChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.dbContrl.DataLoader;
import com.example.models.Monitoring_Daily;
import com.example.models.Monitoring_Weekly;
import com.example.mydashboard.FragmentRestroomTabToday.LoadData;

import android.app.Activity;
import android.graphics.*;
import android.graphics.Paint.Align;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author Jerry
 *
 */
public class FragmentRestroomTabWeekly extends Fragment {

	private boolean isLoad;
	private GraphicalView mChartView;
	ArrayList<Monitoring_Weekly> list;
		
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isLoad=false;
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
//		if (!isLoad) {
//			Log.i("onResume", "NOT loaded.");
//			String pid = getActivity().getIntent().getStringExtra(Define.TAG_ID);
//			Log.d("pid is ", pid);
//
//			DataLoader dataLoader=new DataLoader(getActivity(), getFragmentManager());
//			dataLoader.execute(
//				"sql_string", // param1 name 
//				"select patient_id, location_id, substring(date_id,1,8) date_weekday, place_frequency, duration " // param1 value
//				+ "from monitoring_daily_r1 "
//				+ "where patient_id = 1 and location_id = 2 and date_id between 2011010100 and 2011010799", 
//				
//				"params_string", // param2 name
//				Define.TAG_PATIENT_ID + ","  // param2 value
//				+ Define.TAG_LOCATION_ID + ","
//				+ Define.TAG_DATE_WEEKDAY + ","
//				+ Define.TAG_PLACE_FREQUENCY + ","
//				+ Define.TAG_DURATION
//			);
//			JSONObject json=null;
//			try {
//				json=dataLoader.getJson();
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			if(json!=null){
//				JSONArray dataArray;
//				list=new ArrayList<Monitoring_Weekly>();
//				try {
//					dataArray = json.getJSONArray(Define.TAG_OBJECT_ARRAY);
//					// looping through All Products
//					for (int i = 0; i < dataArray.length(); i++) {
//						JSONObject c = dataArray.getJSONObject(i);
//
//						// Storing each json item in variable
//						int patient_id = c.getInt(Define.TAG_PATIENT_ID);
//						int location_id = c.getInt(Define.TAG_LOCATION_ID);
//						int date_weekday=c.getInt(Define.TAG_DATE_WEEKDAY);
//						int place_frequency=c.getInt(Define.TAG_PLACE_FREQUENCY);
//						int duration = c.getInt(Define.TAG_DURATION);
//
//						Monitoring_Weekly obj = new Monitoring_Weekly(patient_id, location_id,date_weekday,place_frequency, duration);
//						list.add(obj);
//					}
//					// change isLoad flag to true prevents when parent tab (activity) changed
//					isLoad = true;
//					
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} // JSON Array
//			}
//		}
		drawGraph();
	}
	
	private void drawGraph(){
		
		String[] titles = new String[] { "times"};
//		int size=list.size();
		
		//x axis
		List<double[]> x = new ArrayList<double[]>();
		x.add(new double[] { 1, 2, 3, 4, 5, 6, 7});
//		double[] weekdays=new double[size];
//		for(int i=0;i<list.size();i++){
//			weekdays[i]=list.get(i).getDate_weekday();
//		}
//		x.add(weekdays);
		
		
		//values of lines
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 2, 3, 3, 2, 4, 3, 6});
		
		// colors of lines
		int[] colors = new int[] { Color.GREEN};
		
		//style of lines
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE};
		
		//renderer
		XYMultipleSeriesRenderer renderer = ChartHelper.buildRenderer(colors, styles);
		renderer.setPointSize(10.0f);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
			r.setLineWidth(7);
			r.setFillPoints(true);
		}
		
		XYMultipleSeriesDataset dataset = ChartHelper.buildDataset(titles, x, values);
		
		//other axises labels
//		ChartHelper.setChartSettings(renderer, "Weather data", "Month", "Temperature", 0.5,12.5, 0, 40, Color.LTGRAY, Color.LTGRAY);
		
		renderer.setXLabels(7);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		
		XYSeries waterSeries = new XYSeries("Duration Amount");
		waterSeries.add(1, 10);
		waterSeries.add(2, 14);
		waterSeries.add(3, 25);
		waterSeries.add(4, 22);
		waterSeries.add(5, 33);
		waterSeries.add(6, 30);
		waterSeries.add(7, 10);
		renderer.setBarSpacing(0.5);
		
		XYSeriesRenderer waterRenderer = new XYSeriesRenderer();
		waterRenderer.setColor(Color.argb(250, 0, 210, 250));
		waterRenderer.setDisplayChartValues(true);
		waterRenderer.setChartValuesTextSize(15);
		
		dataset.addSeries(0, waterSeries);
		renderer.addSeriesRenderer(0, waterRenderer);
		
		String[] types = new String[] { BarChart.TYPE, LineChart.TYPE};
		
		if (mChartView == null) {
			FrameLayout layout = (FrameLayout) getView().findViewById(R.id.frag);
			mChartView=ChartFactory.getCombinedXYChartView(getActivity(), dataset, renderer, types);
			layout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
	}
}
