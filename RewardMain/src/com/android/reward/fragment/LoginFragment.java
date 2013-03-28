package com.android.reward.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.reward.R;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.util.Util;
import com.android.reward.lib.validation.ForgotPassword;
import com.android.reward.lib.validation.Login;


public class LoginFragment extends MainFragment implements OnClickListener {


	private Login login = null;
	private ForgotPassword forgotPassword = null;
	
	
	private EditText email = null;
	private EditText password = null;
	private Dialog forgotPassDialog;
	private EditText etRepeatEmail = null;
	
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
		
		case R.id.btnResetPassword : dismissForgotPassPopup();
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

		if(Util.isInternetAvailable())
			login.login ();
		else
			Toast.makeText(LoginFragment.this.getActivity(),getString(R.string.no_network),Toast.LENGTH_SHORT).show();
	}
	
	

	/**
	 * Redirecting to forgot password screen
	 * 
	 *  Functionality will be different for device and tablet
	 */
	private void forgotPassword ( ) {

		// go back to login/registration activity
		Print.getInstance().show ( " forgotPassword -----------------------------");

		forgotPassword = new ForgotPassword();
		View forgotPassView = LayoutInflater.from( getActivity() ).inflate(R.layout.forgot_password_popup, null);

		forgotPassDialog = new Dialog(getActivity());
		forgotPassDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		forgotPassDialog.setContentView(forgotPassView);
		forgotPassDialog.show();

		forgotPassView.findViewById(R.id.btnResetPassword).setOnClickListener(LoginFragment.this);
		etRepeatEmail = ( EditText ) forgotPassView.findViewById(R.id.etRepeatEmail );

	}
	
	/**
	 * Close forgot password pop-up manually
	 */
	public void dismissForgotPassPopup() {
		if ( forgotPassDialog!= null && forgotPassDialog.isShowing() ) { 
			
			Msg msg = forgotPassword.setEmail ( etRepeatEmail.getText().toString() );
			
			
			if ( msg.getiStatusCode() == -1 ) {
				Toast.makeText( LoginFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
				etRepeatEmail.requestFocus();
				return;
			}
			
			forgotPassword.forgotPassword();
			
			forgotPassDialog.dismiss();
		}
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

		if ( msg != null ) {
			Print.getInstance()
			.show( "msg.obj ===================================="+ msg.obj );
			if ( msg.what == -1 || msg.obj != null ) {
				Toast.makeText ( getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT ).show();
			} else {
				// start the product fragment
			}
		}
	}



}
