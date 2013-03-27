package com.android.reward.fragment;


import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.reward.R;
import com.android.reward.application.RewardApplication;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.util.Util;
import com.android.reward.lib.validation.Registration;

public class RegisterFragment extends MainFragment implements OnClickListener {

	
	private Registration register = null;
	private EditText firstName = null;
	private EditText lastName = null;
	private EditText email = null;
	private EditText phone = null;
	private EditText password = null;
	private EditText confirm_password = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
    	View v = inflater.inflate(R.layout.register, container, false);
        
    	v.findViewById( R.id.already_member ).setOnClickListener( this );
    	v.findViewById( R.id.register ).setOnClickListener( this );
    	
    	firstName = ( EditText ) (v.findViewById( R.id.firstName ));
    	lastName = ( EditText ) (v.findViewById( R.id.lastName ));
    	email = ( EditText ) (v.findViewById( R.id.email ));
    	phone = ( EditText ) (v.findViewById( R.id.phone ));
    	password = ( EditText ) (v.findViewById( R.id.password ));
    	confirm_password = ( EditText ) (v.findViewById( R.id.confirm_password ));
    	
    	register = new Registration();
    	return v;
    
    }

	@Override
	public void onClick ( View v ) {
		
		switch ( v.getId() ) {
		
		case R.id.already_member : alreadyMember();
		 				 break;
		 				 
		case R.id.register : register ();
						 break;
		}
	}
	
	
	
	private void register () {
		
		
		Msg msg = register.setvFirstName( firstName.getText().toString() );
		
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			firstName.requestFocus();
			return;
		}
		
		msg = register.setvLastName(  lastName.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			lastName.requestFocus();
			return;
		}
		
		msg = register.setEmail( email.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			email.requestFocus();
			return;
		}
		
		msg = register.setvPhoneNumber( phone.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			phone.requestFocus();
			return;
		}
		
		msg = register.setvPassword( password.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			password.requestFocus();
			return;
		}
		
		msg = register.setvConfirmPassword( confirm_password.getText().toString() );
		if ( msg.getiStatusCode() == -1 ) {
			Toast.makeText( RegisterFragment.this.getActivity(), msg.getvMsg(), Toast.LENGTH_SHORT ).show();
			confirm_password.requestFocus();
			return;
		}
		
		if(Util.isInternetAvailable())
			register.register( );
		else
			Toast.makeText(RegisterFragment.this.getActivity(), getString(R.string.no_network), Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Redirecting to login screen
	 * 
	 *  Functionality will be different for device and tablet
	 */
	private void alreadyMember ( ) {
		
		// go back to login/registration activity
		Print.getInstance().show ( " alreadyMember -----------------------------");
		if ( RewardApplication.getFragmentManagerUtil().checkIfFragmentExists( "LoginFragment" ) ) {
			RewardApplication.getFragmentManagerUtil().popBackStackImmediate( );
		}
 	}

	/**
	 * Handling the functions which need to perform after thread has completed running. 
	 */
	@Override
	public void onUpdate( Message msg ) {
		System.out.println ( "Message ========================================"+ msg );
		
		
		if ( msg.what == 200 ) {
			// start the product fragment
		}
	}

}
