package com.example.mydashboard;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Monitoring_Daily;
import com.example.mydashboard.FragmentRestroomTabToday.LoadData;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.Legend.LegendPosition;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Feature_2_Tab_5 extends Activity{
	
	TextView lblRoomNumber;
	TextView lblDetectedTime;
	TextView lblCurrentTime;
	
	boolean isLoad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_location);

		lblRoomNumber = (TextView) findViewById(R.id.lblRoomNumber);
		lblDetectedTime = (TextView) findViewById(R.id.lblDetectedTime);
		lblCurrentTime = (TextView) findViewById(R.id.lblCurrentTime);

		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		final Handler handler = new Handler();
		Runnable run = new Runnable() {			
			@Override
			public void run() {
				lblCurrentTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
				handler.postDelayed(this, 1000);
			}
		};		
		
		handler.post(run);
		
		/*if(!isLoad){
			String pid = this.getIntent().getStringExtra(Define.TAG_ID);
			Log.d("pid in Today", pid);
			
			new LoadData().execute(
					"sql_string", //param1 name 
					"SELECT * FROM monitoring_daily_r1 WHERE date_id=2011010109 AND patient_id=2", //param1 value
					"params_string", //param2 name
					Define.TAG_PATIENT_ID + "," + Define.TAG_LOCATION_ID + "," +
							Define.TAG_ORDER + "," + Define.TAG_DURATION); //param2 value
			
			//change isLoad flag to true
			//prevents when parent tab (activity) changed
			isLoad = true;
		}*/
	}
	
	/*
	class LoadData extends AsyncTask<String, String, String> {
		ProgressDialog mDialog;
		boolean isException;
		
		public LoadData(){					
			isException = false;
		}
		
		
	    *//**
	     * Before starting background thread Show Progress Dialog
	     * *//*
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        mDialog = new ProgressDialog(Feature_2_Tab_5.this);
	        mDialog.setMessage("Loading...");
	        mDialog.setIndeterminate(false);
	        mDialog.setCancelable(false);
	        mDialog.show();
	    }

	    *//**
	     * getting data from url
	     * *//*
	    @Override
	    protected String doInBackground(String... args) {
	        
	    	// updating UI from Background Thread        
	    	int success;
	        try {     	
	        	// Building Parameters
	        	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	            
	        	//Set parameters: FIRST is Column Name, SECOND is Column Value
	        	//***Number of Parameters must be even
	        	if(args.length>0){
	        		for(int i=0; i<args.length/2; i++){
	        			Log.i("Param " + (i+1), args[i*2]);
	        			Log.i("Param " + (i+1), args[i*2 + 1]);
	        			params.add(new BasicNameValuePair(args[i*2], args[(i*2) + 1]));
	        		}
	        		
		            // getting product details by making HTTP request
		            // Note that product details url will use GET request        	
		        	JSONObject json = JSONParser.makeHttpRequest
		        			(Define.URL_GET_FROM_QUERY, "GET", params);
		
		            // check your log for json response
		            Log.d("Data", json.toString());	
		            
		            // json success tag
		            success = json.getInt(Define.TAG_SUCCESS);
		            if (success == 1) {
		                // successfully received product details
		                JSONArray dataArray = json.getJSONArray(Define.TAG_OBJECT_ARRAY); // JSON Array                
		
		                // looping through All Products
		                for (int i = 0; i < dataArray.length(); i++) {
		                    JSONObject c = dataArray.getJSONObject(i);
		
		                    // Storing each json item in variable
		                    int patient_id = c.getInt(Define.TAG_PATIENT_ID);                    
		                    int location_id = c.getInt(Define.TAG_LOCATION_ID);
		                    int order = c.getInt(Define.TAG_ORDER);
		                    int duration = c.getInt(Define.TAG_DURATION);
		                    
		                    Monitoring_Daily obj = new Monitoring_Daily
		                    		(patient_id, location_id, order, duration);                                  
		                }                
		                
		            }else{	            	
		            	Log.e("Database", "Failed to load data");
		            }
	        	}else{
	        		Log.e("URL", "No Parameter");
	        	}
	        	
	        } catch(ConnectException e){
	        	isException = true;
	        	String msg = getString(R.string.connection_exception) + 
	        			"\n" + getString(R.string.check_internet_connection);
	        	return msg;
	        } 	
	        catch(ConnectTimeoutException | SocketTimeoutException e){
	        	isException = true;
	        	String msg = getString(R.string.connection_timeout_exception) + 
	        			"\n" + getString(R.string.server_might_be_down);
	        	return msg;        
	        } catch (Exception e) {
	        	isException = true;
	        	String msg = getString(R.string.server_might_be_down);
	        	return msg;           
	        }

	        return null;
	    }
	    
	    
	    
	    *//**
	     * After completing background task Dismiss the progress dialog
	     * **//*
	    @Override
	    protected void onPostExecute(String result) {
	    	// dismiss the dialog after getting all products    	
	        mDialog.dismiss(); 		    	  

	    }  
	    

	}
*/
}
