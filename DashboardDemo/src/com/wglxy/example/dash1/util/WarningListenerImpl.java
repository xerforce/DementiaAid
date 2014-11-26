/**
 * 
 */
package com.wglxy.example.dash1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
/**
 * @author Jerry
 *
 */
public class WarningListenerImpl implements IWarningListener{
	private Context context;
	
	public WarningListenerImpl(Context context){
		this.context=context;
	}

	@Override
	public void giveWarning(WarningEvent e) {
		// TODO Auto-generated method stub
		//give warning...
		Date now = new Date();
		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//system date
		// time
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd HH:mm");//
		String dateTimeString = dateFormat.format(now);
		String warningContent="Patient OOO.\n1F Room "
				+ e.getSource().toString() + " falled over at "
				+ dateTimeString;
		MyWarning.giveWarning(warningContent, context);
	}

}
