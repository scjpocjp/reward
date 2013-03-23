package com.android.reward.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.reward.R;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.validation.Login;

public class LoginFragment extends MainFragment implements OnClickListener {


	private Login login = null;
	private EditText email = null;
	private EditText password = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.login, container, false);

		v.findViewById( R.id.forgot_password ).setOnClickListener( this );
		v.findViewById( R.id.not_member ).setOnClickListener( this );
		v.findViewById( R.id.login ).setOnClickListener( this );

		email = ( EditText ) (v.findViewById( R.id.email ));
		password = ( EditText ) (v.findViewById( R.id.password ));

		login = new Login ();
		return v;

	}

	@Override
	public void onClick ( View v ) {

		switch ( v.getId() ) {

		case R.id.forgot_password : forgotPassword();
		break;

		case R.id.not_member : notMember ();
		break;

		case R.id.login : login ();
		break;
		}
	}

	private void login () {

		Msg msg = login.setEmail( email.getText().toString() );

		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( LoginFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			email.requestFocus();
			return;
		}

		msg = login.setPassword( password.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( LoginFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			password.requestFocus();
			return;
		}

		login.login ();
	}

	/**
	 * Redirecting to forgot password screen
	 * 
	 *  Functionality will be different for device and tablet
	 */
	private void forgotPassword ( ) {

		// go back to login/registration activity
		Print.getInstance().show ( " forgotPassword -----------------------------");

		View forgotPassView = LayoutInflater.from( getActivity() ).inflate(R.layout.forgot_password_popup, null);

		final PopupWindow forgotPassPopup = new PopupWindow(forgotPassView, LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, true);
		forgotPassPopup.setAnimationStyle(android.R.style.Animation_Dialog);
		forgotPassPopup.showAtLocation(forgotPassView, Gravity.CENTER, 0, 0); 
		forgotPassView.findViewById(R.id.btnResetPassword).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				forgotPassPopup.dismiss();
			}
		});

	}

	/**
	 * Redirecting to registration page
	 * Device  and tablet functionality will be different 
	 */ 
	private void notMember () {

		// skip to home activity where product related information is there
		Print.getInstance().show ( " notMember -------------------------------");
		RewardApplication.getFragmentManagerUtil().setFragment( "RegisterFragment", R.id.content );

	}


	/**
	 * Handling the functions which need to perform after thread has completed running. 
	 */
	@Override
	public void onUpdate( Message msg ) {
		System.out.println ( "Message ========================================"+ msg );
	}



}
