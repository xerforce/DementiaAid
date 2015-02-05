package com.example.mydashboard;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;

public class Feature_2_Tab_1 extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feature_2_tab_1);

	}
	
	
	public void doClick(View v){
		int id = v.getId();
		TabHost tab = ((TabActivity)getParent()).getTabHost();
		
		switch(id){
		case R.id.btnPSleep :
			tab.setCurrentTab(1); //1-sleep			
			break;
		case R.id.btnPRestroom :
			tab.setCurrentTab(2); //2-restroom
			break;
		case R.id.btnPTemperature :
			tab.setCurrentTab(3); //3-temperature
			break;
		case
		R.id.btnPLocation :
			tab.setCurrentTab(4); //4-location
			break;
		}
	}
	

}
