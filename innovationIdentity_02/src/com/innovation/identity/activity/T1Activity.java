package com.innovation.identity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class T1Activity extends Activity{
	private ListView list_activity_t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t1_activity);
		initWidgetAndVar();
		addListener();
	}
	private void initWidgetAndVar(){
		list_activity_t1=(ListView) this.findViewById(R.id.list_activity_t1);
	}
	private void addListener(){
		list_activity_t1.setOnItemClickListener(new ItemOnClickListener());;
	}
	private class ItemOnClickListener implements OnItemClickListener{		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}
	}
}
