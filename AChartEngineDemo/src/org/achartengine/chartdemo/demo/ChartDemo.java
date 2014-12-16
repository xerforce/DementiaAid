/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.achartengine.chartdemo.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.achartengine.chartdemo.demo.chart.AverageCubicTemperatureChart;
import org.achartengine.chartdemo.demo.chart.AverageTemperatureChart;
import org.achartengine.chartdemo.demo.chart.BudgetDoughnutChart;
import org.achartengine.chartdemo.demo.chart.BudgetPieChart;
import org.achartengine.chartdemo.demo.chart.CombinedTemperatureChart;
import org.achartengine.chartdemo.demo.chart.IDemoChart;
import org.achartengine.chartdemo.demo.chart.MultipleTemperatureChart;
import org.achartengine.chartdemo.demo.chart.PieChartBuilder;
import org.achartengine.chartdemo.demo.chart.ProjectStatusBubbleChart;
import org.achartengine.chartdemo.demo.chart.ProjectStatusChart;
import org.achartengine.chartdemo.demo.chart.SalesBarChart;
import org.achartengine.chartdemo.demo.chart.SalesComparisonChart;
import org.achartengine.chartdemo.demo.chart.SalesGrowthChart;
import org.achartengine.chartdemo.demo.chart.SalesStackedBarChart;
import org.achartengine.chartdemo.demo.chart.ScatterChart;
import org.achartengine.chartdemo.demo.chart.SensorValuesChart;
import org.achartengine.chartdemo.demo.chart.TemperatureChart;
import org.achartengine.chartdemo.demo.chart.TrigonometricFunctionsChart;
import org.achartengine.chartdemo.demo.chart.WeightDialChart;
import org.achartengine.chartdemo.demo.chart.XYChartBuilder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ChartDemo extends ListActivity {
	private IDemoChart[] mCharts = new IDemoChart[] {
			new AverageTemperatureChart(),// line chart
			new AverageCubicTemperatureChart(),//cubic line chart
			new SalesStackedBarChart(), //bar chart
			new SalesBarChart(),//bar chart
			new TrigonometricFunctionsChart(), //smooth line chart
			new ScatterChart(),//scatter chart
			new SalesComparisonChart(), //interpolated line and area chart
			new ProjectStatusChart(),//hard line chart
			new SalesGrowthChart(), //area chart
			new BudgetPieChart(),//pie chart
			new BudgetDoughnutChart(), //doughnut chart
			new ProjectStatusBubbleChart(),//bubble chart
			new TemperatureChart(), //vertical range chart
			new WeightDialChart(),//dial chart
			new SensorValuesChart(), //line dots chart
			new CombinedTemperatureChart(),//combined chart
			new MultipleTemperatureChart() };//multiple Y scales and axis smooth line chart

	private String[] mMenuText;

	private String[] mMenuSummary;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int length = mCharts.length;
		mMenuText = new String[length + 3];
		mMenuSummary = new String[length + 3];
		mMenuText[0] = "Embedded line chart demo";
		mMenuSummary[0] = "A demo on how to include a clickable line chart into a graphical activity";
		mMenuText[1] = "Embedded pie chart demo";
		mMenuSummary[1] = "A demo on how to include a clickable pie chart into a graphical activity";
		
		for (int i = 0; i < length; i++) {
			mMenuText[i + 2] = mCharts[i].getName();
			mMenuSummary[i + 2] = mCharts[i].getDesc();
		}
		mMenuText[length + 2] = "Random values charts";
		mMenuSummary[length + 2] = "Chart demos using randomly generated values";
		setListAdapter(new SimpleAdapter(this, getListValues(),
				android.R.layout.simple_list_item_2, new String[] {
						IDemoChart.NAME, IDemoChart.DESC }, new int[] {
						android.R.id.text1, android.R.id.text2 }));
	}

	private List<Map<String, String>> getListValues() {
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		int length = mMenuText.length;
		for (int i = 0; i < length; i++) {
			Map<String, String> v = new HashMap<String, String>();
			v.put(IDemoChart.NAME, mMenuText[i]);
			v.put(IDemoChart.DESC, mMenuSummary[i]);
			values.add(v);
		}
		return values;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = null;
		if (position == 0) {
			intent = new Intent(this, XYChartBuilder.class);
		} else if (position == 1) {
			intent = new Intent(this, PieChartBuilder.class);
		} else if (position <= mCharts.length + 1) {
			intent = mCharts[position - 2].execute(this);
		} else {
			intent = new Intent(this, GeneratedChartDemo.class);
		}
		startActivity(intent);
	}
}