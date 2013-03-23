package com.android.reward.fragment;

import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.android.reward.R;
import com.android.reward.activities.MainActivity;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.fragment.FragmentInterface;

public class MainFragment extends Fragment implements  FragmentInterface {


	public Hashtable<String,  Runnable > runnableList = new Hashtable <String, Runnable > ( );

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	public void onAttach (Activity activity) {
		super.onAttach( MainActivity.context );
	}

	/**
	 * to update the top bar fragment . 
	 */
	public void onDestroy () {
		super.onDestroy();
		RewardApplication.getInstance().callUpdateOnFragments( null ); 
	}

	@Override
	public void onAgainActivated(Bundle args) {


	}

	@Override
	public void onUpdate( Message msg ) {

	}

	
/**
 * For HMAC Purpose call the root API to get the Credentials
 */
	@Override
	public void onConnectionChanged(boolean isConnectionActive) {
		/**
		 * Called Automatically when device internet connection is on/off
		 */
		if ( isConnectionActive ) {
			// perform tasks  that need to be implemented when connection is live 
		} else {
			
			// show no network available message
			RewardApplication.showToast ( R.string.no_network );
		}




	}


	public void cancelRunnable () {
	}


	@Override
	public void onResume(){
		super.onResume();
		
		
	}

	@Override
	public void onPause() {
		super.onPause();

		cancelRunnable();
	}
}
