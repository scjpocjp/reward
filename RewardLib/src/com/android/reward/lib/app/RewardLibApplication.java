/*
 * Copyright 2011 PlayUP. All Rights Reserved.

 */
package com.android.reward.lib.app;

import android.app.Application;
import android.os.Message;


public abstract class RewardLibApplication extends Application {


	public RewardLibApplication (  ) {
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
