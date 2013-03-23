package com.android.reward.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.android.reward.R;
import com.android.reward.activities.MainActivity;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Print;

public class HeaderFragment extends MainFragment implements OnClickListener {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.header, container, false);

		v.findViewById( R.id.back ).setOnClickListener( this );
		v.findViewById( R.id.skip ).setOnClickListener( this );

		return v;

	}

	@Override
	public void onClick ( View v ) {

		switch ( v.getId() ) {

		case R.id.back : back();
		break;

		case R.id.skip : skip ();
		break;
		}
	}

	/**
	 * Handling back button
	 * 
	 *  Functionality will be different for device and tablet
	 */
	private void back ( ) {

		// go back to login/registration activity

		int backStackEntryCount = RewardApplication.getFragmentManager().getBackStackEntryCount();

		// 2 size tends to HeaderFragment
		if ( backStackEntryCount <= 2 ) {
		(	( Activity ) MainActivity.context).finish ();
		} else {
			RewardApplication.getFragmentManagerUtil().popBackStackImmediate();
		}
	}

	/**
	 * Skipping to different screens
	 * Device  and tablet functionality will be different 
	 */ 
	private void skip () {

		// skip to home activity where product related information is there
		Print.getInstance().show ( " Skip -------------------------------");
	}



}
