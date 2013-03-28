package com.android.reward.lib.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BaseParser extends DefaultHandler {

	protected boolean isStatus = false;
	protected boolean isReason = false;

	protected String status = null;
	protected String reason = null;

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if ( qName.equalsIgnoreCase( "status" ) ) {
			isStatus = true;
		} else if ( qName.equalsIgnoreCase( "reason" ) || qName.equalsIgnoreCase( "message" ) ) {
			isReason = true;
		}

	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if ( qName.equalsIgnoreCase( "status" ) ) {
			isStatus = false;
		} else if ( qName.equalsIgnoreCase( "reason" ) || qName.equalsIgnoreCase( "message" ) ) {
			isReason = false;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if ( isStatus ) {
			status = new String ( ch, start, length );
			isStatus = false;
		} else if ( isReason ) {
			reason = new String ( ch, start, length );
			isReason = false;
		}
	}

	public boolean isSuccess () {
		if ( status == null || ( status != null && status.trim().equalsIgnoreCase( "success" ) ) ) {
			return true;
		} else {
			return false;
		}
	}

	public String getReason () {
		return reason;
	}

}
