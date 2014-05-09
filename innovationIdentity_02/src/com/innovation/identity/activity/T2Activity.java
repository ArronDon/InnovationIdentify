package com.innovation.identity.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class T2Activity extends Activity{
	private Button btn_activity_t2_click;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t2_activity);
		initWidgetAndVar();
		addListener();
	}
	private void initWidgetAndVar(){
		btn_activity_t2_click=(Button) this.findViewById(R.id.btn_activity_t2_click);
	}
	private void addListener(){
		btn_activity_t2_click.setOnClickListener(new BtnOnClickListener());
	}
	private class BtnOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_activity_t2_click:
				btn_activity_t2_click.setText("Please Stop");
				break;
			default:
				break;
			}
		}
	}
}
