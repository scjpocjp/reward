package com.android.reward.lib.validation;

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
	}
	
	
	
    
}
