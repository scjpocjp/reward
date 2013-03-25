package com.android.reward.lib.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import android.os.Message;

import com.android.reward.lib.exception.RequestRepeatException;
import com.android.reward.lib.parser.SAXParsing;
import com.android.reward.lib.request.HttpRequest;
import com.android.reward.lib.util.Constants;
import com.android.reward.lib.util.Keys;
import com.android.reward.lib.util.Msg;
import com.android.reward.lib.util.Print;
import com.android.reward.lib.util.Util;


public class Login  {

	
	private String email = null;
	private String password = null;
    
	public Login ( ) {
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


	public String getPassword() {
		return password;
	}


	public Msg setPassword(String password) {
		this.password = password;
		
		Msg msg = new Msg ();
		
		if ( password == null ) {
			
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Password cannot be null" );
		
		} else if  ( password != null && password.trim().length() == 0 ) {
			
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Password cannot be blank " );
		
		} else if ( password.trim().length() < 6 ) {
			
			msg.setiStatusCode( -1 );
			msg.setvMsg( "Min 6 character password" );
		
		} else  {
			
			msg.setiStatusCode( 1 );
			msg.setvMsg( "Password is appropriate " );
		}
		
		return msg;
	}


	// check if  email is valid or not 
	public boolean isValidEmail ( ) {
		return new Util ().isValidEmail ( email );
	}
	
	// check with the server and send status code accordingly
	public void login () {
		
		Print.getInstance().show( " Login --------------------------" );
		
				// generate params 
				final List < NameValuePair > paramNameValuePair = new ArrayList < NameValuePair > ();
				paramNameValuePair.add(  new NameValuePair( "emailId", email ) );
				paramNameValuePair.add(  new NameValuePair( "password", password ) );
				
				Runnable r = new Runnable () {

					@Override
					public void run() {
						HttpRequest request = new HttpRequest( Keys.LOGIN_URL, null, Constants.POST_METHOD, paramNameValuePair );
						try {
							final Object result = request.send();
							final SAXParsing parsing = new SAXParsing ( result, SAXParsing.LOGIN_PARSER );
							
							Constants.handler.post( new Runnable () {

								@Override
								public void run() {
									Message msg = new Message ();
									msg.what = parsing.getStatus();
									if ( parsing.getStatus() == -1 ) {
										msg.obj = parsing.getReason();
									} else {
										msg.obj = result;
									}
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
