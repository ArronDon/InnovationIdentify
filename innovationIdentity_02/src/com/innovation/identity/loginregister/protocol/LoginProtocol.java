package com.innovation.identity.loginregister.protocol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.innovation.identity.common.BaseProtocol;
import com.innovation.identity.common.Constant;
import com.innovation.identity.common.SharedPreferencesTool;
import com.innovation.identity.loginregister.model.User;

/**
 * 验证登陆
 * 
 * @author Xiaona
 * 
 */
public class LoginProtocol extends BaseProtocol {
	private final static String url = Constant.SERVER_ADDRESS+"user!login";
	private Gson gson = new Gson();
	private User userJson;
	private SharedPreferencesTool sharedPreferencesTool;
	@SuppressLint("SimpleDateFormat")
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.0");

	public boolean checkLogin(User user, Context context) {
		sharedPreferencesTool = SharedPreferencesTool
				.getSharedPreferencesToolInstance(context);
		try {
			addNameValuePair("username", user.getUsername());
			addNameValuePair("password", user.getPassword());
			pack(url);
			parse();
			userJson = userFromUserJSON();
			if (userJson == null) {
				return false;
			} else {
				Map<String, String> params = new HashMap<String, String>();
				params.put("userId", String.valueOf(userJson.getUserId()));
				params.put("username", userJson.getUsername());
				params.put("gender", userJson.getGender());
				params.put("password", userJson.getPassword());
				params.put("portrait", userJson.getPortrait());
				params.put("email", userJson.getEmail());
				params.put("cellphone", userJson.getCellphone());
				params.put("createOn",
						dateFormat.format(userJson.getCreateOn()));
				sharedPreferencesTool.saveUserPrefences(params);
				Log.v("tag", "用户"+userJson.getUsername()+"登陆成功");
				return true;
			}
		} catch (Exception e) {
			Log.v("Exception", "Message:登陆出现异常    method:LoginProtocol.checkLogin()");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据服务器返回的json数据生成user对象
	 * 
	 * @return
	 * @throws JSONException
	 * @throws JsonSyntaxException
	 */
	private User userFromUserJSON() throws JsonSyntaxException, JSONException {
		return gson.fromJson(getJSON().getString("userJson").toString(),
				User.class);
	}

}
