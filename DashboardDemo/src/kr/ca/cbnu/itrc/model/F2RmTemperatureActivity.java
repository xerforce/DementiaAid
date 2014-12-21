package kr.ca.cbnu.itrc.model;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.RangeCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.view.ViewGroup.LayoutParams;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import kr.ca.cbnu.itrc.chart.*;

public class F2RmTemperatureActivity extends FragmentActivity {
	/** The chart view that displays the data. */
	private GraphicalView mChartView;

	private LinearLayout linearLayout_chart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acivity_f2_temperature);
	}

	@Override
	protected void onResume() {
		super.onResume();
		double[] minValues = new double[] { -24, -19, -10, -1, 7, 12, 15, 14,
				9, 1, -11, -16 };
		double[] maxValues = new double[] { 7, 12, 24, 28, 33, 35, 37, 36, 28,
				19, 11, 4 };

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		RangeCategorySeries series = new RangeCategorySeries("Temperature");
		int length = minValues.length;
		for (int k = 0; k < length; k++) {
			series.add(minValues[k], maxValues[k]);
		}
		dataset.addSeries(series.toXYSeries());
		int[] colors = new int[] { Color.CYAN };
		XYMultipleSeriesRenderer renderer = ChartHelper
				.buildBarRenderer(colors);
		ChartHelper.setChartSettings(renderer, "Monthly temperature range",
				"Month", "Celsius degrees", 0.5, 12.5, -30, 45, Color.GRAY,
				Color.LTGRAY);
		renderer.setBarSpacing(0.5);
		renderer.setXLabels(0);
		renderer.setYLabels(10);
		renderer.addXTextLabel(1, "Jan");
		renderer.addXTextLabel(3, "Mar");
		renderer.addXTextLabel(5, "May");
		renderer.addXTextLabel(7, "Jul");
		renderer.addXTextLabel(10, "Oct");
		renderer.addXTextLabel(12, "Dec");
		renderer.addYTextLabel(-25, "Very cold");
		renderer.addYTextLabel(-10, "Cold");
		renderer.addYTextLabel(5, "OK");
		renderer.addYTextLabel(20, "Nice");
		renderer.setMargins(new int[] { 30, 70, 10, 0 });
		renderer.setYLabelsAlign(Align.RIGHT);
		SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
		r.setDisplayChartValues(true);
		r.setChartValuesTextSize(12);
		r.setChartValuesSpacing(3);
		r.setGradientEnabled(true);
		r.setGradientStart(-20, Color.BLUE);
		r.setGradientStop(20, Color.GREEN);

		if (mChartView == null) {
			linearLayout_chart = (LinearLayout) findViewById(R.id.linearlayout_temperature_chart);
			mChartView = ChartFactory.getRangeBarChartView(this, dataset, renderer,
					BarChart.Type.DEFAULT);
	
			linearLayout_chart.addView(mChartView, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}else {
			mChartView.repaint();
		}
	}
}
