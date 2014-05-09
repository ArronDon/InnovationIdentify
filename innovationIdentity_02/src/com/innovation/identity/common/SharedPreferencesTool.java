package com.innovation.identity.common;

import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 构造单例用于存储获得用户偏好（如用户信息，用户收藏等等）
 * @author Xiaona
 *
 */
public class SharedPreferencesTool {
	private String userPreferencesXmlName="identity_user_preferences";
	private Context context;
	private SharedPreferences sharedPreferences;
	private static SharedPreferencesTool sharedPreferencesTool;
	/**
	 * 私有构造方法，单例模式
	 * @param context
	 */
	private SharedPreferencesTool(Context context){
		this.context=context;
	}
	/**
	 * 通过该方法获得SharedPreferencesTool对象
	 * @param context
	 * @return
	 */
	public static SharedPreferencesTool getSharedPreferencesToolInstance(Context context){
		if(sharedPreferencesTool==null){
			sharedPreferencesTool=new SharedPreferencesTool(context);
		}
		return sharedPreferencesTool;
	}
	public void saveUserPrefences(Map<String, String> params){
		sharedPreferences=context.getSharedPreferences(userPreferencesXmlName, Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			editor.putString(entry.getKey(), entry.getValue());
		}
		editor.commit();
	}
	/**
	 * 读取identity_user_preferences，返回map类型的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getUserPrefences(){
		sharedPreferences=context.getSharedPreferences(userPreferencesXmlName, Context.MODE_PRIVATE);
		return (Map<String,String>)sharedPreferences.getAll();
	}
}
