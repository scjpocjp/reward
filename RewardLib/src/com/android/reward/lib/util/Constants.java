package com.android.reward.lib.util;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;

import com.android.reward.lib.app.RewardLibApplication;

public class Constants {

	
	// application related params 
	public static String LANGUAGE = "en";
	public static String COUNTRY = "US";
	
	public static boolean isCurrent = false;
	
	public static final String KEY = "key";
	public static final String APPID = "appId";
	
	public static Handler handler = null;
	public static Context context = null;
	public static IBinder mBinder = null;
	public static RewardLibApplication appInstance = null;
	
	public static final String IS_LOGGED_IN = "isLoggedIn";
	
	public static String vUserId = null;
	public static String vFirstName = null;
	public static String vLastName = null;
	public static String vPhone = null;
	public static String vPoints = null;
	
	
	
	
	// fragment related params
	// fragment URI 
	public static String 	FRAGMENT_URI 			= null;
	
	
	// database related params 
	public static final String DATABASE_NAME = "Reward360";
	public static final int DATABASE_VERSION = 1;
	public static String DATABASE_PATH = null;

	public static final int QUERY_EMPTY = 0;
	public static final int QUERY_INSERT = QUERY_EMPTY + 1;
	public static final int QUERY_UPDATE = QUERY_INSERT + 1;
	public static final int QUERY_DELETE = QUERY_UPDATE + 1;
	public static final int QUERY_RAW = QUERY_DELETE + 1;
	
	// request related params
	public static final int TYPE_BYTE = 0;
	public static final int TYPE_STREAM = TYPE_BYTE + 1;
	public static final int TYPE_STRINGBUFFER = TYPE_STREAM + 1;
	
	public static final int POST_METHOD = 0;
	public static final int IMAGE_METHOD = POST_METHOD + 1;
	public static final int GET_METHOD = IMAGE_METHOD + 1;
	public static final int DELETE_METHOD = GET_METHOD + 1;
	public static final int HEAD_METHOD = DELETE_METHOD + 1;
	public static final int OPTIONS_METHOD = HEAD_METHOD + 1;
	public static final int PUT_METHOD = OPTIONS_METHOD + 1;
	public static final int TRACE_METHOD = PUT_METHOD + 1;
	
	
	
	
	
	
}
