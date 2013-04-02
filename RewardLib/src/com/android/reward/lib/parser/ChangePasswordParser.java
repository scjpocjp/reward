package com.android.reward.lib.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.android.reward.lib.util.Constants;

public class ChangePasswordParser extends BaseParser {
	
	private boolean isPasswordChange = false;
	
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {

		super.startElement(uri, localName, qName, attributes);
		
		if ( qName.equalsIgnoreCase( "password_change" ) ) {
			isPasswordChange = true;
		} 
		
	}
	
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		
		super.endElement(uri, localName, qName);
		
		if ( qName.equalsIgnoreCase( "password_change" ) ) {
			isPasswordChange = false;
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		super.characters(ch, start, length );
		if ( isPasswordChange ) {
			
			isPasswordChange = false;
		} 

	}
}
