/*
 * Copyright 2011 PlayUP. All Rights Reserved.

 */
package com.android.reward.application;

import java.util.UUID;

import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.android.reward.fragmanager.FragmentManagerUtil;
import com.android.reward.lib.app.RewardLibApplication;
import com.android.reward.lib.database.DatabaseWrapper;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.PollManager;
import com.android.reward.lib.util.PreferenceManagerUtil;


public class RewardApplication extends RewardLibApplication {

	private static PollManager pollMangar;
	private static RewardApplication applicationContext;
	private static FragmentManager fragmentManager ;
	private static FragmentManagerUtil fragmentManagerUtil ;

	public static DatabaseWrapper databaseWrapper;
	

	private static Handler handler = new Handler ();

	public RewardApplication (  ) {
		applicationContext  = this;
	}

	public static RewardApplication getInstance () {
		return applicationContext;
	}

	public static void setFragmentManager ( FragmentManager fmanager)  {
		fragmentManager = fmanager;
	}


	public static FragmentManager getFragmentManager () {
		return fragmentManager;
	}

	public static FragmentManagerUtil getFragmentManagerUtil () {
		if ( fragmentManagerUtil == null ) {
			fragmentManagerUtil = new FragmentManagerUtil();
		} 
		return fragmentManagerUtil;
	}

	

	

	public static DatabaseWrapper getDatabaseWrapper () {
		if ( databaseWrapper == null ) {
			databaseWrapper = DatabaseWrapper.getInstance();
		}
		return databaseWrapper;
	}


	public static PollManager getPollManager () {
		if ( pollMangar == null ) {
			pollMangar = new PollManager();
		} 
		return pollMangar;
	}
	
	
	@Override
	public void onCreate() {
		// The following line triggers the initialization of ACRA

		super.onCreate();

		
		Constants.appInstance = this;
		Constants.FRAGMENT_URI = getPackageName() + ".fragment.";
		Constants.DATABASE_PATH = "/data/data/" + getPackageName() + "/databases/" + Constants.DATABASE_NAME;
		if ( Constants.handler == null ) {
			Constants.handler = new Handler (); 
		}
		
		getApplicationId ();
		initialise ();
		
		
		




		// start the service
		// register for connection receivers 
		// copy database 
		// sync database

		// force the load of the end points early on

	}

	
	/**
	 *  Assigning unique id to applicatio as an identifier to server 
	 */
	private void getApplicationId () {
		PreferenceManagerUtil preferences = new PreferenceManagerUtil();
		Keys.APPID = preferences.get( Keys.APPID_KEY,  null );
		if ( Keys.APPID == null ) {
			Keys.APPID = UUID.randomUUID().toString();
			preferences.set( Keys.APPID_KEY, Keys.APPID );
		}
	}


	/**
	 * initialises databaseWrapper, fragment Util and threadpoolExecutor. 
	 */
	private void initialise () {
		
		System.out.println ( " 0000000000000000000000000000000000000000000000000000000000000000 ");
		pollMangar = new PollManager();
		fragmentManagerUtil = new FragmentManagerUtil();


		//String deviceId = Settings.System.getString(getContentResolver(),Settings.System.ANDROID_ID);


		if (Keys.DEVELOPER_MODE) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectDiskReads()
			.detectDiskWrites()
			.detectNetwork()   // or .detectAll() for all detectable problems
			.penaltyLog()
			.build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
			.detectLeakedSqlLiteObjects()
			.detectLeakedSqlLiteObjects()
			.penaltyLog()
			.penaltyDeath()
			.build());
		}else{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
		}
	}

	/**
	 * updating the fragments.  
	 */
	public void callUpdateOnFragments ( Message msg ) {

		if ( fragmentManagerUtil != null ) {
			fragmentManagerUtil.onUpdate( msg );
		} else {
			fragmentManagerUtil = new FragmentManagerUtil();
			fragmentManagerUtil.onUpdate( msg );
		}
	}


	/**
	 * updating the fragments.  
	 */
	public void callUpdateTopBarFragments ( Message msg ) {

		if ( fragmentManagerUtil != null ) {
			fragmentManagerUtil.updateTopBarFragment ( msg );
		} else {
			fragmentManagerUtil = new FragmentManagerUtil();
			fragmentManagerUtil.updateTopBarFragment ( msg );
		}
	}




	/**
	 * updating the fragments except topbar fragmnt .  
	 */
	public void callUpdateOnFragmentsNotTopBar ( Message msg ) {

		if ( fragmentManagerUtil != null ) {
			fragmentManagerUtil.onUpdateNotTopBar( msg );
		} else {
			fragmentManagerUtil = new FragmentManagerUtil();
			fragmentManagerUtil.onUpdateNotTopBar( msg );
		}
	}


	/**
	 * for showing any toast message 
	 */ 
	public static void showToast ( final int resourceId ) {
		
		

		if ( handler != null ) {

			handler.post( new Runnable () {

				@Override
				public void run() {

					try {
						Toast.makeText( RewardApplication.getInstance().getBaseContext(), resourceId, Toast.LENGTH_SHORT ).show();
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						//Logs.show ( e );
					}
				}

			});
		}
	}

	

	
	
//	public static void callRefreshOnFragments( Message msg) {
//		if ( fragmentManagerUtil != null ) {
//			fragmentManagerUtil.onRefresh( msg );
//		} else {
//			fragmentManagerUtil = new FragmentManagerUtil();
//			fragmentManagerUtil.onRefresh( msg );
//		}
//	}

}
