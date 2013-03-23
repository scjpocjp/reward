package com.android.reward.lib.util;

public class Print {

	private static Print instance = null;
	private Print () {
		instance = this;
	}
	
	public synchronized static Print getInstance () {
		
		if ( instance == null ) {
			instance = new Print();
		}
		
		return instance;
	}
	
	public void show ( String str ) {
		
		if ( Keys.LOG_SHOW ) {
			System.out.println ( str );
		}
	}
	
	
	public void show ( Exception e ) {
		if ( Keys.LOG_SHOW ) {
			e.printStackTrace();
		}
	}
	
	public void show ( Error e ) {
		if ( Keys.LOG_SHOW ) {
			e.printStackTrace();
		}
	}
	
}
