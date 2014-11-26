/**
 * 
 */
package com.wglxy.example.dash1.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wglxy.example.dash1.F1Activity;
import com.wglxy.example.dash1.R;

/**
 * @author Jerry
 *
 */
public class MyWarning extends AlertDialog{
	private Context context;
	public MyWarning(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	public void giveWarning(String warningContent) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Emergency!!!");
		alertDialogBuilder.setIcon(R.drawable.warning);
		// alertDialogBuilder.setMessage(R.string.partient_warning);
		alertDialogBuilder.setMessage(warningContent);
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.e("Dialog", "ok has been clicked");
					}
				});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.e("Dialog", "cancel has been clicked");
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	public static void giveWarning(String warningContent, Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Emergency!!!");
		alertDialogBuilder.setIcon(R.drawable.warning);
		// alertDialogBuilder.setMessage(R.string.partient_warning);
		alertDialogBuilder.setMessage(warningContent);
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.e("Dialog", "ok has been clicked");
					}
				});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.e("Dialog", "cancel has been clicked");
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
