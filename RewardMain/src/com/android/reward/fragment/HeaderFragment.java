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

public class HeaderFragment extends MainFragment implements OnClickListener {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private View back;
	private View menu;
	private View foryou;
	private View skip;
	
	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


		View v = inflater.inflate(R.layout.header, container, false);

		back = v.findViewById( R.id.back );
		skip = v.findViewById( R.id.skip );
		menu = v.findViewById( R.id.menu );
		foryou = v.findViewById( R.id.forYou );
		
		back.setOnClickListener( this );
		skip.setOnClickListener( this );
		menu.setOnClickListener( this );
		foryou.setOnClickListener( this );
		

		PreferenceManagerUtil prefUtil = new PreferenceManagerUtil();
		if ( prefUtil.get( Constants.IS_LOGGED_IN, false ) ) {
			
			back.setVisibility( View.VISIBLE );
			skip.setVisibility( View.VISIBLE );
			
			menu.setVisibility(View.GONE );
			foryou.setVisibility(View.GONE );
			
		} else {
			
			back.setVisibility( View.GONE );
			skip.setVisibility( View.GONE );
			
			menu.setVisibility(View.VISIBLE );
			foryou.setVisibility(View.VISIBLE );
			
		}
		

		return v;

	}

	@Override
	public void onClick ( View v ) {

		switch ( v.getId() ) {

		case R.id.back : back();
		break;

		case R.id.skip : skip ();
		break;
		
		case R.id.menu : menu ();
		break;
		
		case R.id.forYou : foryou ();
		break;
		}
	}
	
	/**
	 * Sliding the menu 
	 * */
	private void menu () {
		Print.getInstance().show ( " menu -------------------------------");
		( ( MainActivity ) getActivity() ).showHideMenu();
	
	}
	
	
	/**
	 * for you functionality  
	 */
	private void foryou () {
		
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
		( ( MainActivity ) getActivity() ).showHideMenu();
	}

	
	/**
	 * Handling the functions which need to perform after thread has completed running. 
	 */
	@Override
	public void onUpdate( Message msg ) {
		System.out.println ( "Header fragment update eeee ========================================"+ msg );
	}


}
