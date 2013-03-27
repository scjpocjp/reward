package com.android.reward.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.android.reward.R;
import com.android.reward.application.RewardApplication;
import com.android.reward.fragment.LoginFragment;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Print;


public class MainActivity extends FragmentActivity {

	
	public static MainActivity context; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Constants.isCurrent = true;
		context = this;
		RewardApplication.getInstance().setFragmentManager( getSupportFragmentManager() );
		setContentView(R.layout.main );

		RewardApplication.getFragmentManagerUtil().setFragment( "HeaderFragment", R.id.header );
		RewardApplication.getFragmentManagerUtil().setFragment( "LoginFragment", R.id.content );
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			
			int backStackEntryCount = RewardApplication.getFragmentManager().getBackStackEntryCount();
			Print.getInstance().show ( " MainActivity :: backStackEntryCount ----- "+backStackEntryCount);
			// 2 size tends to HeaderFragment
			if ( backStackEntryCount <= 2 ) {
				finish ();
			}
			
			
		}

		return super.onKeyDown(keyCode, event);
	}

	


}
