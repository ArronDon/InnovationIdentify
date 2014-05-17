package com.innovation.identity.activity;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

import com.actionbarsherlock.R.string;
import com.innovation.identity.activity.MainActivity.OnTabActivityResultListener;

import android.R.array;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class T2Activity extends Activity implements OnTabActivityResultListener{
	private Button btn_activity_t2_select;
	private Button btn_activity_t2_camera;
	private Button btn_actitity_t2_search;
	private ImageView imgview_activity_t2_show;
	private Uri imgUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t2_activity);
		initWidgetAndVar();
		addListener();
	}
	private void initWidgetAndVar(){
		btn_activity_t2_select=(Button) this.findViewById(R.id.btn_activity_t2_select_image);
		btn_activity_t2_camera = (Button) this.findViewById(R.id.btn_activity_t2_start_camera);
		
		btn_actitity_t2_search = (Button) this.findViewById(R.id.btn_activity_t2_search);
		imgview_activity_t2_show = (ImageView) this.findViewById(R.id.imgview_activity_t2_show);
	}
	private void addListener(){
		btn_activity_t2_select.setOnClickListener(new BtnOnClickListener());
		btn_activity_t2_camera.setOnClickListener(new BtnOnClickListener());
		btn_actitity_t2_search.setOnClickListener(new BtnOnClickListener());
		
	}
	private class BtnOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_activity_t2_select_image:
				imgview_activity_t2_show.setImageResource(R.drawable.ic_launcher);
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				getParent().startActivityForResult(intent, 2);
				break;
			case R.id.btn_activity_t2_start_camera:			
				takePicture();
				break;
			default:
				break;
			}
		}
	}
	private void takePicture(){
		//获取系统时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date curDate = new Date(System.currentTimeMillis());	
		//设置图片名
		String name="IMG_"+formatter.format(curDate)+".jpg";
		//设置保存路径
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Environment.getExternalStorageDirectory()+"/InnovationIdentify/Image/");
		imgUri = Uri.fromFile(new File(file,name));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);	
		//启动相机
		
		getParent().startActivityForResult(intent,1);
	}
	@Override
	public void onTabActivityResult(int requestCode,int resultCode,Intent data){
		
		
		switch (requestCode) {
		case 1://照相机
			if(resultCode==RESULT_OK){
				/*Uri uri=data.getData();
				ContentResolver cr = this.getContentResolver();
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
					imgview_activity_t2_show.setImageBitmap(bitmap);
					imgview_activity_t2_show.setVisibility(View.VISIBLE);
				} catch (Exception e) {
					// TODO: handle exception
				}*/
				if(data != null){
					//btn_activity_t2_camera.setText("空");
					imgview_activity_t2_show.setImageResource(R.drawable.ic_launcher);
					Bundle extra = data.getExtras();
						
					Bitmap bitmap = (Bitmap)extra.get("data");
					imgview_activity_t2_show.setImageBitmap(bitmap);;
					
				}else {
					
					System.out.println("不空");
				}
			}
			break;
		case 2://系统图库
			if(resultCode==RESULT_OK){
				if(data!=null){
					System.out.println("不空");
					imgview_activity_t2_show.setImageResource(R.drawable.ic_launcher);
					Uri selectedImage = data.getData();
	                //this.imgview_activity_t2_show.setImageURI(selectedImage);
	                this.imgview_activity_t2_show.setImageResource(R.drawable.ic_launcher);
	                btn_activity_t2_camera.setText("显示啊 MD");
	                System.out.println("xianshi");
				}
				
				
				
			}
			break;
		default:
			break;
		}
		
		
		
	}//onActivityResult
	protected void onResume() {
		super.onResume();
		
	}
}
