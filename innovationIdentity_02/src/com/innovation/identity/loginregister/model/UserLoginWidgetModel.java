package com.innovation.identity.loginregister.model;

import android.widget.EditText;


/**
 * 用于登陆的控件模型
 * @author Xiaona
 *
 */
public class UserLoginWidgetModel {
	private EditText username;
	private EditText password;
	public EditText getUsername() {
		return username;
	}
	public void setUsername(EditText username) {
		this.username = username;
	}
	public EditText getPassword() {
		return password;
	}
	public void setPassword(EditText password) {
		this.password = password;
	}
	
	
}
