/**
 * 
 */
package kr.ca.cbnu.itrc.model.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.os.Looper;
/**
 * @author Jerry
 *
 */
public class WarningSimulation implements Runnable{
	private List<Integer> roomNumlist = new ArrayList();
	private Context context;
	private WarningListenerImpl listenerImpl;
	
	
	public WarningSimulation(Context context){
		this.context=context;
		listenerImpl=new WarningListenerImpl(context);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Looper.prepare();
		for(int i=0;i<2;i++){
			long sleepTime=(long) Math.random()*6000+10000;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random rand=new Random();
			int roomNum=rand.nextInt(27)+100;
			roomNumlist.add(roomNum);
			listenerImpl.giveWarning(new WarningEvent(roomNum));
		}
		Looper.loop();
	}
	
	
}
