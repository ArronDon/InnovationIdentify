package com.innovation.identity.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.innovation.identity.loginregister.activity.LoginActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
/**
 * ��Activity,��ҳ���棬���˵����Ҳ�tabhost
 * @author Xiaona
 *
 */
@SuppressWarnings("deprecation")
public class MainActivity extends SlidingActivity {
	private List<View> listViews;// ��ͼ�б�
	private Context context = null;
	LocalActivityManager manager = null;
	TabHost tabHost = null;
	private ViewPager pager = null;
	private SlidingMenu slidingMenu;
	private Button btn_activity_behind_login;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.activity_main_behind);
		
		initWidgetAndVar();
		addListener();
		
		slidingMenu = getSlidingMenu();
		slidingMenu.setShadowWidth(50);
		slidingMenu.setBehindOffset(200);
		slidingMenu.setFadeDegree(0.35f);
		//slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		/*
		 * ��һ��Activity��һ��������ʾ����Activity��Ҫ�õ�LocalActivityManagerity
		 * ����������manager��ȡView��manager.startActivity(String,
		 * Intent).getDecorView()
		 */
		tabHost.setup();
		tabHost.setup(manager);// ʵ����THost
		context = MainActivity.this;
		Intent i1 = new Intent(context, T1Activity.class);
		Intent i2 = new Intent(context, T2Activity.class);
		Intent i3 = new Intent(context, T3Activity.class);
		listViews = new ArrayList<View>(); // ʵ����listViews
		listViews.add(manager.startActivity("T1", i1).getDecorView());
		listViews.add(manager.startActivity("T2", i2).getDecorView());
		listViews.add(manager.startActivity("T3", i3).getDecorView());
//		LinearLayout tabIndicator1 = (LinearLayout) LayoutInflater.from(this)
//				.inflate(R.layout.activity_t1_activity, null);
//		LinearLayout tabIndicator2 = (LinearLayout) LayoutInflater.from(this)
//				.inflate(R.layout.activity_t2_activity, null);
//		LinearLayout tabIndicator3 = (LinearLayout) LayoutInflater.from(this)
//				.inflate(R.layout.activity_t3_activity, null);
		tabHost.addTab(tabHost.newTabSpec("A").setIndicator("����")
				.setContent(i1));
		// TabSpec������A��B��C���Ǹ���tab��Id
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator("ʶ��")
				.setContent(i2));
		tabHost.addTab(tabHost.newTabSpec("C").setIndicator("�ղ�")
				.setContent(i3));
		// Ϊtabhost���ü���
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {
					@Override
					public void onTabChanged(String tabId) {
						if ("A".equals(tabId)) {
							pager.setCurrentItem(0);// ��tabhost�ļ����ı�Viewpager
						}
						if ("B".equals(tabId)) {
							pager.setCurrentItem(1);
						}
						if ("C".equals(tabId)) {
							pager.setCurrentItem(2);
						}
					}
				});

			}
		});

		// ΪViewPager��������ü���
		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);// ��Viewpager�ı�tabhost
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	private void initWidgetAndVar(){
		tabHost = (TabHost) findViewById(R.id.tabhost);
		pager = (ViewPager) findViewById(R.id.viewpager);
		btn_activity_behind_login=(Button) findViewById(R.id.btn_activity_behind_login);
		
	}
	private void addListener(){
		btn_activity_behind_login.setOnClickListener(new BtnOnClickListener());
	}
	private class BtnOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_activity_behind_login:
				Intent intent=new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}
		
	}
	private class MyPageAdapter extends PagerAdapter {
		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// MenuInflater inflater = getSupportMenuInflater();
		// inflater.inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// toggle����SlidingMenu�Զ��ж��Ǵ򿪻��ǹر�
			toggle();
			// getSlidingMenu().showMenu(); // show menu
			// getSlidingMenu().showContent(); // show content
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}