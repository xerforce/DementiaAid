package com.wglxy.example.dash1;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

public class F2Activity_Sleep extends Activity {
	// the key feature for sliding images
	private ViewPager viewPager;
	// for lin
	private ArrayList<View> imageViewLinearlayouts;
	// for each new imageview
	private ImageView imageView;
	// for
	private ImageView[] tipsImageViews;
	// for this activity view group
	private ViewGroup mainGroup;
	// for all the imageviews
	private ViewGroup tipsImageviewGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();
		mainGroup = (ViewGroup) inflater.inflate(R.layout.acivity_f2_sleep,
				null);
		viewPager = (ViewPager) mainGroup
				.findViewById(R.id.viewpager_guidePages);
		tipsImageviewGroup = (ViewGroup) mainGroup
				.findViewById(R.id.tipsImageViewGroup);

		int[] intImgs = new int[] { R.drawable.livingroom_pose_faceleft,
				R.drawable.livingroom_pose_faceright,
				R.drawable.livingroom_pose_faceup,
				R.drawable.livingroom_pose_laydown,
				R.drawable.livingroom_pose_layup };
		int imagesNum = intImgs.length;

		imageViewLinearlayouts = new ArrayList<View>();
		for (int i = 0; i < imagesNum; i++) {
			LinearLayout layout = new LinearLayout(this);
			LayoutParams ltp = new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
			final ImageView imageView = new ImageView(this);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(intImgs[i]);
			layout.addView(imageView, ltp);
			imageViewLinearlayouts.add(layout);
		}

		tipsImageViews = new ImageView[imagesNum];
		for (int j = 0; j < imagesNum; j++) {
			imageView = new ImageView(F2Activity_Sleep.this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(20, 0, 20, 0);
			tipsImageViews[j] = imageView;
			if (j == 0) {
				tipsImageViews[j]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tipsImageViews[j]
						.setBackgroundResource(R.drawable.page_indicator);
			}
			tipsImageviewGroup.addView(tipsImageViews[j]);
		}

		setContentView(mainGroup);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.f2_activity__sleep, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViewLinearlayouts.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(imageViewLinearlayouts.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(imageViewLinearlayouts.get(arg1));
			return imageViewLinearlayouts.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	// for tips changing
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			for (int i = 0; i < tipsImageViews.length; i++) {
				tipsImageViews[arg0]
						.setBackgroundResource(R.drawable.page_indicator_focused);

				if (arg0 != i) {
					tipsImageViews[i]
							.setBackgroundResource(R.drawable.page_indicator);
				}
			}
		}

	}
}
