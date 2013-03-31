package com.android.reward.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsoluteLayout;

import com.android.reward.R;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Print;


public class MainActivity extends FragmentActivity {

	
	public static MainActivity context; 
	
	private boolean isMenuShown = false;
	private View menuView;
	private View mainView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Constants.isCurrent = true;
		context = this;
		RewardApplication.getInstance().setFragmentManager( getSupportFragmentManager() );
		setContentView(R.layout.main );

		menuView = findViewById( R.id.menu );
		mainView = findViewById( R.id.main );
		
		RewardApplication.getFragmentManagerUtil().setFragment( "MenuFragment", R.id.menu );
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
	
	
	public void showHideMenu () {
		if ( isMenuShown ) {
			isMenuShown = false;
			// hide the meu with animation
			final TranslateAnimation anim1 = new TranslateAnimation( TranslateAnimation.ABSOLUTE, 0, TranslateAnimation
					.ABSOLUTE
					, -200, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f); 
			anim1.setDuration( 500 );
			anim1.setAnimationListener( new AnimationListener( ) {
				
				@Override
				public void onAnimationStart(Animation arg0) {
					mainView.postDelayed( new Runnable () {

						@Override
						public void run() {
							AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams( mainView.getLayoutParams() );
							lp.x = 0;
							lp.y = 0;
							mainView.setLayoutParams( lp );
							mainView.clearAnimation();
						}
					}, 250 );
				}
				
				@Override
				public void onAnimationRepeat(Animation arg0) {}
				
				@Override
				public void onAnimationEnd(Animation arg0) {}
				
				
			});
			
			TranslateAnimation anim = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, 0 );
			anim.setDuration( 600 );
			anim.setAnimationListener( new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					 menuView.setVisibility( View.VISIBLE );
					 mainView.startAnimation( anim1 );
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					menuView.setVisibility( View.GONE );
				}
			});
			menuView.startAnimation(anim ); 

		} else {
			isMenuShown = true;
			// show the menu with animation
			
			final TranslateAnimation anim1 = new TranslateAnimation( TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 200, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f); 
			anim1.setDuration( 500 );
			anim1.setAnimationListener( new AnimationListener( ) {
				
				@Override
				public void onAnimationStart(Animation arg0) {
					mainView.postDelayed( new Runnable () {

						@Override
						public void run() {
							AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams( mainView.getLayoutParams() );
							lp.x = 200;
							lp.y = 0;
							mainView.setLayoutParams( lp );
							mainView.clearAnimation();
						}
						
					}, 410 );
				}
				
				@Override
				public void onAnimationRepeat(Animation arg0) {}
				
				@Override
				public void onAnimationEnd(Animation arg0) {}
			});
			
			TranslateAnimation anim = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, 0 );
			anim.setDuration( 500 );
			anim.setAnimationListener( new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					menuView.setVisibility( View.VISIBLE );
					 mainView.startAnimation( anim1 );
					 
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {}
				
				@Override
				public void onAnimationEnd(Animation animation) {}
			});
			menuView.startAnimation(anim ); 
		}
	}

	


}
