package com.etoc.weflow.utils;

import java.util.UUID;

import android.os.Environment;


public class ConStant {
	public final static String SD_STORAGE_ROOT = "/.weflow"; //不包括sdcard的路径
	public static final long MEDIA_CACHE_LIMIT = 100;
	public static final int LOAIND_DISSMISS_DALAY = 10000;
	
	public static final String INTENT_SOFT_DETAIL = "intent_sort_detail";
	public static final String INTENT_MAKE_FLOW = "intent_make_flow";
	public static final String INTENT_EXPENSE_FLOW = "intent_expense_flow";
	
	public static String getApkCachePath(){
		return SD_STORAGE_ROOT + "/" + "apk";
	}
	
	public static String getAbsolutePath() {
		return Environment.getExternalStorageDirectory() + SD_STORAGE_ROOT;
	}
	
	public static String getImageLoaderCachePath() {
		return SD_STORAGE_ROOT + "/" + "imageloader/cache";
	}
	
	public static String getPicAbsoluteDirPath() {
		return getAbsolutePath() + "/pic/";
	}
	
	public static String getDownloadCachePath() {
		return SD_STORAGE_ROOT + "/" + "downloads";
	}
	
	/**
	 * 获取一个新的UUID;
	 * @return
	 */
	public static String getNextUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
