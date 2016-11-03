package com.zhihuigusu.protect.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * ��������ת�����ַ���
	 * @param is  ������
	 * @return  �ַ���    null�����쳣
	 */
	public static String stream2String(InputStream is) {
		//�����������뻺������һ��ȫ�����������ַ���
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
