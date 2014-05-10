package com.innovation.identity.loginregister.activity;

import com.innovation.identity.activity.R;
import com.innovation.identity.loginregister.model.UserLoginWidgetModel;
import com.innovation.identity.loginregister.service.LoginService;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
	private EditText edt_main_activity_username,edt_main_activity_password;
	private Button btn_main_activity_login,btn_main_activity_register;
	private LoginService loginService;
	private UserLoginWidgetModel userModel;
	private Dialog loadingDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initWidgetAndVar();
		addListener();
	}
	/**
	 * 初始化控件
	 */
	private void initWidgetAndVar(){
		this.edt_main_activity_username=(EditText)this.findViewById(R.id.edt_main_activity_username);
		this.edt_main_activity_password=(EditText)this.findViewById(R.id.edt_main_activity_password);
		this.btn_main_activity_login=(Button)this.findViewById(R.id.btn_main_activity_login);
		this.btn_main_activity_register=(Button)this.findViewById(R.id.btn_main_activity_register);
		this.loadingDialog=new Dialog(this);
		
		this.loginService=new LoginService(this);
		this.userModel=new UserLoginWidgetModel();
		this.userModel.setPassword(this.edt_main_activity_password);
		this.userModel.setUsername(this.edt_main_activity_username);
	}
	/**
	 * 给控件添加监听
	 */
	private void addListener(){
		this.btn_main_activity_login.setOnClickListener(new BtnOnClickListener());
		this.btn_main_activity_register.setOnClickListener(new BtnOnClickListener());
	}
	/**
	 * 监听类
	 * @author Xiaona
	 *
	 */
	private class BtnOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_main_activity_login:
				loadingDialog.show();
				loadingDialog.setCancelable(false);
				loadingDialog.setContentView(R.layout.activity_main_loading_dialog_view);
				loginService.loginService(userModel, loadingDialog);
				break;
			case R.id.btn_main_activity_register:
				
				break;
			default:
				break;
			}
		}
	}
	
}
