package com.android.reward.lib.exception;

/**
 * exception for closing the database without application context. 
 */
public class NotApplicationContextException extends Exception {
	
	
	public NotApplicationContextException ( String msg ) {
		super ( msg );
	}
}
