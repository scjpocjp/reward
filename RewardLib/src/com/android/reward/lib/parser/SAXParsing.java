package com.android.reward.lib.parser;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.android.reward.lib.util.Print;

public class SAXParsing {

	public static final int LOGIN_PARSER = 0;
	
	private SAXParser saxParser;
	private BaseParser baseParser;
	
	
	
	public SAXParsing ( Object result, int type ) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
			
			switch ( type ) {
			case LOGIN_PARSER : baseParser = new LoginParser(); 
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader( (( StringBuffer ) result).toString() ) );
								saxParser.parse( is, ( LoginParser ) baseParser );
								break;
			
			}
			
		} catch (ParserConfigurationException e) {
			Print.getInstance().show( e );
		} catch (SAXException e) {
			Print.getInstance().show( e );
		} catch (IOException e) {
			Print.getInstance().show( e );
		}
	}
	
	public int getStatus () {
		if ( baseParser != null ) {
			return ( baseParser.isSuccess () )? 1 : -1;
		} else {
			return -1;
		}
	}
	
	public String getReason () {
		if ( baseParser != null ) {
			return baseParser.getReason();
		} else {
			return null;
		}
	}
}
