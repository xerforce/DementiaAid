/**
 * 
 */
package kr.ca.cbnu.itrc.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

/**
 * @author Jerry
 *
 */
public class F2ActivityRestroom extends FragmentActivity {

	/**
     * Called when the activity is first created.
     */
	private RadioGroup rgs;
	public List<Fragment> fragments=new ArrayList<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acivity_f2_restroom);
		
		fragments.add(new FragmentRestroomTabToday());
		fragments.add(new FragmentRestroomTabWeekly());
		fragments.add(new FragmentRestroomTabMonthly());
		
		rgs=(RadioGroup) findViewById(R.id.tabs_rg);
		
		RestroomFragmentTabsAdapter tabAdapter = new RestroomFragmentTabsAdapter(this, fragments, rgs,R.id.tab_frequency_content);
		tabAdapter.setOnRgsExtraCheckedChangedListener(new RestroomFragmentTabsAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });
	}
}
