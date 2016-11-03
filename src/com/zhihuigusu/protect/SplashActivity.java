package com.zhihuigusu.protect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpConnection;

import com.zhihuigusu.protect.utils.StreamUtil;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends Activity {

	private TextView tv_version_name;
	private String mLocalVersionName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����activityȥͷ
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		//��ʼ��ui
		initUI();
		
		//��ʼ������
		 initData();
		
		
		
	}

	
	/**
	 * ���汾��
	 * @return
	 */
	private String checkVersion() {
		//�������̣߳�����ͨ��Ҫ�����̲߳���
		new Thread(){
			public void run() {
				try {
					//��װurl
					URL url=new URL("http://192.168.1.100/update.json");
					//����url����
					HttpURLConnection con= (HttpURLConnection) url.openConnection();
					
					//��������
					//�������ӳ�ʱ
					con.setConnectTimeout(2000);
					//���������ж�
					con.setReadTimeout(2000);
					//��ȡӦ����
					int responseCode = con.getResponseCode();
					InputStream is = con.getInputStream();
					if(responseCode==200){
						ByteArrayOutputStream bos=new ByteArrayOutputStream();
						String json=StreamUtil.stream2String(is);						
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		}.start();
		return null;
	}

	/**
	 * ��ʼ������     
	 * 
	 */
	private void initData() {
		//��ȡ�汾����
		tv_version_name.setText("�汾����"+getVersionName());
		//��ȡ�������汾��Ϣ,�뱾�ذ汾�Ž��жԱȣ�������ڱ��ؾ����ظ���
		//��ȡ���ذ汾��
		getVersionCode();
		//��ȡ�������汾��
		checkVersion();
	}


	/**
	 * ��ȡ���ذ汾��
	 * @return	����0��ʾ�쳣
	 */
	private int getVersionCode() {
		try {
			//flag ��Ϊ�˴�����ȡ������Ϣ  �������߶���
			PackageManager pm = getPackageManager();
			//�Ӱ������߶����ȡ�汾��Ϣ
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			//��ȡ�汾����
			int versionName = packageInfo.versionCode;
			
			
			return versionName;
		} catch (NameNotFoundException e) {
			return 0;
		}
		
	}


	/**
	 * ���嵥�ļ��л�ȡ�汾����
	 * @return  �汾����   ����null�����쳣
	 */
	private String getVersionName() {
		try {
			//flag ��Ϊ�˴�����ȡ������Ϣ  �������߶���
			PackageManager pm = getPackageManager();
			//�Ӱ������߶����ȡ�汾��Ϣ
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			//��ȡ�汾����
			String versionName = packageInfo.versionName;
			
			
			return versionName;
		} catch (NameNotFoundException e) {
			return null;
		}
	}

	/**
	 * ��ʼ��ui
	 */
	private void initUI() {
		
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
		
	}

	
}
