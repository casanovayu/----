package com.zhihuigusu.protect.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * 将输入流转换成字符串
	 * @param is  输入流
	 * @return  字符串    null代表异常
	 */
	public static String stream2String(InputStream is) {
		//将输入流存入缓冲区，一次全部读出存入字符串
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int a=-1;
		try {
			while((a=is.read(buffer))!=-1){
				bos.write(buffer, 0, a);
			}
			return bos.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				is.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
