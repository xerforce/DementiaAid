/**
 * 
 */
package com.example.dbContrl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Monitoring_Daily;
import com.example.mydashboard.Define;
import com.example.mydashboard.IDrawGraph;
import com.example.mydashboard.JSONParser;
import com.example.mydashboard.R;
import com.example.mydashboard.RetryFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Jerry
 * 
 */
public class DataLoader extends AsyncTask<String, String, String> {
	FragmentManager myFragmentManager;
	Activity contentActivity;
	ProgressDialog mDialog;
	boolean isException;
	JSONObject jsonQuaryResult;
	
	public IDrawGraph drawGraphInterface;

	public DataLoader() {
		isException = false;
	}

	public DataLoader(Activity contentActivity, FragmentManager myFragmentManager, IDrawGraph drawGraphInterface) {
		this.contentActivity = contentActivity;
		this.myFragmentManager=myFragmentManager;
		isException = false;
		this.drawGraphInterface=drawGraphInterface;
	}

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog = new ProgressDialog(contentActivity);
		mDialog.setMessage("Loading...");
		mDialog.setIndeterminate(false);
		mDialog.setCancelable(false);
		mDialog.show();
	}

	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		// updating UI from Background Thread
		int success;
		String failMsg="";
		try {
			// Building Parameters
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

			// Set parameters: FIRST is Column Name, SECOND is Column Value
			// ***Number of Parameters must be even
			for (int i = 0; i < args.length / 2; i++) {
				Log.i("Param " + (i + 1), args[i * 2]);
				Log.i("Param " + (i + 1), args[i * 2 + 1]);
				params.add(new BasicNameValuePair(args[i * 2],
						args[(i * 2) + 1]));
			}

			// getting product details by making HTTP request
			// Note that product details url will use GET request
			jsonQuaryResult = JSONParser.makeHttpRequest(
					Define.URL_GET_FROM_QUERY, "GET", params);

			// check your log for json response
			Log.d("Data", jsonQuaryResult.toString());

			// json success tag
			success = jsonQuaryResult.getInt(Define.TAG_SUCCESS);
			if (success == 1) {
				setJson(jsonQuaryResult);
			} else {
				Log.e("Database", "Failed to load data");
//					String msg = contentActivity.getString(R.string.connection_exception) + "\n"
//							+ contentActivity.getString(R.string.check_internet_connection);
				failMsg="Failed to load data";
				contentActivity.finish();
			}
		} catch (ConnectException e) {
			isException = true;
			failMsg = contentActivity.getString(R.string.connection_exception) + "\n"
					+ contentActivity.getString(R.string.check_internet_connection);
		} catch (ConnectTimeoutException | SocketTimeoutException e) {
			isException = true;
			failMsg = contentActivity.getString(R.string.connection_timeout_exception)
					+ "\n" + contentActivity.getString(R.string.server_might_be_down);
		} catch (Exception e) {
			isException = true;
			failMsg = contentActivity.getString(R.string.server_might_be_down);
		}
		if(failMsg!="")
			Log.d("Exception",failMsg);
		return null;
	}
	
	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	@Override
	protected void onPostExecute(String result) {
		// dismiss the dialog after getting all products
		mDialog.dismiss();

		// check if there is error
		if (isException) {
			showErrorDialogFragment(result);
			return;
		}

		// draw graph
		this.drawGraphInterface.drawGraph(jsonQuaryResult);  //call back the drawGraph function of caller
	}
	
	private void showErrorDialogFragment(String msg) {
		RetryFragment retryFragment = (RetryFragment) myFragmentManager.findFragmentByTag(Define.TAG_RETRY_FRAGMENT);
		retryFragment.setMessage(msg); // set the message for the retry fragment
		retryFragment.setLabel(0, Define.TAG_TODAY); // set the label to identify that this fragment is being replaced

		FragmentTransaction ft = myFragmentManager.beginTransaction();
		ft.detach(myFragmentManager.findFragmentByTag(Define.TAG_TODAY)); // detach the current fragment
		ft.show(retryFragment).commit(); // show the retry fragment
	}
	
	public JSONObject getJson() {
		return jsonQuaryResult;
	}

	public void setJson(JSONObject jsonQuaryResult) {
		this.jsonQuaryResult = jsonQuaryResult;
	}
}
