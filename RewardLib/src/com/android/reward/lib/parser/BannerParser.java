package com.android.reward.lib.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class BannerParser extends BaseParser {
	
	
	
	public void startElement(String uri, String localName, String qName, Attributes attributes ) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
	}
	
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		super.characters(ch, start, length );
	}
}
