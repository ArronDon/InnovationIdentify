package com.innovation.identity.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * �ж�NET�������
 * 
 * @author xiaona
 * 
 */
public class NetTools {
	/**
	 * �ж�Android�ͻ��������Ƿ����� �Ƿ��п��õ����ӣ��������ж��Ƿ�������
	 * 
	 * @param context
	 * @return true false
	 */
	public static boolean checkNet(Context context) {

		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {

					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * ������������ ��toast��ʾ
	 * 
	 * @return true/false 
	 */
	public boolean note_Intent(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = con.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			// ��ǰ���粻����
			Toast.makeText(context.getApplicationContext(), "��������Internet��",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		if (!wifi) { // ��ʾʹ��wifi
			Toast.makeText(context.getApplicationContext(), "������ʹ��WIFI�Լ���������",
					Toast.LENGTH_SHORT).show();
		}
		return true;

	}

	/**
	 * �ж����������Ƿ����
	 * 
	 * @return true/false ���ӳɹ�/ʧ��
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else { // ��������������ж���������//�����ʹ��
					// cm.getActiveNetworkInfo().isAvailable();
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * �ж�GPS�Ƿ��
	 * 
	 * @return GPS : true/false �Ƿ���Ч
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = lm.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * �ж�WIFI�Ƿ��
	 * 
	 * @return GPS : true/false �Ƿ���Ч
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
				.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * �ж��Ƿ���3G����
	 * 
	 * @return GPS : true/false �Ƿ���Ч
	 */
	public static boolean is3rd(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * �ж����Ƿ�ΪWIFI,�û����������������ˣ�wifi�Ϳ��Խ������ػ������߲���
	 * 
	 * @return true/false �Ƿ���Ч
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}
	 /** 
     * ����ֻ����Ƿ�װ��ָ������� 
     * @param context 
     * @param packageName��Ӧ�ð��� 
     * @return 
     */  
    public static boolean isAvilible(Context context, String packageName){   
        //��ȡpackagemanager   
        final PackageManager packageManager = context.getPackageManager();  
      //��ȡ�����Ѱ�װ����İ���Ϣ   
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);  
      //���ڴ洢�����Ѱ�װ����İ���   
        List<String> packageNames = new ArrayList<String>();  
        //��pinfo�н���������һȡ����ѹ��pName list��   
        if(packageInfos != null){   
            for(int i = 0; i < packageInfos.size(); i++){   
                String packName = packageInfos.get(i).packageName;   
                packageNames.add(packName);   
            }   
        }   
      //�ж�packageNames���Ƿ���Ŀ�����İ�������TRUE��û��FALSE   
        return packageNames.contains(packageName);  
  }   
}
