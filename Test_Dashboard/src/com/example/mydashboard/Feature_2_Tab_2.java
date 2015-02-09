package com.example.mydashboard;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tabandswipe.MySectionGenerator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Feature_2_Tab_2 extends Activity{

	int[] imgResources = new int[5];	
	ImageView imgPSleepingMode;
	TextView lblAccumulatedTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_sleeping);
		
		imgResources[0] = R.drawable.livingroom_pose_faceleft;
		imgResources[1] = R.drawable.livingroom_pose_faceright;
		imgResources[2] = R.drawable.livingroom_pose_faceup;
		imgResources[3] = R.drawable.livingroom_pose_laydown;
		imgResources[4] = R.drawable.livingroom_pose_layup;

		imgPSleepingMode = (ImageView) findViewById(R.id.imgPSleepingMode);
		lblAccumulatedTime = (TextView) findViewById(R.id.lblAccumulatedTime);			
		
		//create swapping pages
/*		try{			
			
			MySectionGenerator.setupSliderWithCircleIndicator(this,
				 R.id.pager, R.id.indicator, images);			

		}catch(Exception e){
			Log.e("onActivityCreated", e.getMessage());
		}
		*/
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
				
		int rnd = (int) (Math.random() * 4);		
		try{			
			imgPSleepingMode.setImageResource(imgResources[rnd]);
		}catch(IndexOutOfBoundsException e){			
			imgPSleepingMode.setImageResource(imgResources[0]);
		}
		lblAccumulatedTime.setText(getString(R.string.accumulated_time)
				+ ": " + String.format("%2d:%2d:%2d", 0, (rnd * 10), (rnd + 6)) );
	}
	
	

}
