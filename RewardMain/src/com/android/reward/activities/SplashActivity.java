package com.android.reward.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.reward.R;

public class SplashActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate (  Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.splash );
		
		findViewById( R.id.splash ).setOnClickListener( this );
		
	}

	

	@Override
	public void onClick ( View view ) {
		Intent i = new Intent ( SplashActivity.this, MainActivity.class );
		startActivity( i );
		finish();
		
	}

}
