package com.android.reward.lib.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import android.os.Message;

import com.android.reward.lib.app.RewardLibApplication;
import com.android.reward.lib.exception.RequestRepeatException;
import com.android.reward.lib.request.HttpRequest;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.util.Util;


public class Registration  {

	
	private String vFirstName = null;
	private String vLastName = null;
	private String vEmail = null;
	private String vPhoneNumber = null;
	private String vPassword  = null;
	private String vConfirmPassword = null;

	private String vIdentity1 = null;
	private String vIdentity2 = null;
	private String vIdentity3 = null;
	private boolean isSubscribed = false;
	
	
    
	public Registration ( ) {
	}
	
	
	
	public String getEmail() {
		return vEmail;
	}


	public String getvIdentity1() {
		return vIdentity1;
	}



	public void setvIdentity1(String vIdentity1) {
		this.vIdentity1 = vIdentity1;
	}



	public String getvIdentity2() {
		return vIdentity2;
	}



	public void setvIdentity2(String vIdentity2) {
		this.vIdentity2 = vIdentity2;
	}



	public String getvIdentity3() {
		return vIdentity3;
	}



	public void setvIdentity3(String vIdentity3) {
		this.vIdentity3 = vIdentity3;
	}



	public boolean isSubscribed() {
		return isSubscribed;
	}



	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}



	public String getvFirstName() {
		return vFirstName;
	}



	public Msg setvFirstName(String vFirstName) {
		this.vFirstName = vFirstName;
		
		Msg msg  = new Msg ();
		if ( vFirstName ==  null ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " First name cannot be null " );
		} else if ( vFirstName  != null && vFirstName.trim().length() == 0 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " First name cannot be blank " );
		} else {
			msg.setiStatusCode( 1 );
			msg.setvMsg( "First name is appropriate " );
		}
		return msg;
		
	}



	public String getvLastName() {
		return vLastName;
	}



	public Msg setvLastName(String vLastName) {
		this.vLastName = vLastName;
		
		Msg msg  = new Msg ();
		if ( vLastName ==  null ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Last name cannot be null " );
		} else if ( vLastName  != null && vLastName.trim().length() == 0 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Last name cannot be blank " );
		} else {
			msg.setiStatusCode( 1 );
			msg.setvMsg( "Last name is appropriate " );
		}
		return msg;
		
	}



	public String getvPhoneNumber() {
		return vPhoneNumber;
	}



	public Msg setvPhoneNumber(String vPhoneNumber) {
		this.vPhoneNumber = vPhoneNumber;
		
		Msg msg = new Msg ();
		
		if ( vPhoneNumber == null ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Phone number cannot be null " );
		} else if ( vPhoneNumber != null && vPhoneNumber.trim().length() == 0 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Phone number cannot be blank " );
		} else if ( !vPhoneNumber.matches( "[0-9]+" ) ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Phone number should have only numeric values " );
		} else {
			msg.setiStatusCode( 1 );
			msg.setvMsg( "Appropriate" );
		}
		return msg;
	}



	public String getvPassword() {
		return vPassword;
	}



	public Msg setvPassword(String vPassword) {
		this.vPassword = vPassword;
		
		Msg msg  = new Msg ();
		if ( vPassword ==  null ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Password cannot be null " );
		} else if ( vPassword  != null && vPassword.trim().length() == 0 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Password cannot be blank " );
		} else if ( vPassword.trim().length() < 6 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Min 6 character password" );
		} else {
			msg.setiStatusCode( 1 );
			msg.setvMsg( "Appropriate " );
		}
		return msg;
		
	}



	public String getvConfirmPassword() {
		return vConfirmPassword;
	}



	public Msg setvConfirmPassword(String vConfirmPassword) {
		this.vConfirmPassword = vConfirmPassword;
		
		Msg msg  = new Msg ();
		if ( vConfirmPassword ==  null ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Confirm password cannot be null " );
		} else if ( vConfirmPassword  != null && vConfirmPassword.trim().length() == 0 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( " Confirm password cannot be blank " );
		} else if ( vConfirmPassword.trim().length() < 6 ) {
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Min 6 character password" );
		}  else if ( !vConfirmPassword.equalsIgnoreCase( vPassword ) ) { 
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Passwords do not match " );
		} else {
			msg.setiStatusCode( 1 );
			msg.setvMsg( "Appropriate " );
		}
		return msg;
	}



	public Msg setEmail ( String vEmail ) {
		this.vEmail = vEmail;
		
		Msg msg = new Msg ();
		if ( vEmail == null ) {
		
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Email cannot be null" );
		
		} else if ( vEmail != null && vEmail.trim().length() == 0 ) {
		
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Email cannot be blank" );
		
		} else if ( !isValidEmail () ) {
		
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Please enter valid email" );
			
		} else {
			
			msg.setiStatusCode( 1 );
			msg.setvMsg(  "Email is appropriate " );
				
		} 
		return msg;
	}

	// check if  email is valid or not 
	public boolean isValidEmail ( ) {
		return new Util ().isValidEmail ( vEmail );
	}
	
	// check with the server and send status code accordingly
	public void register ( ) {
		
		// generate headers
		final List < NameValuePair > headerNameValuePair = new ArrayList < NameValuePair > ();
		headerNameValuePair.add(  new NameValuePair( "appId", "12121" ) );
		headerNameValuePair.add(  new NameValuePair( "key", "U2FsdGVkX19K9JCDn7vR1F" ) );
		
		// generate params 
		final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
		paramNameValuePair.add(  new NameValuePair( "first_name", vFirstName ) );
		paramNameValuePair.add(  new NameValuePair( "last_name", vLastName ) );
		paramNameValuePair.add(  new NameValuePair( "phone", vPhoneNumber ) );
		paramNameValuePair.add(  new NameValuePair( "emailId", vEmail ) );
		paramNameValuePair.add(  new NameValuePair( "password", vPassword ) );
		paramNameValuePair.add(  new NameValuePair( "identity1", vIdentity1 ) );
		paramNameValuePair.add(  new NameValuePair( "identity2", vIdentity2 ) );
		paramNameValuePair.add(  new NameValuePair( "identity3", vIdentity3 ) );
		paramNameValuePair.add(  new NameValuePair( "subscribe", (isSubscribed)? "1" : "0" ) );
		
		Runnable r = new Runnable () {

			@Override
			public void run() {
				HttpRequest request = new HttpRequest( Keys.REGISTRATION_URL, headerNameValuePair, Constants.POST_METHOD, paramNameValuePair );
				try {
					final Object result = request.send();
					Constants.handler.post( new Runnable () {

						@Override
						public void run() {
							Message msg = new Message ();
							msg.what = 1;
							msg.obj = result;
							Constants.appInstance.callUpdateOnFragments( msg );
						}
						
					});
				} catch (RequestRepeatException e) {
					Print.getInstance().show(e);
				}
				
			}
			
		};
		
		Thread th = new Thread ( r );
		th.start();
		
		
	}
	
	
	
    
}
