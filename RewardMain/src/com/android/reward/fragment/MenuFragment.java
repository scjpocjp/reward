package com.android.reward.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.android.reward.R;
import com.android.reward.activities.MainActivity;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.PreferenceManagerUtil;
import com.android.reward.lib.util.Print;

public class MenuFragment extends MainFragment implements OnClickListener {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


		View v = inflater.inflate(R.layout.menu, container, false);

		
		

				return v;

	}

	@Override
	public void onClick ( View v ) {

	
	}
	

	
	/**
	 * Handling the functions which need to perform after thread has completed running. 
	 */
	@Override
	public void onUpdate( Message msg ) {
		System.out.println ( "menu fragment update eeee ========================================"+ msg );
	}


}
