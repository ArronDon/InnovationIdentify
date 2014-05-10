package com.innovation.identity.loginregister.service;

import com.innovation.identity.activity.MainActivity;
import com.innovation.identity.common.Constant;
import com.innovation.identity.loginregister.model.User;
import com.innovation.identity.loginregister.model.UserLoginWidgetModel;
import com.innovation.identity.loginregister.protocol.LoginProtocol;
import com.innovation.identity.util.NetTools;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class LoginService {
	private Context context;
	private static Dialog loadingDialog;
	private static UserLoginWidgetModel userModel;
	public LoginService(Context context){
		this.context=context;
		
	}
	/**
	 * 登陆时使用的线程
	 * @author Xiaona
	 *
	 */
	private class LoginServiceThread extends Thread{
		private Handler handler;
		private Bundle bundle;
		private User user;
		public LoginServiceThread(UserLoginWidgetModel userModel,Handler handler){
			this.handler=handler;
			LoginService.userModel=userModel;
		}
		private void sendCode(int code){
			Message message=new Message();
			bundle.putInt("code", code);
			message.setData(bundle);
			handler.sendMessage(message);
		}
		@Override
		public void run() {
			bundle=new Bundle();
			user=new User();
			LoginProtocol loginProtocol=new LoginProtocol();
			user.setUsername(userModel.getUsername().getText().toString().trim());
			user.setPassword(userModel.getPassword().getText().toString().trim());
			if(!NetTools.isNetworkAvailable(context)){
				sendCode(-1);//无网络连接
			}
			if(loginProtocol.checkLogin(user,context)){
				sendCode(1);//登陆成功
			}
			else{
				sendCode(0);//用户名密码不正确
			}
		}
	}
	private Handler handler=new Handler(){
		@SuppressLint("HandlerLeak")
		public void handleMessage(Message msg) {
			loadingDialog.dismiss();//关闭对话框
			Bundle bundle=msg.getData();
			int code=bundle.getInt("code");
			switch (code) {
			case -1:
				Toast.makeText(context, Constant.NETWORK_NOT_AVAILABLE, Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Toast.makeText(context, Constant.LOGIN_USERNAME_PASSWORD_WRONG, Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Intent intent=new Intent(context,MainActivity.class);
				context.startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
	public void loginService(UserLoginWidgetModel userModel,Dialog dialog){
		LoginService.loadingDialog=dialog;
		new LoginServiceThread(userModel,handler).start();
	}
}
