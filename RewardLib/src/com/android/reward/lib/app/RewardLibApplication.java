/*
 * Copyright 2011 PlayUP. All Rights Reserved.

 */
package com.android.reward.lib.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Application;
import android.os.Message;


public abstract class RewardLibApplication extends Application {

	private static ThreadPoolExecutor threadPoolExecutor;

	public RewardLibApplication (  ) {
	}
	@Override
	public void onCreate() {
	
		super.onCreate();
		threadPoolExecutor = new ThreadPoolExecutor ( 5, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>( 100  ), new RewardRejectedExecutionHandler ( ) );
	}



	class RewardRejectedExecutionHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

		}

	}
	public static ThreadPoolExecutor getThreadPoolExecutor () {
		return threadPoolExecutor;
	}
	/**
	 * updating the fragments.  
	 */
	public abstract void callUpdateOnFragments ( Message msg ) ;
	


	/**
	 * updating the fragments.  
	 */
	public abstract void callUpdateTopBarFragments ( Message msg ) ;



	/**
	 * updating the fragments except topbar fragmnt .  
	 */
	public abstract void callUpdateOnFragmentsNotTopBar ( Message msg );


}
