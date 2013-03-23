package com.android.reward.lib.database;


import java.io.FileOutputStream;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.PreferenceManagerUtil;
import com.android.reward.lib.util.Util;


public class DatabaseControl extends SQLiteOpenHelper {

	//private  final boolean DATABASE_EXISTS 		= true;
	public static final boolean  DATABASE_EXISTS 		= true;
	private final boolean DATABASE_NOT_EXISTS 	= false;

	public DatabaseControl ( ) {

		super( Constants.appInstance, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION );

		PreferenceManagerUtil preferenceManagerUtil = new PreferenceManagerUtil();
		// check if the database already exists in application or not.


		if ( !preferenceManagerUtil.get(  Keys.IS_DATABASE_EXISTS , DATABASE_NOT_EXISTS ) )  {

			// create the database in the application
			SQLiteDatabase db = Constants.appInstance.openOrCreateDatabase ( Constants.DATABASE_NAME , 0 , null); 
			
			db.setLockingEnabled(false);
			Constants.DATABASE_PATH  = db.getPath();
			db.close();
			db = null;

			// copy the structure of database from asset into the database that just got created in the application.
			copyDatabase();

		}
		
		preferenceManagerUtil = null;
	}

	/**
	 * Copies the database from the asset folder in the database of application. 
	 */
	private void copyDatabase  () {
		CopyFromAsset();
		// PlayupLiveApplication.getThreadPoolExecutor().execute( copyDatabaseRunnable );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// left for later purpose if needed.
	}



	/**
	 * Runnable for copying the database in the application . 
	 */
	private Runnable copyDatabaseRunnable = new Runnable() {
		@Override
		public void run() {
			/*try {
				Thread.sleep( 4000 );
			} catch (InterruptedException e) {
				Logs.show ( e );
			}*/
			CopyFromAsset();
		}
	};


	private void CopyFromAsset () {


		try {
			
			// copies the stream
			Util.copyStreams( Constants.appInstance.getAssets().open(  Constants.DATABASE_NAME + ".mp3" ) , new FileOutputStream( Constants.DATABASE_PATH ) );

			// set the values in shared preferences.
			PreferenceManagerUtil preferenceManagerUtil = new PreferenceManagerUtil();
			preferenceManagerUtil.set (  Keys.IS_DATABASE_EXISTS, DATABASE_EXISTS);
			preferenceManagerUtil = null;
			
			
			

		} catch (Exception e)  {
			//Logs.show( e ); 
		} catch ( Error e ) {
			//Logs.show ( e );
		}


	}
}
