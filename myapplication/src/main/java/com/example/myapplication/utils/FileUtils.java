package com.delta.ams.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileUtils {

	// 读取sdk根目录的properties
	public static String readValue(String fileName, String key) {
		String pathurl = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/amsUrl/" + fileName;
		Properties props = new Properties();
		try {
			// System.getProperties().getProperty(name)
			InputStream in = new BufferedInputStream(new FileInputStream(
					pathurl));
			props.load(in);
			String value = props.getProperty(key);
			System.out.println(key + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isExisted(String fileName) {

		String pathurl = Environment.getExternalStorageDirectory().getPath()
				+ "/amsUrl/" + fileName;
		File file = new File(pathurl);
		if (file.exists()) {
			return true;
		} else {

			return false;
		}
	}

	// 写入properties
	public static void writeProperties(String fileName, Context context,
			String parameterName, String parameterValue) {
		Properties prop = new Properties();

		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String pathurl = Environment.getExternalStorageDirectory()
						.getPath() + "/amsUrl/" + fileName;
				File file = new File(pathurl);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
					Log.e("ssss", "ytrjytjh" + pathurl);
				}
				if (!file.exists()) {
					file.createNewFile();
				}
				InputStream fis = new FileInputStream(pathurl);
				// 从输入流中读取属性列表（键和元素对）
				prop.load(fis);
				// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
				// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
				OutputStream fos = new FileOutputStream(pathurl);
				prop.setProperty(parameterName, parameterValue);
				// 以适合使用 load 方法加载到 Properties 表中的格式，
				// 将此 Properties 表中的属性列表（键和元素对）写入输出流
				prop.store(fos, "Update '" + parameterName + "' value");
			}

		} catch (IOException e) {

		}
	}
}
