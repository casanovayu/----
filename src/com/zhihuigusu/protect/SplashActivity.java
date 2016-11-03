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
		//单个activity去头
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		//初始化ui
		initUI();
		
		//初始化数据
		 initData();
		
		
		
	}

	
	/**
	 * 检测版本号
	 * @return
	 */
	private String checkVersion() {
		//开启子线程，网络通信要在子线程操作
		new Thread(){
			public void run() {
				try {
					//封装url
					URL url=new URL("http://192.168.1.100/update.json");
					//开启url连接
					HttpURLConnection con= (HttpURLConnection) url.openConnection();
					
					//设置请求
					//设置连接超时
					con.setConnectTimeout(2000);
					//设置连接中断
					con.setReadTimeout(2000);
					//获取应答码
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
	 * 初始化数据     
	 * 
	 */
	private void initData() {
		//获取版本名称
		tv_version_name.setText("版本名称"+getVersionName());
		//获取服务器版本信息,与本地版本号进行对比，如果大于本地酒下载更新
		//获取本地版本号
		getVersionCode();
		//获取服务器版本号
		checkVersion();
	}


	/**
	 * 获取本地版本号
	 * @return	返回0表示异常
	 */
	private int getVersionCode() {
		try {
			//flag 设为了代步获取基本信息  包管理者对象
			PackageManager pm = getPackageManager();
			//从包管理者对象获取版本信息
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			//获取版本名称
			int versionName = packageInfo.versionCode;
			
			
			return versionName;
		} catch (NameNotFoundException e) {
			return 0;
		}
		
	}


	/**
	 * 在清单文件中获取版本名称
	 * @return  版本名称   返回null代表异常
	 */
	private String getVersionName() {
		try {
			//flag 设为了代步获取基本信息  包管理者对象
			PackageManager pm = getPackageManager();
			//从包管理者对象获取版本信息
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			//获取版本名称
			String versionName = packageInfo.versionName;
			
			
			return versionName;
		} catch (NameNotFoundException e) {
			return null;
		}
	}

	/**
	 * 初始化ui
	 */
	private void initUI() {
		
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
		
	}

	
}
