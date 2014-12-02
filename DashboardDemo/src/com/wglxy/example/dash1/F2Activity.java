/*
 * Copyright (C) 2011 Wglxy.com
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

package com.wglxy.example.dash1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * This is the activity for feature 2 in the dashboard application. It displays
 * some text and provides a way to get back to the home activity.
 * 
 */

public class F2Activity extends DashboardActivity {
	
	private TabHost myTabHost;

	/**
	 * onCreate
	 * 
	 * Called when the activity is first created. This is where you should do
	 * all of your normal static set up: create views, bind data to lists, etc.
	 * This method also provides you with a Bundle containing the activity's
	 * previously frozen state, if there was one.
	 * 
	 * Always followed by onStart().
	 * 
	 * @param savedInstanceState
	 *            Bundle
	 */

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_f2);
		setTitleFromActivityLabel(R.id.title_text);
		
		myTabHost = (TabHost) findViewById(R.id.tabhost_patient);
//		myTabHost.setup();
		myTabHost.setup(this.getLocalActivityManager());
		
		
		// adding tabs, have to use this continues way
		// tab 1
		myTabHost.addTab(myTabHost.newTabSpec("tab1")
				.setIndicator(getResources().getString(R.string.partient_home))
				.setContent(R.id.acivity_f2_home));
		// tab 2
		myTabHost.addTab(myTabHost.newTabSpec("tab2")
				.setIndicator(getResources().getString(R.string.partient_sleep))
				.setContent(new Intent().setClass(this, F2Activity_Sleep.class)));
//				.setContent(R.id.acivity_f2_sleep));
		// tab 3
		myTabHost.addTab(myTabHost.newTabSpec("tab3")
				.setIndicator(getResources().getString(R.string.partient_restroom))
				.setContent(R.id.acivity_f2_restroom));
		// tab 4
		myTabHost.addTab(myTabHost.newTabSpec("tab4")
				.setIndicator(getResources().getString(R.string.partient_temperature))
				.setContent(R.id.acivity_f2_temperature));
		// tab 5
		myTabHost.addTab(myTabHost.newTabSpec("tab5")
				.setIndicator(getResources().getString(R.string.partient_location))
				.setContent(R.id.acivity_f2_location));
		
		
	}

} // end class
