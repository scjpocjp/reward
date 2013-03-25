package com.android.reward.lib.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.android.reward.lib.util.Constants;

public class LoginParser extends BaseParser {
	
	private boolean isUserId = false;
	private boolean isFirstname = false;
	private boolean isLastname = false;
	private boolean isPhone = false;
	private boolean isPoints = false;
	
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {

		super.startElement(uri, localName, qName, attributes);
		
		if ( qName.equalsIgnoreCase( "user_id" ) ) {
			isUserId = true;
		} else if ( qName.equalsIgnoreCase( "firstname" ) ) {
			isFirstname = true;
		} else if ( qName.equalsIgnoreCase( "lastname" ) ) {
			isLastname = true;
		} else if ( qName.equalsIgnoreCase( "phone" ) ) {
			isPhone = true;
		} else if ( qName.equalsIgnoreCase( "points" ) ) {
			isPoints = true;
		}
		
	}
	
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		
		super.endElement(uri, localName, qName);
		
		if ( qName.equalsIgnoreCase( "user_id" ) ) {
			isUserId = false;
		} else if ( qName.equalsIgnoreCase( "firstname" ) ) {
			isFirstname = false;
		} else if ( qName.equalsIgnoreCase( "lastname" ) ) {
			isLastname = false;
		} else if ( qName.equalsIgnoreCase( "phone" ) ) {
			isPhone = false;
		} else if ( qName.equalsIgnoreCase( "points" ) ) {
			isPoints = false;
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		super.characters(ch, start, length );
		if ( isFirstname ) {
			Constants.vFirstName = new String ( ch, start, length );
			isFirstname = false;
		} else if ( isLastname ) {
			Constants.vLastName = new String ( ch, start, length );
			isLastname = false;
		} else if ( isUserId ) {
			Constants.vUserId = new String ( ch, start, length );
			isUserId = false;
		} else if ( isPhone ) {
			Constants.vPhone = new String ( ch, start, length );
			isPhone = false;
		} else if ( isPoints ) {
			Constants.vPoints = new String ( ch, start, length );
			isPoints = false;
		}
	}
}
