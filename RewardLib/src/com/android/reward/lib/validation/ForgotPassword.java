package com.android.reward.lib.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import android.os.Message;

import com.android.reward.lib.app.RewardLibApplication;
import com.android.reward.lib.exception.RequestRepeatException;
import com.android.reward.lib.parser.SAXParsing;
import com.android.reward.lib.request.HttpRequest;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.util.Util;


public class ForgotPassword  {

	
	private String email = null;
	
	public ForgotPassword ( ) {
	}
	
	
	
	public String getEmail() {
		return email;
	}


	public Msg setEmail ( String email ) {
		this.email = email;
		
		Msg msg = new Msg ();
		if ( email == null ) {
		
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Email cannot be null" );
		
		} else if ( email != null && email.trim().length() == 0 ) {
		
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
		return new Util ().isValidEmail ( email );
	}
	
	// check with the server and send status code accordingly
	public void forgotPassword () {
		
		Print.getInstance().show( " forgotPassword --------------------------" );
		
				// generate params 
				final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
				paramNameValuePair.add(  new NameValuePair( "email", email ) );
				
				Runnable r = new Runnable () {

					@Override
					public void run() {
						HttpRequest request = new HttpRequest( Keys.FORGOT_PASSWORD_URL, null, Constants.POST_METHOD, paramNameValuePair );
						try {
							final Object result = request.send();
							final SAXParsing parsing = new SAXParsing ( result, SAXParsing.FORGOT_PASSWORD_PARSER );
							
							Constants.handler.post( new Runnable () {

								@Override
								public void run() {
									Message msg = new Message ();
									msg.what = parsing.getStatus();
									msg.obj = parsing.getReason();
									Constants.appInstance.callUpdateOnFragments( msg );
								}
								
							});
						} catch (RequestRepeatException e) {
							Print.getInstance().show(e);
						}
						
					}
					
				};
				RewardLibApplication.getThreadPoolExecutor().execute( r );
				

	}
	
	
	
    
}
