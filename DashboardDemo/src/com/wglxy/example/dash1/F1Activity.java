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

import java.text.SimpleDateFormat;
import java.util.Date; 
import java.util.Calendar; 

import android.R.integer;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

/**
 * This is the activity for feature 1 in the dashboard application. It displays
 * some text and provides a way to get back to the home activity.
 * 
 */

public class F1Activity extends DashboardActivity {

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
		setContentView(R.layout.activity_f1);
		setTitleFromActivityLabel(R.id.title_text);

		myTabHost = (TabHost) findViewById(R.id.tabhost);
		myTabHost.setup();

		// adding tabs
		// tab 1
		TabSpec spec1 = myTabHost.newTabSpec("tab1");
		spec1.setIndicator("tab one");// set text and image
		spec1.setContent(R.id.tab1);
		GridLayout roomGrid = (GridLayout) findViewById(R.id.roomGrid1);
		int rowNum = 9;
		int columnNum = 3;
		addViewToGridlayout(rowNum, columnNum, roomGrid);
		myTabHost.addTab(spec1);

		// tab 2
		TabSpec spec2 = myTabHost.newTabSpec("tab2");
		spec2.setIndicator("tab two");// set text and image
		spec2.setContent(R.id.tab2);
		myTabHost.addTab(spec2);

		// tab 3
		TabSpec spec3 = myTabHost.newTabSpec("tab3");
		spec3.setIndicator("tab three");// set text and image
		spec3.setContent(R.id.tab3);
		myTabHost.addTab(spec3);

	}

	public void addViewToGridlayout(int rowNum, int columnNum,
			GridLayout roomGrid) {
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				Button bn = new Button(this);
				int roomNum = 100 + i * 3 + j + 1;
				bn.setText(roomNum + "");
//				bn.setTextSize(30sp);
				bn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
	//				TypedValue.COMPLEX_UNIT_PX : Pixels
	//				TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
	//				TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
				GridLayout.Spec rowSpec = GridLayout.spec(i);
				GridLayout.Spec columnSpec = GridLayout.spec(j);
				GridLayout.LayoutParams params = new GridLayout.LayoutParams(
						rowSpec, columnSpec);
				params.setGravity(Gravity.FILL);
				bn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Date now = new Date(); 
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//system date time
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd HH:mm");//
						String dateTimeString=dateFormat.format(now);
						giveWarning("Patient OOO.\n1F Room "+((Button)v).getText()+" falled over at "+dateTimeString);
					}
				});
				roomGrid.addView(bn, params);
			}
		}
	}

	public void giveWarning(String warningContent) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Emergency!!!");
		alertDialogBuilder.setIcon(R.drawable.warning);
//		alertDialogBuilder.setMessage(R.string.partient_warning);
		alertDialogBuilder.setMessage(warningContent);
//		alertDialogBuilder.setPositiveButton(R.string.positive_button,
//				new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						Intent positiveActivity = new Intent(
//								getApplicationContext(), PositiveActivity.class);
//						startActivity(positiveActivity);
//					}
//				});
//		alertDialogBuilder.setNegativeButton(R.string.negative_button,
//				new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						Intent negativeActivity = new Intent(
//								getApplicationContext(), NegativeActivity.class);
//						startActivity(negativeActivity);
//					}
//				});
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(F1Activity.this,"Confirmed", 30000).show();
					}
				});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(F1Activity.this,"Canceled", 30000).show();
			}
		});
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

} // end class
