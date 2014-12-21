/**
 * 
 */
package kr.ca.cbnu.itrc.model;

import kr.ca.cbnu.itrc.chart.ChartHelper;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

/**
 * @author Jerry
 *
 */
public class FragmentRestroomTabToday extends Fragment {

	private GraphicalView mChartView;
	private LinearLayout linearLayout_chart;
	
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
		return inflater.inflate(R.layout.fragment_tab_today, container, false);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		XYValueSeries newTicketSeries = new XYValueSeries("New Tickets");
		newTicketSeries.add(1f, 2, 14);
		newTicketSeries.add(2f, 2, 12);
		newTicketSeries.add(3f, 2, 18);
		newTicketSeries.add(4f, 2, 5);
		newTicketSeries.add(5f, 3, 1);
		dataset.addSeries(newTicketSeries);
		XYValueSeries fixedTicketSeries = new XYValueSeries("Fixed Tickets");
		fixedTicketSeries.add(1f, 1, 7);
		fixedTicketSeries.add(2f, 1, 4);
		fixedTicketSeries.add(3f, 1, 18);
		fixedTicketSeries.add(4f, 1, 3);
		fixedTicketSeries.add(5f, 5, 1);
		dataset.addSeries(fixedTicketSeries);
		
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		XYSeriesRenderer newTicketRenderer = new XYSeriesRenderer();
		newTicketRenderer.setColor(Color.BLUE);
		renderer.addSeriesRenderer(newTicketRenderer);
		XYSeriesRenderer fixedTicketRenderer = new XYSeriesRenderer();
		fixedTicketRenderer.setColor(Color.GREEN);
		renderer.addSeriesRenderer(fixedTicketRenderer);

		ChartHelper.setChartSettings(renderer, "Project work status", "Priority", "", 0.5,
				5.5, 0, 5, Color.GRAY, Color.LTGRAY);
		renderer.setXLabels(7);
		renderer.setYLabels(7);
		renderer.setShowGrid(false);
		
		if (mChartView == null) {
			linearLayout_chart = (LinearLayout) getView().findViewById(R.id.linearlayout_today_chart);
			mChartView=ChartFactory.getBubbleChartView(getActivity(), dataset, renderer);
			linearLayout_chart.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
	}

}
