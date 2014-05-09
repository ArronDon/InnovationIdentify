package com.innovation.identity.common;

import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * ���쵥�����ڴ洢����û�ƫ�ã����û���Ϣ���û��ղصȵȣ�
 * @author Xiaona
 *
 */
public class SharedPreferencesTool {
	private String userPreferencesXmlName="identity_user_preferences";
	private Context context;
	private SharedPreferences sharedPreferences;
	private static SharedPreferencesTool sharedPreferencesTool;
	/**
	 * ˽�й��췽��������ģʽ
	 * @param context
	 */
	private SharedPreferencesTool(Context context){
		this.context=context;
	}
	/**
	 * ͨ���÷������SharedPreferencesTool����
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
	 * ��ȡidentity_user_preferences������map���͵�����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getUserPrefences(){
		sharedPreferences=context.getSharedPreferences(userPreferencesXmlName, Context.MODE_PRIVATE);
		return (Map<String,String>)sharedPreferences.getAll();
	}
}
