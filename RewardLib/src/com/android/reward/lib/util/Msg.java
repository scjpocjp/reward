package com.android.reward.lib.util;

public class Msg {

	private int iStatusCode = 0;
	private String vMsg = null; 
	
	
	public Msg () {
		
	}
	
	public Msg (  int iStatusCode, String vMsg ) {
		this.iStatusCode = iStatusCode;
		this.vMsg = vMsg;
	}

	public int getiStatusCode() {
		return iStatusCode;
	}

	public void setiStatusCode ( int iStatusCode ) {
		this.iStatusCode = iStatusCode;
	}

	public String getvMsg() {
		return vMsg;
	}

	public void setvMsg ( String vMsg ) {
		this.vMsg = vMsg;
	}
	
	
	
}
