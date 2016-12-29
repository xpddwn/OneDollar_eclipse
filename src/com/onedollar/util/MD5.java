package com.onedollar.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

/*
 * MD5 算法
 */
public class MD5 {

	private static final String BID = "999999";
	private static final String KEY = "c3610194220d99c191706f275f70af32";

	// 全局数组
	private static final String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String getMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest()
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static void sign(ArrayList<BasicNameValuePair> paramList,
			String param) {
		if (paramList != null) {
			paramList.add(new BasicNameValuePair("bid", BID));
			paramList.add(new BasicNameValuePair("sign", TextUtils
					.isEmpty(param) ? getMD5Code("bid=" + BID + "&key=" + KEY)
					: getMD5Code(param + "&bid=" + BID + "&key=" + KEY)));
		}
	}
}