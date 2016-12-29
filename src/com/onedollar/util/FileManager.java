package com.onedollar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileManager {
	public static final String TAG = "FileManager";

	private static final String FILE_PATH = "/Android/data/com.shunshunliuxue/files/imgs/";

	static {
		creatFileInSDCard(FILE_PATH);
	}

	// private static Context mContext = App.getInstance();

	public static File creatFileInSDCard(String path) {
		if (!Util.isExistSDCard()) {
			return null;
		}
		return creatFile(path);
	}

	public static File creatFile(String path) {
		if (path == null)
			return null;

		File file = new File(path);
		if (!file.exists()) {

			buildFilePath(path);
			file = new File(path);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return file;

	}

	private static void buildFilePath(String path) {
		if (path == null) {
			return;
		}
		// Android/data/packageName/files/log/xxx.txt
		String[] dirPaths = path.split("\\/");
		String currentPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		if (dirPaths != null && dirPaths.length > 0) {
			for (int i = 0; i < dirPaths.length - 1; i++) {
				if (dirPaths[i] != null && dirPaths[i].length() > 0) {
					currentPath = currentPath + "//" + dirPaths[i];
					File file = new File(currentPath);
					if (file.exists() && file.isDirectory()) {

					} else {
						file.mkdir();
					}
				}
			}
		}
	}

	/** 保存方法 */
	public static void saveBitmap(String fileName, Bitmap bm) {
		Log.i(TAG, "保存图片");
		if (fileName != null) {
			Log.e(TAG, "fileName :" + fileName);
			fileName = fileName.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
			fileName = fileName.replaceAll("jpg$", ".jpg");
			fileName = fileName.replaceAll("png$", ".png");
			fileName = fileName.replaceAll("bmp$", ".bmp");
			Log.e(TAG, "fileName :" + fileName);
		}
		File f = creatFile(FILE_PATH + fileName);

		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			Log.i(TAG, "已经保存:" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getBitmap(String fileName) {
		try {
			File f = new File(FILE_PATH, fileName);
			if (f.exists()) {
				try {
					FileInputStream in = new FileInputStream(f);
					return BitmapFactory.decodeStream(in);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}

		return null;
	}
}
